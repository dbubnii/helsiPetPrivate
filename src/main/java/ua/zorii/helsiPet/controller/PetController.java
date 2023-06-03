package ua.zorii.helsiPet.controller;

import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.zorii.helsiPet.entity.Animal;
import ua.zorii.helsiPet.entity.Owner;
import ua.zorii.helsiPet.enums.PetType;
import ua.zorii.helsiPet.enums.Sex;
import ua.zorii.helsiPet.enums.Size;
import ua.zorii.helsiPet.service.PetService;
import ua.zorii.helsiPet.service.UserService;
import ua.zorii.helsiPet.util.PDFExporter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Controller
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;
    private final UserService userService;

    public PetController(PetService petService, UserService userService) {
        this.petService = petService;
        this.userService = userService;
    }

    @GetMapping
    public String getPetPage(Model model) {
        final String usernameFromSession = getUsernameFromSession();

        List<Animal> allPets = petService.getAllPetsForOwnerByUsername(usernameFromSession);
        model.addAttribute("pets", allPets);
        return "petPage";
    }

    @GetMapping("/addByUniqueID")
    public String getAddByIDPage() {
        return "addPetByUniqueID";
    }

    @PostMapping("/byUniqueID")
    public String getPetByUniqueID(@RequestParam String uniqueID, Model model) {
        final Animal petByUniqueID = petService.getPetByUniqueID(uniqueID);

        if (petByUniqueID == null) {
            return "redirect:/";
        }

        model.addAttribute("pet", petByUniqueID);
        return "redirect:/pet/profile/" + petByUniqueID.getId();
    }

    @PostMapping("/assignToMe")
    public String assignPet(@RequestParam Integer petId) {
        final String usernameFromSession = getUsernameFromSession();

        petService.assignPetToOwner(usernameFromSession, petId);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        final String usernameFromSession = getUsernameFromSession();
        final Owner owner = userService.getOwnerInfo(usernameFromSession);

        model.addAttribute("owner", owner);
        model.addAttribute("petSex", Arrays.stream(Sex.values()).map(Enum::toString).collect(Collectors.toList()));
        model.addAttribute("petSize", Arrays.stream(Size.values()).map(Enum::toString).collect(Collectors.toList()));
        model.addAttribute("petType", Arrays.stream(PetType.values()).map(Enum::toString).collect(Collectors.toList()));
        return "addPet";
    }

    @PostMapping("/add")
    public String addPet(@RequestParam String name,
                         @RequestParam String breed,
                         @RequestParam String petSex,
                         @RequestParam Integer age,
                         @RequestParam String sterilized,
                         @RequestParam String petSize,
                         @RequestParam String weight,
                         @RequestParam String petType,
                         @RequestParam String details,
                         @RequestParam String ownerUsername) {
        final Animal animal = Animal.builder()
                .uniqueID(RandomStringUtils.random(10, "0123456789abcdef"))
                .name(name)
                .breed(breed)
                .sex(Sex.valueOf(petSex))
                .age(age)
                .sterilized(sterilized.equals("ТАК"))
                .size(Size.valueOf(petSize))
                .weight(Float.valueOf(weight))
                .type(PetType.valueOf(petType))
                .details(details)
                .ownerUsername(ownerUsername)
                .build();
        petService.addPet(animal);
        return "redirect:/";
    }

    @GetMapping("/forVet/{id}")
    public String getPetPageForVet(@PathVariable Integer id, Model model) {
        final List<Animal> allPetsByVetId = petService.getAllPetsByVetId(id);
        model.addAttribute("pets", allPetsByVetId);
        return "vetPatians";
    }

    @GetMapping("/profile/{id}")
    public String getPetProfilePage(@PathVariable Integer id, Model model) {
        final String usernameFromSession = getUsernameFromSession();
        final Owner ownerInfo = userService.getOwnerInfo(usernameFromSession);
        final Animal pet = petService.getPetById(id);

        model.addAttribute("owner", ownerInfo);
        model.addAttribute("petInfo", pet);
        return "petProfile";
    }

    @GetMapping("/history/pdf/{id}")
    public void generateHistoryPDF(@PathVariable Integer id, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + "Історія_хворіб" + ".pdf";
        response.setHeader(headerKey, headerValue);
        response.setContentType("application/pdf");

        Animal animal = petService.getPetById(id);

        PDFExporter exporter = new PDFExporter(animal);
        exporter.export(response);
    }

    @GetMapping("/appointment")
    public String getAppointmentPage() {
        return "appointment";
    }

    private static String getUsernameFromSession() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) currentRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession(false);
        return (String) session.getAttribute("user");
    }
}
