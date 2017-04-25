package generator;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.util.ArrayList;
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
            PDDocument document = PDDocument.load(new File("resources/initialPDF/facture.pdf"));
            setDocument(document);
            initFonts();
            
            facture = db.getFacture(mission.get("facture"));

            setTitle("FAC");
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
        // ============ PRESTA ===========
    	Map<String, String> person = db.getUser(mission.get("id_prestataire"));
    	
        // Name
        write(57, 772, person.get("nom") + " " + person.get("prenom"), arialBold, FONT_SIZE_NORMAL);

        // City
        write(57, 754, person.get("adresse"), arial, FONT_SIZE_NORMAL);

        // Ville, CP
        write(57, 736, person.get("ville") + ", " + person.get("codePostal"), arial, FONT_SIZE_NORMAL);

        // Siret
        //write(105, 747, person.get("numero_siret"), arial, FONT_SIZE_NORMAL);
        write(105, 718, person.get("numero_siret"), arial, FONT_SIZE_NORMAL);

        // ============ CUSTOMER ===========
        person = db.getUser(mission.get("id_client"));
        
        // Name
        write(327, 772, person.get("nom_entreprise"), arialBold, FONT_SIZE_NORMAL);

        // City
        write(327, 754, person.get("adresse"), arial, FONT_SIZE_NORMAL);

        // Ville, CP
        write(327, 736, person.get("ville") + ", " + person.get("codePostal"), arial, FONT_SIZE_NORMAL);

        // Siret
        write(378, 721, person.get("numero_siret"), arial, FONT_SIZE_NORMAL);

        // ================= Facture ==================
        // Date
        write(91, 673, facture.get("date_facture"), arial, FONT_SIZE_NORMAL);
    }

    /**
     * Writes the body
     */
    @Override
    protected void body() throws Exception {
        // Facture's number
        write(304, 615, facture.get("numero_facture"), arialBold, FONT_SIZE_BIG_TITLE);

        // TABLE
        int y = 544;
        
        // ===================== Le service =================
        // Le prix unitaire
        // Quantity
        write(55, y, mission.get("quantite"), arial, FONT_SIZE_NORMAL);

        // Unit's price TTC
        write(330, y, numberFormat.format(Double.parseDouble(mission.get("prix_unitaire_ht"))) + " Ä", arial, FONT_SIZE_NORMAL);

        // Total price TTC
        double total_price = Double.parseDouble(mission.get("quantite")) * Double.parseDouble(mission.get("prix_unitaire_ht"));
        write(445, y, numberFormat.format(total_price) + " Ä", arial, FONT_SIZE_NORMAL);
        
        // Designation
        int nbLignesMax 			= 23;
        int nbLignesTot 			= 0;
        
        String c 					= mission.get("objet");
        ArrayList<String> dsn 		= new ArrayList<String>(); 
        
    	// On prend le nombre de lignes gÈnÈrÈes par la ligne courante
    	int nbLignes = (c.length() / 35) + 1;
    	
    	// On l'ajoute au total de lignes
    	nbLignesTot += nbLignes;
    	
    	// Si le nombre de lignes totales est supÈrieur au nombre de lignes max..
    	if(nbLignesTot >= nbLignesMax){
    		// ..on enlËve les lignes en trop
    		nbLignes -= nbLignesTot - nbLignesMax;
    	}
    	
		for(int j = 0; j < nbLignes; j++){
			if(c.length() < (j*35) + 36){// Si on sort du tableau de caractËres
				dsn.add(c.substring((j == 0 ? j*35 : (j*35+1)), c.length()));
			} else {// Sinon
				dsn.add(c.substring((j == 0 ? j*35 : (j*35+1)), (j*35) + 36));
			}
		}

        for(String d: dsn){
        	write(130, y, d, arial, FONT_SIZE_NORMAL);
        	y -= 10;
        }
        
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
	        write(330, y, numberFormat.format(frais_annexes) + " Ä", arial, FONT_SIZE_NORMAL);
	
	        // Total price
	        total_price += frais_annexes;
	        write(445, y, numberFormat.format(frais_annexes) + " Ä", arial, FONT_SIZE_NORMAL);
        }

        // Total price TTC
        write(445, 206, numberFormat.format(total_price) + " Ä", arial, FONT_SIZE_NORMAL);
    }

    /**
     * Writes the footer
     */
    @Override
    protected void footer() throws Exception {
    }
}
