package generator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;

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
     * Writes the header
     */
    @Override
    protected void header() throws Exception {
        PDFont font = PDTrueTypeFont.loadTTF(document, new File(ARIAL));

        // Sender
        write(57, 785, "Jean-Luc", font, FONT_SIZE_NORMAL);
        // Adress
        write(57, 770, "5 rue Delarue", font, FONT_SIZE_NORMAL);
        // Postal Code & City
        write(57, 755, "35000, Rennes", font, FONT_SIZE_NORMAL);
        // Phone & Fax
        write(57, 740, "02 11 22 33 44, 01234566789", font, FONT_SIZE_NORMAL);
        // Email
        write(57, 725, "le@mail.fr", font, FONT_SIZE_NORMAL);

        // Receiver
        write(327, 705, "Jean-Lucque", font, FONT_SIZE_NORMAL);
        // Name
        write(327, 690, "À l'attention de M Boubze", font, FONT_SIZE_NORMAL);
        // Adress
        write(327, 675, "1 rue du dortoir", font, FONT_SIZE_NORMAL);
        // Postal Code & City
        write(327, 660, "22300, Lannion", font, FONT_SIZE_NORMAL);

        // City & Date
        write(327, 615, "Rennes, le 23/12/2006", font, FONT_SIZE_NORMAL);

        font = PDTrueTypeFont.loadTTF(document, new File(ARIAL_BOLD));
        // Bon de commande's number
        write(325, 575, "06120000001", font, FONT_SIZE_TITLE);
    }

    /**
     * Writes the body
     */
    @Override
    protected void body() throws Exception {
        PDFont font = PDTrueTypeFont.loadTTF(document, new File(ARIAL));

        // Command date
        write(165, 548, "20/12/2006", font, FONT_SIZE_NORMAL);

        // Livraison date
        write(142, 535, "29/12/2006", font, FONT_SIZE_NORMAL);

        // Livraison adress
        write(324, 523, "1 rue de la caraffe, 22100 Morlaix", font, FONT_SIZE_NORMAL);

        // THE TABLE
        int y = 473;
        for(int i = 0; i < 5; i++) {
            // Ref
            write(57, y, "R" + i, font, FONT_SIZE_NORMAL);

            // Designation
            write(130, y, "Rouille " + i, font, FONT_SIZE_NORMAL);

            // Unit price HT
            write(217, y, "12€", font, FONT_SIZE_NORMAL);

            // Quantity
            write(290, y, "1", font, FONT_SIZE_NORMAL);

            // Total HT
            write(365, y, "12€", font, FONT_SIZE_NORMAL);

            // TVA
            write(438, y, "0%", font, FONT_SIZE_NORMAL);

            // Total TTC
            write(509, y, "12€", font, FONT_SIZE_NORMAL);

            y -= 12;
        }

        // TOTAL
        // HT
        write(365, 321, (5*12) + "€", font, FONT_SIZE_NORMAL);

        // TVA
        write(438, 321, "0%", font, FONT_SIZE_NORMAL);

        // TTC
        write(509, 321, (5*12) + "€", font, FONT_SIZE_NORMAL);
    }

    /**
     * Writes the footer
     */
    @Override
    protected void footer() throws Exception {
        PDFont font = PDTrueTypeFont.loadTTF(document, new File(ARIAL));

        // Acompte
        write(114, 260, "9%, payable à la commande par chèque (s'il y a lieu)", font, FONT_SIZE_NORMAL);

        // Days
        write(147, 237, "3 jours à réception de facture, par chèque", font, FONT_SIZE_NORMAL);

        // Penalty
        write(222, 213, "8%", font, FONT_SIZE_NORMAL);

        // Retractation
        write(166, 164, "sous 1 jour par voie postale", font, FONT_SIZE_NORMAL);
    }
}
