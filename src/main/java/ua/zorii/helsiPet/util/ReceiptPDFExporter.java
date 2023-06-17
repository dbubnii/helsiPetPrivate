package ua.zorii.helsiPet.util;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import ua.zorii.helsiPet.entity.Animal;
import ua.zorii.helsiPet.entity.Receipt;

import javax.print.Doc;
import java.awt.*;
import java.io.IOException;

public class ReceiptPDFExporter {
    private final Receipt receipt;

    public ReceiptPDFExporter(Receipt receipt) {
        this.receipt = receipt;
    }

    private void writeTextData(Document document) {
        FontFactory.register("c:\\windows\\fonts\\arial.ttf");
        Font font = FontFactory.getFont("arial",  "windows-1251");
        font.setSize(14);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("Електронний Рецепт", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        document.add(new Paragraph("Ідентифікатор рецепту: " + receipt.getId(), font));
        document.add(new Paragraph("ПІБ Ветеринара: " + receipt.getVetFullName(), font));
        document.add(new Paragraph("ПІБ Власника: " + receipt.getOwnerFullName(), font));
        document.add(new Paragraph("Призначенні ліки: " + receipt.getDrugName(), font));
        document.add(new Paragraph("Рекомендації щодо прийому ліків: " + receipt.getDrugCount(), font));
        document.add(new Paragraph("Дата початку дії рецепта: " + receipt.getDateStart(), font));
        document.add(new Paragraph("Дата кінця дії рецепта: " + receipt.getDateEnd(), font));
        document.add(new Paragraph("Назва клініки: " + receipt.getClinicName(), font));
        document.add(new Paragraph("Деталі рецепту: " + receipt.getDetails(), font));
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        writeTextData(document);

        document.close();

    }
}
