package generator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.*;

/**
 * Created by jérémy on 10/02/2017.
 */
public class DevisGenerator extends Generator {
    /**
     * The constructor
     * @param title     The title
     * @param subject   The subject
     * @param document  The document
     */
    public DevisGenerator(String title, String subject, PDDocument document){
        super(title, subject, document);
    }

    /**
     * Generates the PDF file
     */
    @Override
    public void generate() {
        try{
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);

            Rectangle rectangle = new Rectangle( 10, 280, 275, 600);
            stripper.addRegion("class1", rectangle);

            PDPage firstPage = document.getPage(0);
            stripper.extractRegions(firstPage);

            System.out.println("Text in the area" + rectangle);
            System.out.println(stripper.getTextForRegion("class1"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
