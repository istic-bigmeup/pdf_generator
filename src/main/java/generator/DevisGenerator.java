package generator;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.*;
import java.util.ArrayList;
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
            PDDocument document = PDDocument.load(new File("resources/initialPDF/devis.pdf"));
            setDocument(document);
            initFonts();
            
            devis = db.getDevis(mission.get("devis"));
            
            setTitle("Devis");
            setSubject("DEV" + devis.get("numero_devis"));
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
        write(380, 640, person.get("nom_entreprise"), arial, FONT_SIZE_NORMAL);

        // N SIREN
        write(375, 617, person.get("numero_siret"), arial, FONT_SIZE_NORMAL);

        // Devis
        
        // Date
        write(348, 546, devis.get("date_devis"), arial, FONT_SIZE_NORMAL);

        // Devis number
        write(340, 503, devis.get("numero_devis"), arialBold, FONT_SIZE_BIG_TITLE);
    }

    /**
     * Writes the body
     */
    @Override
    protected void body() throws Exception {
        int y = 440;
        
        double total_price	= 0;

        // ============= Le service ====================
        // Products
        write(61, y, mission.get("objet"), arial, FONT_SIZE_NORMAL);

        // Quantities
        write(342, y, mission.get("quantite"), arial, FONT_SIZE_NORMAL);

        // Unit's price
        write(403, y, mission.get("prix_unitaire_ht") + " Ä", arial, FONT_SIZE_NORMAL);

        // Total price
        double prix_ht 	 = Double.parseDouble(mission.get("quantite")) * Double.parseDouble(mission.get("prix_unitaire_ht"));
        total_price 	+= prix_ht;
        write(475, y, prix_ht + " Ä", arial, FONT_SIZE_NORMAL);
        
        // ============ Les frais annexes ==================
        double frais_annexes = Double.parseDouble(mission.get("autres_frais"));
        y -= 10;
        if(frais_annexes > 0){
	        // Products
	        write(61, y, "Frais annexes", arial, FONT_SIZE_NORMAL);
	
	        // Quantities
	        write(342, y, "1", arial, FONT_SIZE_NORMAL);
	
	        // Unit's price
	        write(403, y, frais_annexes + " Ä", arial, FONT_SIZE_NORMAL);
	
	        // Total price
	        total_price += frais_annexes;
	        write(475, y, frais_annexes + " Ä", arial, FONT_SIZE_NORMAL);
        }

        // SALE CONDITION
        // Clauses
        y 							= 358;
        
        int nbLignesMax 			= 23;
        int nbLignesTot 			= 0;
        
        String[] c 					= mission.get("clauses").split("\n");
        ArrayList<String> clauses 	= new ArrayList<String>(); 
        
        // On coupe les clauses trop longues
        for(int i = 0; i < c.length; i++){
        	// On prend le nombre de lignes gÈnÈrÈes par la ligne courante
        	int nbLignes = (c[i].length() / 70) + 1;
        	
        	// On l'ajoute au total de lignes
        	nbLignesTot += nbLignes;
        	
        	// Si le nombre de lignes totales est supÈrieur au nombre de lignes max..
        	if(nbLignesTot >= nbLignesMax){
        		// ..on enlËve les lignes en trop
        		nbLignes -= nbLignesTot - nbLignesMax;
        	}
        	
    		for(int j = 0; j < nbLignes; j++){
    			if(c[i].length() < (j*70) + 71){// Si on sort du tableau de caractËres
    				clauses.add(c[i].substring((j == 0 ? j*70 : (j*70+1)), c[i].length()));
    			} else {// Sinon
    				clauses.add(c[i].substring((j == 0 ? j*70 : (j*70+1)), (j*70) + 71));
    			}
    		}
    	}
        
        for(String clause: clauses){
        	write(61, y, clause, arial, FONT_SIZE_CLAUSE);
        	y -= 7;
        }

        // Date de dÈbut
        write(127, 185, mission.get("date_debut"), arial, FONT_SIZE_NORMAL);

        // Date de fin
        write(115, 164, mission.get("date_fin"), arial, FONT_SIZE_NORMAL);

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
