package generator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.*;
import java.io.File;

/**
 * Created by jérémy on 10/02/2017.
 */
public class FactureGenerator extends Generator {
    /**
     * The constructor
     * @param title     The title
     * @param subject   The subject
     */
    public FactureGenerator(String title, String subject){
        super(title, subject);

        try {
            PDDocument document = PDDocument.load(new File("resources/facture.pdf"));
            setDocument(document);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Writes the header
     */
    @Override
    protected void header() throws Exception {
        PDFont font = PDTrueTypeFont.loadTTF(document, new File("resources/arial.ttf"));
    }

    /**
     * Writes the body
     */
    @Override
    protected void body() throws Exception {
        PDFont font = PDTrueTypeFont.loadTTF(document, new File("resources/arial.ttf"));
    }

    /**
     * Writes the footer
     */
    @Override
    protected void footer() throws Exception {
        PDFont font = PDTrueTypeFont.loadTTF(document, new File("resources/arial.ttf"));
    }
}
