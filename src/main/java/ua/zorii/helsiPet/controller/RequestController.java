package ua.zorii.helsiPet.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.zorii.helsiPet.dto.AppointmentDTO;
import ua.zorii.helsiPet.dto.UserDTO;
import ua.zorii.helsiPet.entity.Animal;
import ua.zorii.helsiPet.entity.Appointment;
import ua.zorii.helsiPet.entity.Owner;
import ua.zorii.helsiPet.entity.User;
import ua.zorii.helsiPet.entity.Vet;
import ua.zorii.helsiPet.enums.RequestStatus;
import ua.zorii.helsiPet.service.AppointmentService;
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

    public RequestController(AppointmentService appointmentService, UserService userService, PetService petService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
        this.petService = petService;
    }

    @GetMapping
    public String getRequestsPage(Model model) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) currentRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession(false);
        final String usernameFromSession = (String) session.getAttribute("user");

        final User userFromDB = userService.getUserInfoFromUsersTableByUsername(usernameFromSession);
        final List<Appointment> appointments = appointmentService.getAppointmentsByOwnerUsername(usernameFromSession);
        final List<AppointmentDTO> appointmentDTOS = convertToAppointmentDTO(appointments);

        model.addAttribute("appointments", appointmentDTOS);
        model.addAttribute("userType", userFromDB.getUserType().toString());

        return "myRequests";
    }

    @GetMapping("/vet")
    public String getVetRequestPage(Model model) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) currentRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession(false);
        final String usernameFromSession = (String) session.getAttribute("user");
        final User userFromDB = userService.getUserInfoFromUsersTableByUsername(usernameFromSession);

        final List<Appointment> appointments = appointmentService.getAppointmentsByVetUsername(usernameFromSession);
        final List<AppointmentDTO> appointmentDTOS = convertToAppointmentDTO(appointments);
        model.addAttribute("appointments", appointmentDTOS);
        model.addAttribute("userType", userFromDB.getUserType().toString());

        return "myRequests";
    }

    @PostMapping("/confirm")
    public String confirmRequest(@RequestParam Integer requestId) {
        appointmentService.updateAppointmentStatus(requestId, RequestStatus.ПІДТВЕРДЖЕНО);
        return "redirect:/";
    }

    @PostMapping("/complete")
    public String completeRequest(@RequestParam Integer requestId) {
        appointmentService.updateAppointmentStatus(requestId, RequestStatus.ВИКОНАНО);
        return "redirect:/";
    }


    @PostMapping("/reject")
    public String rejectRequest(@RequestParam Integer requestId) {
        appointmentService.removeAppointment(requestId);
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

    public void sendEmail(User user, UserDTO userDTO, String siteUrl) {
        String subject = "Please verify your registration";
        String content = "Dear " + userDTO.getFirstname()
                + ", Please click the link below to verify your registration: "
                + siteUrl + "/registration/verify?code=" + user.getVerificationCode()
                + " Thank you!";

        MailUtil.initializeMessage(userDTO.getEmail(), subject, content);
    }

}
