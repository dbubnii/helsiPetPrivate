package ua.zorii.helsiPet.util;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import ua.zorii.helsiPet.dto.HistoryCombinedDTO;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PDFExporter {
    private final List<HistoryCombinedDTO> historyCombinedDTOList;

    public PDFExporter(List<HistoryCombinedDTO> historyCombinedDTOList) {
        this.historyCombinedDTOList = historyCombinedDTOList;
    }

    private void writeTextData(Document document) {
        FontFactory.register("c:\\windows\\fonts\\arial.ttf");
        Font font = FontFactory.getFont("arial",  "windows-1251");
        font.setSize(14);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("Історія хворіб", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        for (HistoryCombinedDTO historyCombinedDTO : historyCombinedDTOList) {
            final Paragraph data = new Paragraph("Дані про власника та лікаря", font);
            data.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(data);
            document.add(new Paragraph("ПІБ власника: " + historyCombinedDTO.getOwnerFirstName() + " " + historyCombinedDTO.getOwnerLastName(), font));
            document.add(new Paragraph("Електронна пошта власника: " + historyCombinedDTO.getOwnerEmail(), font));
            document.add(new Paragraph("ПІБ ветеринара: " + historyCombinedDTO.getVetFirstName() + " " + historyCombinedDTO.getVetLastName(), font));
            document.add(new Paragraph("Електронна пошта ветеринара: " + historyCombinedDTO.getVetEmail(), font));
            final Paragraph petData = new Paragraph("Дані про тварину", font);
            petData.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(petData);
            document.add(new Paragraph("Кличка тварини: " + historyCombinedDTO.getPetName(), font));
            document.add(new Paragraph("Порода тварини: " + historyCombinedDTO.getPetBreed(), font));
            document.add(new Paragraph("Вік тварини: " + historyCombinedDTO.getPetAge(), font));
            document.add(new Paragraph("Стать тварини: " + historyCombinedDTO.getPetSex(), font));
            document.add(new Paragraph("Розмір тварини: " + historyCombinedDTO.getPetSize(), font));
            document.add(new Paragraph("Стерилізація тварини: " + (historyCombinedDTO.getPetSterilized().equals("1") ? "ТАК" : "НІ"), font));
            document.add(new Paragraph("Тип тварини: " + historyCombinedDTO.getPetType(), font));
            document.add(new Paragraph("Унікальний ідентифікатор тварини: " + historyCombinedDTO.getPetUniqueId(), font));
            document.add(new Paragraph("Вага тварини: " + historyCombinedDTO.getPetWeight() + " кг.", font));
            document.add(new Paragraph("Важливі деталі про тварину: " + historyCombinedDTO.getAnimalDetails(), font));
            final Paragraph otherData = new Paragraph("Дані про проведення", font);
            otherData.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(otherData);
            document.add(new Paragraph("Деталі при заповненні форми: " + historyCombinedDTO.getAppointmentDetails(), font));
            document.add(new Paragraph("Назва клініки: " + historyCombinedDTO.getReceiptClinicName(), font));
            document.add(new Paragraph("Дата та час прийому:" + historyCombinedDTO.getVetAvailableDate(), font));
            final Paragraph receiptData = new Paragraph("Дані про лікування", font);
            receiptData.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(receiptData);
            document.add(new Paragraph("Призначенні ліки: " + historyCombinedDTO.getReceiptDrugName(), font));
            document.add(new Paragraph("Рекомендації щодо вживання ліків: " + historyCombinedDTO.getReceiptDrugCount(), font));
            document.add(new Paragraph("Дата та час призначення рецепту: " + historyCombinedDTO.getReceiptDateStart(), font));
            document.add(new Paragraph("Дата та час завершення дії рецепту: " + historyCombinedDTO.getReceiptDateEnd(), font));
            document.add(new Paragraph("Деталі рецепту: " + historyCombinedDTO.getReceiptDetails(), font));
            document.add(new Paragraph("------------------------------------------------------------------", font));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        writeTextData(document);

        document.close();
    }
}
