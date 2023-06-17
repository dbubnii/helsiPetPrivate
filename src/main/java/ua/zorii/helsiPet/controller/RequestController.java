package ua.zorii.helsiPet.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.zorii.helsiPet.dto.AppointmentDTO;
import ua.zorii.helsiPet.dto.HistoryDTO;
import ua.zorii.helsiPet.dto.UserDTO;
import ua.zorii.helsiPet.entity.Animal;
import ua.zorii.helsiPet.entity.Appointment;
import ua.zorii.helsiPet.entity.Owner;
import ua.zorii.helsiPet.entity.User;
import ua.zorii.helsiPet.entity.Vet;
import ua.zorii.helsiPet.enums.RequestStatus;
import ua.zorii.helsiPet.service.AppointmentService;
import ua.zorii.helsiPet.service.HistoryService;
import ua.zorii.helsiPet.service.PetService;
import ua.zorii.helsiPet.service.UserService;
import ua.zorii.helsiPet.util.MailUtil;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Controller
@RequestMapping("/request")
public class RequestController {
    private final AppointmentService appointmentService;
    private final UserService userService;
    private final PetService petService;
    private final HistoryService historyService;

    public RequestController(AppointmentService appointmentService, UserService userService, PetService petService, HistoryService historyService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
        this.petService = petService;
        this.historyService = historyService;
    }

    @GetMapping
    public String getRequestsPage(Model model) {
        final String usernameFromSession = getUsernameFromSession();

        final User userFromDB = userService.getUserInfoFromUsersTableByUsername(usernameFromSession);
        final List<Appointment> appointments = appointmentService.getAppointmentsByOwnerUsername(usernameFromSession);
        final List<AppointmentDTO> appointmentDTOS = convertToAppointmentDTO(appointments);

        model.addAttribute("appointments", appointmentDTOS);
        model.addAttribute("userType", userFromDB.getUserType().toString());

        return "myRequests";
    }

    @GetMapping("/vet")
    public String getVetRequestPage(Model model) {
        final String usernameFromSession = getUsernameFromSession();
        final User userFromDB = userService.getUserInfoFromUsersTableByUsername(usernameFromSession);

        final List<Appointment> appointments = appointmentService.getAppointmentsByVetUsername(usernameFromSession);
        final List<AppointmentDTO> appointmentDTOS = convertToAppointmentDTO(appointments);
        model.addAttribute("appointments", appointmentDTOS);
        model.addAttribute("userType", userFromDB.getUserType().toString());

        return "myRequests";
    }

    @PostMapping("/confirm")
    public String confirmRequest(@RequestParam Integer requestId, @RequestParam String ownerUsername, HttpServletRequest request) {
        appointmentService.updateAppointmentStatus(requestId, RequestStatus.ПІДТВЕРДЖЕНО);
        final Owner ownerInfo = userService.getOwnerInfo(ownerUsername);
        String message = """
                Лікар прийняв Ваш запис на прийом, очікуйте подальших повідомлень.
                Статус вашого запиту можна переглянути тут %s
                """;
        String formattedMessage = String.format(message, getSiteURL(request) + "/request");
        sendEmail(ownerInfo.getEmail(), formattedMessage);

        return "redirect:/";
    }

    @PostMapping("/complete")
    public String completeRequest(@RequestParam Integer requestId,
                                  @RequestParam String ownerUsername,
                                  @RequestParam String vetUsername,
                                  @RequestParam String petName,
                                  HttpServletRequest request) {
        appointmentService.updateAppointmentStatus(requestId, RequestStatus.ВИКОНАНО);
        final Owner ownerInfo = userService.getOwnerInfo(ownerUsername);
        String message = """
                Ваш запит успішно виконано. Дякуємо за візит!
                Статус та рецепт переглянути тут %s
                """;
        String formattedMessage = String.format(message, getSiteURL(request) + "/request");
        sendEmail(ownerInfo.getEmail(), formattedMessage);

        final HistoryDTO history = HistoryDTO.builder()
                .vet(userService.getVetInfo(vetUsername))
                .owner(ownerInfo)
                .animal(petService.getPetByName(petName))
                .appointment(appointmentService.getAppointmentById(requestId))
                .build();
        historyService.saveHistory(history);
        return "redirect:/";
    }


    @PostMapping("/reject")
    public String rejectRequest(@RequestParam Integer requestId, @RequestParam String ownerUsername, HttpServletRequest request) {
        appointmentService.removeAppointment(requestId);
        final Owner ownerInfo = userService.getOwnerInfo(ownerUsername);
        String message = """
                На жаль, Лікар відмінив Ваш запис на прийом, спробуйте обрати іншу дату, або зателефонуйте нам.
                """;
        sendEmail(ownerInfo.getEmail(), message);

        return "redirect:/";
    }

    private List<AppointmentDTO> convertToAppointmentDTO(List<Appointment> appointments) {
        return appointments.stream().map(appointment -> {
            final Vet vet = userService.getVetInfo(appointment.getVetUsername());
            final Owner owner = userService.getOwnerInfo(appointment.getOwnerUsername());
            final Animal animal = petService.getPetByName(appointment.getOwnerPetName());
            return AppointmentDTO.builder()
                    .id(appointment.getId())
                    .vet(vet)
                    .owner(owner)
                    .animal(animal)
                    .vetAvailableDate(appointment.getVetAvailableDate())
                    .details(appointment.getDetails())
                    .status(appointment.getStatus())
                    .receiptId(appointment.getReceiptId())
                    .build();
        }).collect(Collectors.toList());
    }

    private static String getUsernameFromSession() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) currentRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession(false);
        return (String) session.getAttribute("user");
    }

    public void sendEmail(String email, String message) {
        String subject = "Запис на прийом";

        MailUtil.initializeMessage(email, subject, message);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
