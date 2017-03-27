package generator;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.util.Map;

/**
 * Created by j√©r√©my on 10/02/2017.
 *
 */
public class FactureGenerator extends Generator {
	private Map<String, String> facture;
	
    /**
     * The constructor
     * @param title     The title
     * @param subject   The subject
     */
    public FactureGenerator(String missionId) {
        super(missionId);

        try {
            PDDocument document = PDDocument.load(new File("resources/facture.pdf"));
            setDocument(document);
            initFonts();
            
            facture = db.getFacture(mission.get("facture"));

            setTitle("Facture");
            setSubject("FAC" + facture.get("numero_facture"));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Writes the header
     */
    @Override
    protected void header() throws Exception {
        // ============ PRESTA ===========
    	Map<String, String> person = db.getUser(mission.get("id_prestataire"));
        // Name
        write(57, 801, person.get("nom") + " " + person.get("prenom"), arialBold, FONT_SIZE_NORMAL);

        // City
        write(57, 783, person.get("adresse"), arial, FONT_SIZE_NORMAL);

        // Ville, CP
        write(57, 765, person.get("ville") + ", " + person.get("codePostal"), arial, FONT_SIZE_NORMAL);

        // Siret
        write(105, 747, person.get("numero_siret"), arial, FONT_SIZE_NORMAL);

        // ============ CUSTOMER ===========
        person = db.getUser(mission.get("id_client"));
        // Name
        write(327, 803, person.get("nom") + " " + person.get("prenom"), arialBold, FONT_SIZE_NORMAL);

        // City
        write(327, 786, person.get("adresse"), arial, FONT_SIZE_NORMAL);

        // Ville, CP
        write(327, 768, person.get("ville") + ", " + person.get("codePostal"), arial, FONT_SIZE_NORMAL);

        // Siret
        write(377, 751, person.get("numero_siret"), arial, FONT_SIZE_NORMAL);

        // ================= Facture ==================
        // Date
        write(91, 702, facture.get("date_facture"), arial, FONT_SIZE_NORMAL);
    }

    /**
     * Writes the body
     */
    @Override
    protected void body() throws Exception {
        // Facture's number
        write(300, 644, facture.get("numero_facture"), arialBold, FONT_SIZE_BIG_TITLE);

        // TABLE
        int y = 573;
        
        // Quantity
        write(55, y, mission.get("quantite"), arial, FONT_SIZE_NORMAL);

        // Designation
        write(130, y, mission.get("objet"), arial, FONT_SIZE_NORMAL);

        // Unit's price TTC
        write(330, y, mission.get("prix_unitaire_ht") + " Ä", arial, FONT_SIZE_NORMAL);

        // Total price TTC
        double total_price = Double.parseDouble(mission.get("quantite")) * Double.parseDouble(mission.get("prix_unitaire_ht"));
        write(445, y, total_price + " Ä", arial, FONT_SIZE_NORMAL);

        // TOTAL TTC
        // Unit's price TTC
        write(330, 235, total_price + " Ä", arial, FONT_SIZE_NORMAL);

        // Total price TTC
        write(445, 235, total_price + " Ä", arial, FONT_SIZE_NORMAL);
    }

    /**
     * Writes the footer
     */
    @Override
    protected void footer() throws Exception {
    }
}
