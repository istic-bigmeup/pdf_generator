package generator;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.*;

/**
 * Created by jérémy on 10/02/2017.
 *
 */
public class DevisGenerator extends Generator {
    /**
     * The constructor
     * @param title     The title
     * @param subject   The subject
     */
    public DevisGenerator(String title, String subject) {
        super(title, subject);

        try {
            PDDocument document = PDDocument.load(new File("resources/devis.pdf"));
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
        // Phone number
        write(100, 720, "02 11 22 33 44", arial, FONT_SIZE_NORMAL);

        // Email
        write(78, 697, "le@mail.com", arial, FONT_SIZE_NORMAL);

        // N SIREN
        write(98, 677, "12345679098", arial, FONT_SIZE_NORMAL);

        // Date
        write(348, 576, "10/02/2017", arial, FONT_SIZE_NORMAL);

        // Devis number
        write(340, 533, "0001", arialBold, FONT_SIZE_BIG_TITLE);
    }

    /**
     * Writes the body
     */
    @Override
    protected void body() throws Exception {
        int y = 470;
        for(int i = 0; i < 5; i ++) {
            // Products
            write(61, y, "Produit " + i, arial, FONT_SIZE_NORMAL);

            // Quantities
            write(342, y, "1", arial, FONT_SIZE_NORMAL);

            // Unit's price
            write(403, y, (i + i) + "€", arial, FONT_SIZE_NORMAL);

            // Total price
            write(475, y, (i + i) + "€", arial, FONT_SIZE_NORMAL);

            y -= INTER_LINE;
        }

        // SALE CONDITION
        // Modalities
        write(175, 331, "Carte bancaire", arialBold, FONT_SIZE_NORMAL);

        // Pay date
        write(152, 311, "12/11/2016", arial, FONT_SIZE_NORMAL);

        // Late penalty
        write(157, 290, "12€", arial, FONT_SIZE_NORMAL);

        // LIV CONDITION
        // Liv date
        write(145, 240, "15/11/2016", arial, FONT_SIZE_NORMAL);

        // Modalities
        write(166, 210, "Voie postale", arial, FONT_SIZE_NORMAL);

        // Remise
        write(475, 178, "0€", arial, FONT_SIZE_NORMAL);

        // TOTAL (HT)
        write(475, 163, "20€", arial, FONT_SIZE_NORMAL);
    }

    /**
     * Writes the footer
     */
    @Override
    protected void footer() throws Exception {
    }
}
