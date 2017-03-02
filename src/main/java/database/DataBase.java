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
	private final int ID_DOCUMENTS 			= 3;
	private final int ID_FACTURES 			= 4;
	private final int ID_MISSIONS 			= 5;
	private final int ID_USERS 				= 6;
	
	private DB db;
	/**
	 * The constructor
	 * 
	 * @param host	The host's name
	 */
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
									"telephone"
								};
		
		// Initializes the query
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		// Initializes the fields
		BasicDBObject field = new BasicDBObject();
		field.put(field_names[0], 0);
		field.put(field_names[1], 1);
		field.put(field_names[2], 2);
		field.put(field_names[3], 4);
		field.put(field_names[4], 11);
		field.put(field_names[5], 9);
		
		DBCursor cursor = col.find(query, field);
		while(cursor.hasNext()){
			BasicDBObject object = (BasicDBObject) cursor.next();
			
			for(int i = 0; i < field_names.length; i++){
				res.put	(field_names[i], object.getString(field_names[i]));
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
									"modalite_paiement",
									"date_paiement",
									"penalite_retard",
									"validite_devis",
									"remise",
									"id_missions"
								};
		
		// Initializes the query
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		// Initializes the fields
		BasicDBObject field = new BasicDBObject();
		field.put(field_names[0], 0);
		field.put(field_names[1], 1);
		field.put(field_names[2], 2);
		field.put(field_names[3], 3);
		field.put(field_names[4], 4);
		field.put(field_names[5], 5);
		field.put(field_names[6], 8);
		field.put(field_names[7], 9);
		field.put(field_names[8], 11);
		
		DBCursor cursor = col.find(query, field);
		while(cursor.hasNext()){
			BasicDBObject object = (BasicDBObject) cursor.next();
			
			for(int i = 0; i < field_names.length; i++){
				res.put	(field_names[i], object.getString(field_names[i]));
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
									"id_missions"
								};
				
		// Initializes the query
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		// Initializes the fields
		BasicDBObject field = new BasicDBObject();
		field.put(field_names[0], 0);
		field.put(field_names[1], 1);
		field.put(field_names[2], 2);
		field.put(field_names[3], 3);
		field.put(field_names[4], 4);
		field.put(field_names[5], 6);
		
		DBCursor cursor = col.find(query, field);
		while(cursor.hasNext()){
			BasicDBObject object = (BasicDBObject) cursor.next();
			
			for(int i = 0; i < field_names.length; i++){
				res.put	(field_names[i], object.getString(field_names[i]));
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
								"lieu_mission"};
		
		// Initializes the query
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		
		// Initializes the fields
		BasicDBObject field = new BasicDBObject();
		field.put(field_names[0], 0);
		field.put(field_names[1], 1);
		field.put(field_names[2], 2);
		field.put(field_names[3], 4);
		field.put(field_names[4], 5);
		field.put(field_names[5], 6);
		field.put(field_names[6], 7);
		field.put(field_names[7], 8);
		field.put(field_names[8], 9);
		field.put(field_names[9], 10);
		
		// Storing to the res map
		DBCursor cursor = col.find(query, field);
		while(cursor.hasNext()){
			BasicDBObject object = (BasicDBObject) cursor.next();
			
			for(int i = 0; i < field_names.length; i++){
				res.put	(field_names[i], object.getString(field_names[i]));
			}
		}
		
		// Returning the res map
		return res;
	}
}
