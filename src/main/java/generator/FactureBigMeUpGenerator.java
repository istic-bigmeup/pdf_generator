package generator;

import java.io.File;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;

public class FactureBigMeUpGenerator extends Generator {
	private Map<String, String> facture;

	public FactureBigMeUpGenerator(String missionId) {
		super(missionId);

		try {
            PDDocument document = PDDocument.load(new File("resources/initialPDF/facture.pdf"));
            setDocument(document);
            initFonts();
            
            facture = db.getFacture(mission.get("facture"));

            setTitle("BIGMEUP");
            setSubject(facture.get("numero_facture"));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Writes the header
     */
    @Override
    protected void header() throws Exception {
        // ============ BigMeUp en tant que presta ===========
    	Map<String, String> person = db.getUser(mission.get("id_prestataire"));
        // Name
        write(57, 801, "BigMeUp", arialBold, FONT_SIZE_NORMAL);

        // City
        write(57, 783, "9 B Allée du champ du moulin", arial, FONT_SIZE_NORMAL);

        // Ville, CP
        write(57, 765, "Cesson-Sévigné, 35510", arial, FONT_SIZE_NORMAL);

        // Siret
        write(105, 747, "82276800800010", arial, FONT_SIZE_NORMAL);

        // ============ Presta en tant que client ===========
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
        
        // ===================== Le service =================
        // Le prix unitaire
        // Quantity
        double qte = Double.parseDouble(mission.get("quantite"));
        write(55, y, qte + "", arial, FONT_SIZE_NORMAL);

        // Designation
        write(130, y, mission.get("objet"), arial, FONT_SIZE_NORMAL);

        // Unit's price TTC
        double puht = Double.parseDouble(mission.get("prix_unitaire_ht"));
        write(330, y, puht + " €", arial, FONT_SIZE_NORMAL);

        // Total price TTC
        double total_price = (qte * puht)*0.15;
        write(445, y, total_price + " € (15%)", arial, FONT_SIZE_NORMAL);
        
        // ==================== Les frais ===================
        // ============ Les frais annexes ==================
        double frais_annexes = Double.parseDouble(mission.get("autres_frais"));
        y -= 10;
        if(frais_annexes > 0){
	        // Quantities
	        write(55, y, "1", arial, FONT_SIZE_NORMAL);

	        // Products
	        write(130, y, "Frais annexes", arial, FONT_SIZE_NORMAL);
	
	        // Unit's price
	        write(330, y, frais_annexes + " €", arial, FONT_SIZE_NORMAL);
	
	        // Total price
	        frais_annexes *= 0.15;
	        total_price += frais_annexes;
	        write(445, y, frais_annexes + " € (15%)", arial, FONT_SIZE_NORMAL);
        }

        // TOTAL TTC
        // Unit's price TTC
        write(330, 235, total_price + " €", arial, FONT_SIZE_NORMAL);

        // Total price TTC
        write(445, 235, total_price + " €", arial, FONT_SIZE_NORMAL);
    }

    /**
     * Writes the footer
     */
    @Override
    protected void footer() throws Exception {
    }
}
