package com.example.entre1et20;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Key;

public class Calcul extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// ...
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int k=0;
		int vSomme = 0;
		
		//Lecture de la base de donnée contenant les propositions !
		Query query = new Query("Nombre");   
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		PreparedQuery pq = datastore.prepare(query);
		Iterator<Entity> entities = pq.asIterable().iterator();
		Entity entity = null;
       
       //Lecture des résultats et traitement
       while(entities.hasNext()){
                entity = entities.next();
                vSomme += Integer.parseInt( (String)entity.getProperty("number")); // Marche
                k++;
       }
       System.out.println(k+ " " +vSomme);
       
       //Calcul de la moyenne
       int vMoyenne = vSomme / k ; // Comme c'est un int, on fait une division entiere;		   
	  
       //Récupération de l'entité contenant la moyenne
       Key moyenneKey = KeyFactory.createKey("Moyenne", "id");
       
       //Mise a jour de la valeur Moyenne
       Entity moyenneDB = new Entity("Moyenne","id", moyenneKey);
	   moyenneDB.setProperty("solution", vMoyenne);
	   datastore.put(moyenneDB);	   
	}
}
