package ua.zorii.helsiPet.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.zorii.helsiPet.entity.Animal;
import ua.zorii.helsiPet.entity.Appointment;
import ua.zorii.helsiPet.entity.Owner;
import ua.zorii.helsiPet.entity.Vet;
import ua.zorii.helsiPet.enums.RequestStatus;
import ua.zorii.helsiPet.service.AppointmentService;
import ua.zorii.helsiPet.service.PetService;
import ua.zorii.helsiPet.service.UserService;

import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    private final UserService userService;
    private final PetService petService;
    private final AppointmentService appointmentService;

    public AppointmentController(UserService userService, PetService petService, AppointmentService appointmentService) {
        this.userService = userService;
        this.petService = petService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/form/{username}")
    public String makeAppointment(@PathVariable String username, Model model) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) currentRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession(false);
        final String usernameFromSession = (String) session.getAttribute("user");
        final List<Animal> ownerPets = petService.getAllPetsForOwnerByUsername(usernameFromSession);
        final Vet vet = userService.getVetInfo(username);
        final Owner owner = userService.getOwnerInfo(usernameFromSession);

        final List<String> availableDates = asList(vet.getAvailableAppointmentDate().split(";"));
        model.addAttribute("vet", vet);
        model.addAttribute("owner", owner);
        model.addAttribute("ownerPets", ownerPets);
        model.addAttribute("vetAvailableDates", availableDates);
        return "appointmentForm";
    }

    @PostMapping
    public String appointment(@RequestParam String vetUsername,
                              @RequestParam String ownerUsername,
                              @RequestParam String ownerPetName,
                              @RequestParam String vetAvailableDate,
                              @RequestParam String details) {
        final Appointment appointment = Appointment.builder()
                .vetUsername(vetUsername)
                .ownerUsername(ownerUsername)
                .ownerPetName(ownerPetName)
                .vetAvailableDate(vetAvailableDate)
                .details(details)
                .build();
        appointmentService.saveAppointmentToDB(appointment, RequestStatus.ОЧІКУЄТЬСЯ);
        return "redirect:/";
    }
}
