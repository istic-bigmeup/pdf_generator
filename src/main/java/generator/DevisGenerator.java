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
            write(500, 100, "Coucou Raymond");
            write(400, 100, "Coucou Yannick");

            document.save("resources/" + this.title + "_" + this.subject + ".pdf");

            document.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
