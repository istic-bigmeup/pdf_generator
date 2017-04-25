package generator;

import java.io.File;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;

public class FactureBigMeUpGenerator extends Generator {
	private Map<String, String> facture;

	public FactureBigMeUpGenerator(String missionId) {
		super(missionId);

		try {
            PDDocument document = PDDocument.load(new File("resources/initialPDF/facture_bigmeup.pdf"));
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
        write(57, 801, person.get("nom") + " " + person.get("prenom"), arialBold, FONT_SIZE_NORMAL);

        // City
        write(57, 783, person.get("adresse"), arial, FONT_SIZE_NORMAL);

        // Ville, CP
        write(57, 765, person.get("ville") + ", " + person.get("codePostal"), arial, FONT_SIZE_NORMAL);

        // Siret
        write(105, 747, person.get("numero_siret"), arial, FONT_SIZE_NORMAL);

        // ============ Presta en tant que client ===========
        // Name
        write(327, 803, "BigMeUp", arialBold, FONT_SIZE_NORMAL);

        // City
        write(327, 786, "9 B Allée du champ du moulin", arial, FONT_SIZE_NORMAL);

        // Ville, CP
        write(327, 768, "Cesson-Sévigné, 35510", arial, FONT_SIZE_NORMAL);

        // Siret
        write(377, 751, "82276800800010", arial, FONT_SIZE_NORMAL);

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
        write(285, 644, facture.get("numero_facture"), arialBold, FONT_SIZE_BIG_TITLE);

        // TABLE
        int y = 573;
        
        // ===================== La commission BigMeUp =================
        // Le prix unitaire
        // Quantity
        write(55, y-5, "1", arial, FONT_SIZE_NORMAL);

        // Designation
        double puht = 	( 	( 
				Double.parseDouble(mission.get("prix_unitaire_ht")) 
				* 
				Double.parseDouble(mission.get("quantite")) 
			) 
			+ 
			Double.parseDouble(mission.get("autres_frais"))
		);
        write(130, y, "Commission BigMeUp", arial, FONT_SIZE_NORMAL);
        write(130, y-10, numberFormat.format(puht) + " € x 15%", arial, FONT_SIZE_NORMAL);

        // Unit's price TTC
        puht *= 0.15;
        write(330, y-5, numberFormat.format(puht) + " €", arial, FONT_SIZE_NORMAL);

        // Total price TTC
        double total_price = puht;
        write(445, y-5, numberFormat.format(total_price) + " €", arial, FONT_SIZE_NORMAL);
        
        // ==================== La réduction FrenchTech ===================
        Map<String, String> person = db.getUser(mission.get("id_prestataire"));
        y -= 30;
        if(person.get("french_tech").equals("1")){
        	// Calcul de la réduc si c'est un bonhomme de la FrenchTech
            double reduc = ( 	( 
    								Double.parseDouble(mission.get("prix_unitaire_ht")) 
    								* 
    								Double.parseDouble(mission.get("quantite")) 
    							) 
    							+ 
    							Double.parseDouble(mission.get("autres_frais"))
    						) * 0.075;
            
	        // Quantities
	        write(55, y-5, "1", arial, FONT_SIZE_NORMAL);

	        // Products
	        write(130, y, "Réduction membre FrenchTech", arial, FONT_SIZE_NORMAL);
	        write(130, y-10, "Rennes Saint-Malo (-50%)", arial, FONT_SIZE_NORMAL);
	
	        // Unit's price
	        write(330, y-5, "-" + numberFormat.format(reduc) + " €", arial, FONT_SIZE_NORMAL);
	
	        // Total price
	        total_price -= reduc;
	        write(445, y-5, "-" + numberFormat.format(reduc) + " €", arial, FONT_SIZE_NORMAL);
        }

        // Total price TTC
        write(445, 235, numberFormat.format(total_price) + " €", arial, FONT_SIZE_NORMAL);

        // TVA
        write(445, 203, numberFormat.format(total_price * 0.195) + "€", arial, FONT_SIZE_NORMAL);

        // Total HT
        write(445, 172, numberFormat.format(total_price * 0.805) + " €", arial, FONT_SIZE_NORMAL);
    }

    /**
     * Writes the footer
     */
    @Override
    protected void footer() throws Exception {
    }
}
