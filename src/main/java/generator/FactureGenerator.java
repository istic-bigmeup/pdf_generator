package generator;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;

/**
 * Created by jérémy on 10/02/2017.
 *
 */
public class FactureGenerator extends Generator {
    /**
     * The constructor
     * @param title     The title
     * @param subject   The subject
     */
    public FactureGenerator(String title, String subject) {
        super(title, subject);

        try {
            PDDocument document = PDDocument.load(new File("resources/facture.pdf"));
            setDocument(document);
            initFonts();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Writes the header
     */
    @Override
    protected void header() throws Exception {
        // PRESTA
        // Name
        write(57, 765, "Le Presta", arial, FONT_SIZE_NORMAL);

        // Siret
        write(93, 746, "0258 852 156 879", arial, FONT_SIZE_NORMAL);

        // CUSTOMER
        // Name
        write(350, 725, "Le Client", arialBold, FONT_SIZE_NORMAL);

        // City
        write(350, 710, "Rennes 35000", arial, FONT_SIZE_NORMAL);

        // Date
        write(91, 702, "23/12/2016", arial, FONT_SIZE_NORMAL);
    }

    /**
     * Writes the body
     */
    @Override
    protected void body() throws Exception {
        // Facture's number
        write(300, 644, "FAC20161200005", arialBold, FONT_SIZE_BIG_TITLE);

        // TABLE
        int y = 573;
        for(int i = 0; i < 5; i++){
            // Quantity
            write(55, y, "1", arial, FONT_SIZE_NORMAL);

            // Designation
            write(130, y, "Prod " + i, arial, FONT_SIZE_NORMAL);

            // Unit's price TTC
            write(330, y, "1€", arial, FONT_SIZE_NORMAL);

            // Total price TTC
            write(445, y, "1€", arial, FONT_SIZE_NORMAL);

            y -= INTER_LINE;
        }

        // TOTAL TTC
        // Unit's price TTC
        write(330, 235, "1€", arial, FONT_SIZE_NORMAL);

        // Total price TTC
        write(445, 235, "1€", arial, FONT_SIZE_NORMAL);
    }

    /**
     * Writes the footer
     */
    @Override
    protected void footer() throws Exception {
    }
}
