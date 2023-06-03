package ua.zorii.helsiPet.util;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import ua.zorii.helsiPet.entity.Animal;

import java.awt.*;
import java.io.IOException;

import static com.lowagie.text.pdf.BaseFont.IDENTITY_H;

public class PDFExporter {
    private Animal animal;

    public PDFExporter(Animal animal) {
        this.animal = animal;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Animal ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Breed", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Sex", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Details", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        FontFactory.register("c:\\windows\\fonts\\arial.ttf");
        Font font = FontFactory.getFont("arial",  "windows-1251");

        table.addCell(new Phrase(String.valueOf(animal.getId()), font));
        table.addCell(new Phrase(animal.getName(), font));
        table.addCell(new Phrase(animal.getBreed(), font));
        table.addCell(new Phrase(animal.getSex().toString(), font));
        table.addCell(new Phrase(animal.getDetails(), font));
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        FontFactory.register("c:\\windows\\fonts\\arial.ttf");
        Font font = FontFactory.getFont("arial",  "windows-1251");
        font.setSize(18);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("Історія хворіб", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
