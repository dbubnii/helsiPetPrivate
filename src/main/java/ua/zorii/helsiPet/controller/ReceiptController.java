package ua.zorii.helsiPet.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.zorii.helsiPet.dto.UserDTO;
import ua.zorii.helsiPet.entity.Animal;
import ua.zorii.helsiPet.entity.Appointment;
import ua.zorii.helsiPet.entity.Owner;
import ua.zorii.helsiPet.entity.Receipt;
import ua.zorii.helsiPet.entity.User;
import ua.zorii.helsiPet.service.AppointmentService;
import ua.zorii.helsiPet.service.ReceiptService;
import ua.zorii.helsiPet.service.UserService;
import ua.zorii.helsiPet.util.MailUtil;
import ua.zorii.helsiPet.util.PDFExporter;
import ua.zorii.helsiPet.util.ReceiptPDFExporter;

import java.io.IOException;
import java.util.Random;

import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Controller
@RequestMapping("/receipt")
public class ReceiptController {
    private final ReceiptService receiptService;
    private final AppointmentService appointmentService;
    private final UserService userService;

    public ReceiptController(ReceiptService receiptService, AppointmentService appointmentService, UserService userService) {
        this.receiptService = receiptService;
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    @GetMapping("{id}")
    public String getReceiptPage(@PathVariable Integer id, Model model) {
        model.addAttribute("appointmentId", id);
        return "receipt";
    }

    @GetMapping("/get/{id}")
    public void getReceipt(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + "Електронний рецепт" + ".pdf";
        response.setHeader(headerKey, headerValue);
        response.setContentType("application/pdf");

        final Receipt receiptById = receiptService.getReceiptById(id);

        ReceiptPDFExporter exporter = new ReceiptPDFExporter(receiptById);
        exporter.export(response);
    }

    @PostMapping("/create")
    public String createReceipt(@RequestParam String vetFullName,
                                @RequestParam String ownerFullName,
                                @RequestParam String drugName,
                                @RequestParam String drugCount,
                                @RequestParam String dateStart,
                                @RequestParam String dateEnd,
                                @RequestParam String clinicName,
                                @RequestParam String details,
                                @RequestParam Integer appointmentId, HttpServletRequest request) {

        final Receipt receipt = Receipt.builder()
                .id(new Random().nextInt(1000 - 10) + 10)
                .vetFullName(vetFullName)
                .ownerFullName(ownerFullName)
                .drugName(drugName)
                .drugCount(drugCount)
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .clinicName(clinicName)
                .details(details)
                .build();
        receiptService.createReceipt(receipt);
        appointmentService.updateAppointment(appointmentId, receipt.getId());

        final Appointment appointmentById = appointmentService.getAppointmentById(appointmentId);
        final Owner ownerInfo = userService.getOwnerInfo(appointmentById.getOwnerUsername());
        sendVerificationEmail(ownerInfo.getEmail(), vetFullName, receipt.getId(), getSiteURL(request));
        return "redirect:/";
    }

    private void sendVerificationEmail(String email, String vetFullName, Integer receiptId, String siteUrl) {
        String subject = "Електронний рецепт";
        String content = """
                Вітаю! Лікар %s створив електронний рецепт для Вашої тварини!
                Ви можете переглянути його тут: %s
                """;
        String url = siteUrl + "/receipt/get/" + receiptId;
        final String contentFormatted = String.format(content, vetFullName, url);

        MailUtil.initializeMessage(email, subject, contentFormatted);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

}
