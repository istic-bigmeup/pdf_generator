package generator;

import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * Created by jérémy on 10/02/2017.
 */
public class FactureGenerator extends Generator {
    /**
     * The constructor
     * @param title     The title
     * @param subject   The subject
     * @param document  The document
     */
    public FactureGenerator(String title, String subject, PDDocument document){
        super(title, subject, document);
    }

    /**
     * Generates the PDF file
     */
    @Override
    public void generate() {

    }
}
