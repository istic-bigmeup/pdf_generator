package generator;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;

/**
 * Created by jérémy on 10/02/2017.
 */
public class BonDeCommandeGenerator extends Generator{
    /**
     * The constructor
     * @param title     The title
     * @param subject   The subject
     */
    public BonDeCommandeGenerator(String title, String subject){
        super(title, subject);

        try {
            PDDocument document = PDDocument.load(new File("resources/bon_de_commande.pdf"));
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

    }
}
