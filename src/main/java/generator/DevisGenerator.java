package generator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.*;
import java.io.*;

/**
 * Created by jérémy on 10/02/2017.
 */
public class DevisGenerator extends Generator {
    private final String PATH = "resources/devis.pdf";
    /**
     * The constructor
     * @param title     The title
     * @param subject   The subject
     */
    public DevisGenerator(String title, String subject){
        super(title, subject);

        try {
            PDDocument document = PDDocument.load(new File(PATH));
            setDocument(document);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Generates the PDF file
     */
    @Override
    public void generate() {
        try{
            // Phone number
            write(100, 720, "02 11 22 33 44");

            // Email
            write(78, 696, "le@mail.com");

            // N SIREN
            write(98, 677, "12345679098");

            // Date
            write(348, 575, "10/02/2017");

            // Devis number
            write(340, 533, "0001");

            // Products
            write(61, 470, "Produit 1");

            // Quantities
            write(342, 470, "1");

            // Unit's price
            write(403, 470, "33€");

            // Total price
            write(475, 470, "33€");

            document.save("resources/" + this.title + "_" + this.subject + ".pdf");

            document.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
