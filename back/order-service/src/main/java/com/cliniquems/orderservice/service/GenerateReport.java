package com.cliniquems.orderservice.service;

import com.cliniquems.orderservice.model.Order;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;

@Configuration
public class GenerateReport {

    public void generate(Order order) throws FileNotFoundException {
        PdfWriter pdfWriter= new PdfWriter("E:\\clinique-ms\\bill\\Bill.pdf");
        PdfDocument pdfDocument= new PdfDocument(pdfWriter);
        Document document= new Document(pdfDocument);

        float threeCol= 190f;
        float twoCol= 285f;
        float twoCol150= twoCol+150f;

        float[] twoColWidth= {twoCol, twoCol};


        Table headerTable= new Table(twoColWidth);
        headerTable.addCell(new Cell().add(new Paragraph("FJ- Clinic")).setBold().setFontSize(15f).setBorder(Border.NO_BORDER));

        document.add(headerTable);

        document.close();


    }
}
