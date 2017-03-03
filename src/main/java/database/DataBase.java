package database;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class DataBase {
	private final String[] COLLECTION_NAMES = {"annonces", "bons_de_commandes", "devis", "documents", "factures", "missions", "users"};
	
	private final int ID_DEVIS 				= 2;
	private final int ID_FACTURES 			= 4;
	private final int ID_MISSIONS 			= 5;
	private final int ID_USERS 				= 6;
	
	private DB db;
	/**
	 * The constructor
	 * 
	 * @param host	The host's name
	 */
	@SuppressWarnings({ "deprecation", "resource" })
	public DataBase(String host){
		try{
			MongoClient mongoClient= new MongoClient(host);
			
			db = mongoClient.getDB("bigmeup");
		} catch(Exception e){
			System.out.println("Error in the db connection");
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the user having the right ID
	 * 
	 * @param id	The id
	 * 
	 * @return	Map	The result map
	 */
	public Map<String, String> getUser(String id){
		// Initializes the vars
		Map<String, String> res = new HashMap<String, String>();
		DBCollection col = db.getCollection(COLLECTION_NAMES[ID_USERS]);
		
		String[] field_names = 	{	"_id",
									"nom",
									"prenom",
									"email",
									"adresse",
									"telephone",
									"nom_entreprise",
									"siret",
									"numero_tva"
								};
		
		// Initializes the query
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		// Initializes the fields
		BasicDBObject field = new BasicDBObject();
		field.put(field_names[0], 0);//_id
		field.put(field_names[1], 1);//nom
		field.put(field_names[2], 2);//prenom
		field.put(field_names[3], 4);//email
		field.put(field_names[4], 14);//adresse
		field.put(field_names[5], 9);//telephone
		field.put(field_names[6], 10);//nom entreprise
		field.put(field_names[7], 13);//siret
		field.put(field_names[8], 15);//numero tva
		
		DBCursor cursor = col.find(query, field);
		while(cursor.hasNext()){
			BasicDBObject object = (BasicDBObject) cursor.next();
			
			for(int i = 0; i < field_names.length; i++){
				res.put	(field_names[i], object.getString(field_names[i]) == null ? "" : object.getString(field_names[i]));
			}
		}
		
		return res;
	}
	
	/**
	 * Get the devis having the right ID
	 * 
	 * @param id	The id
	 * 
	 * @return	Map	The result map
	 */
	public Map<String, String> getDevis(String id){
		// Initializes the vars
		Map<String, String> res = new HashMap<String, String>();
		DBCollection col = db.getCollection(COLLECTION_NAMES[ID_DEVIS]);
		
		String[] field_names = 	{	"_id",
									"numero_devis",
									"date_devis",
									"date_paiement",
									"penalite_retard",
									"validite_devis",
									"url"
								};
		
		// Initializes the query
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		// Initializes the fields
		BasicDBObject field = new BasicDBObject();
		field.put(field_names[0], 0);//_id
		field.put(field_names[1], 1);//numero_devis
		field.put(field_names[2], 2);//date_devis
		field.put(field_names[3], 3);//date_paiement
		field.put(field_names[5], 4);//penalite_retard
		field.put(field_names[5], 5);//validite_devis
		field.put(field_names[6], 6);//url
		
		DBCursor cursor = col.find(query, field);
		while(cursor.hasNext()){
			BasicDBObject object = (BasicDBObject) cursor.next();
			
			for(int i = 0; i < field_names.length; i++){
				res.put	(field_names[i], object.getString(field_names[i]) == null ? "" : object.getString(field_names[i]));
			}
		}
		
		return res;
	}

	
	/**
	 * Get the facture having the right ID
	 * 
	 * @param id	The id
	 * 
	 * @return	Map	The result map	
	 */
	public Map<String, String> getFacture(String id){
		// Initializes the vars
		Map<String, String> res = new HashMap<String, String>();
		DBCollection col = db.getCollection(COLLECTION_NAMES[ID_FACTURES]);
		
		String[] field_names = 	{	"_id",
									"numero_facture",
									"date_facture",
									"date_prestation",
									"tva",
									"url"
								};
				
		// Initializes the query
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		// Initializes the fields
		BasicDBObject field = new BasicDBObject();
		field.put(field_names[0], 0);//_id
		field.put(field_names[1], 1);//numero_facture
		field.put(field_names[2], 2);//date_facture
		field.put(field_names[3], 3);//date_prestation
		field.put(field_names[4], 4);//tva
		field.put(field_names[5], 6);//url
		
		DBCursor cursor = col.find(query, field);
		while(cursor.hasNext()){
			BasicDBObject object = (BasicDBObject) cursor.next();
			
			for(int i = 0; i < field_names.length; i++){
				res.put	(field_names[i], object.getString(field_names[i]) == null ? "" : object.getString(field_names[i]));
			}
		}
		
		return res;
	}
	
	/**
	 * Get the mission having the right ID
	 * 
	 * @param 	id	The id
	 * 
	 * @return	Map	The result map	
	 */
	public Map<String, String> getMission(String id){
		// Initializes the vars
		Map<String, String> res = new HashMap<String, String>();
		DBCollection col = db.getCollection(COLLECTION_NAMES[ID_MISSIONS]);
		
		String[] field_names = {"_id",
								"id_prestataire",
								"id_client",
								"objet",
								"prix_unitaire_ht",
								"quantite",
								"date_debut",
								"date_fin",
								"clauses",
								"lieu_mission",
								"facture",
								"devis"};
		
		// Initializes the query
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		// Initializes the fields
		BasicDBObject field = new BasicDBObject();
		field.put(field_names[0], 0);//_id
		field.put(field_names[1], 1);//id_prestataire
		field.put(field_names[2], 2);//id_cliet
		field.put(field_names[3], 3);//objet
		field.put(field_names[4], 4);//pu ht
		field.put(field_names[5], 5);//quantite
		field.put(field_names[6], 6);//date debut
		field.put(field_names[7], 7);//date fin
		field.put(field_names[8], 8);//clauses
		field.put(field_names[9], 9);//lieu mission
		field.put(field_names[10], 14);//facture
		field.put(field_names[11], 15);//devis
		
		// Storing to the res map
		DBCursor cursor = col.find(query, field);
		while(cursor.hasNext()){
			BasicDBObject object = (BasicDBObject) cursor.next();
			
			for(int i = 0; i < field_names.length; i++){
				res.put	(field_names[i], object.getString(field_names[i]) == null ? "" : object.getString(field_names[i]));
			}
		}
		
		// Returning the res map
		return res;
	}
}
