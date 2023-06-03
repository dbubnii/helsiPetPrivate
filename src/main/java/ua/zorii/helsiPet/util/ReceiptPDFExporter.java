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

import java.awt.*;
import java.io.IOException;

public class ReceiptPDFExporter {
    private Receipt receipt;

    public ReceiptPDFExporter(Receipt receipt) {
        this.receipt = receipt;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Receipt ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Vet Full Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Owner Full Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Drug Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Drug Count", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date Start", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date End", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Clinic name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Details", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        FontFactory.register("c:\\windows\\fonts\\arial.ttf");
        com.lowagie.text.Font font = FontFactory.getFont("arial",  "windows-1251");

        table.addCell(new Phrase(String.valueOf(receipt.getId()), font));
        table.addCell(new Phrase(receipt.getVetFullName(), font));
        table.addCell(new Phrase(receipt.getOwnerFullName(), font));
        table.addCell(new Phrase(receipt.getDrugName(), font));
        table.addCell(new Phrase(receipt.getDrugCount(), font));
        table.addCell(new Phrase(receipt.getDateStart(), font));
        table.addCell(new Phrase(receipt.getDateEnd(), font));
        table.addCell(new Phrase(receipt.getClinicName(), font));
        table.addCell(new Phrase(receipt.getDetails(), font));

    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A2);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        FontFactory.register("c:\\windows\\fonts\\arial.ttf");
        Font font = FontFactory.getFont("arial",  "windows-1251");
        font.setSize(18);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("Електронний Рецепт", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 3.5f, 3.0f, 1.5f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
