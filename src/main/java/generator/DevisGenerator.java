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
    	// =============== Prestataire ===================
    	Map<String, String> person = db.getUser(mission.get("id_prestataire"));
        // Phone number
        write(98, 707, person.get("telephone"), arial, FONT_SIZE_NORMAL);

        // Email
        write(78, 694, person.get("email"), arial, FONT_SIZE_NORMAL);

        // N SIREN
        write(95, 673, person.get("numero_siret"), arial, FONT_SIZE_NORMAL);
        
        // ============= Client =========================
        person = db.getUser(mission.get("id_client"));
        // Phone number
        write(375, 652, person.get("telephone"), arial, FONT_SIZE_NORMAL);

        // Email
        write(360, 640, person.get("email"), arial, FONT_SIZE_NORMAL);

        // N SIREN
        write(375, 617, person.get("numero_siret"), arial, FONT_SIZE_NORMAL);

        // Devis
        
        // Date
        write(348, 545, devis.get("date_devis"), arial, FONT_SIZE_NORMAL);

        // Devis number
        write(340, 503, devis.get("numero_devis"), arialBold, FONT_SIZE_BIG_TITLE);
    }

    /**
     * Writes the body
     */
    @Override
    protected void body() throws Exception {
        int y = 440;

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
        // Clauses
        write(61, 277, mission.get("clauses"), arial, FONT_SIZE_NORMAL);

        // Date de dÈbut
        write(127, 188, mission.get("date_debut"), arial, FONT_SIZE_NORMAL);

        // Date de fin
        write(115, 167, mission.get("date_fin") + " Ä", arial, FONT_SIZE_NORMAL);

        // TOTAL (HT)
        write(475, 118, total_price + " Ä", arial, FONT_SIZE_NORMAL);
    }

    /**
     * Writes the footer
     */
    @Override
    protected void footer() throws Exception {
    }
}
