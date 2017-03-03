package generator;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.*;
import java.util.Map;

/**
 * Created by j√©r√©my on 10/02/2017.
 *
 */
public class DevisGenerator extends Generator {
	private Map<String, String> devis;
	
    /**
     * The constructor
     * @param title     The title
     * @param subject   The subject
     */
    public DevisGenerator(String missionId) {
        super(missionId);

        try {
            PDDocument document = PDDocument.load(new File("resources/devis.pdf"));
            setDocument(document);
            initFonts();
            
            devis = db.getDevis(mission.get("devis"));
            
            setTitle("Devis");
            setSubject(devis.get("numero_devis"));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Writes the header
     */
    @Override
    protected void header() throws Exception {
    	Map<String, String> person = db.getUser(mission.get("id_prestataire"));
        // Phone number
        write(100, 720, person.get("telephone"), arial, FONT_SIZE_NORMAL);

        // Email
        write(78, 697, person.get("email"), arial, FONT_SIZE_NORMAL);

        // N SIREN
        write(98, 677, person.get("siret"), arial, FONT_SIZE_NORMAL);

        // Date
        write(348, 576, devis.get("date_devis"), arial, FONT_SIZE_NORMAL);

        // Devis number
        write(340, 533, devis.get("numero_devis"), arialBold, FONT_SIZE_BIG_TITLE);
    }

    /**
     * Writes the body
     */
    @Override
    protected void body() throws Exception {
        int y = 470;

        // Products
        write(61, y, mission.get("objet"), arial, FONT_SIZE_NORMAL);

        // Quantities
        write(342, y, mission.get("quantite"), arial, FONT_SIZE_NORMAL);

        // Unit's price
        write(403, y, mission.get("prix_unitaire_ht") + " Ä", arial, FONT_SIZE_NORMAL);

        // Total price
        double total_price = Double.parseDouble(mission.get("quantite")) * Double.parseDouble(mission.get("prix_unitaire_ht"));
        write(475, y, total_price + " Ä", arial, FONT_SIZE_NORMAL);

        // SALE CONDITION
        // Modalities
        write(175, 331, "Carte bancaire", arialBold, FONT_SIZE_NORMAL);

        // Pay date
        write(152, 311, devis.get("date_paiement"), arial, FONT_SIZE_NORMAL);

        // Late penalty
        write(157, 290, devis.get("penalite_retard") + " Ä", arial, FONT_SIZE_NORMAL);

        // LIV CONDITION
        // Liv date
        write(145, 240, "15/11/2016", arial, FONT_SIZE_NORMAL);

        // Modalities
        write(166, 210, "Voie postale", arial, FONT_SIZE_NORMAL);

        // TOTAL (HT)
        write(475, 163, total_price + " Ä", arial, FONT_SIZE_NORMAL);
    }

    /**
     * Writes the footer
     */
    @Override
    protected void footer() throws Exception {
    }
}
