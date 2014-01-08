package com.example.entre1et20;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Restore extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// ...
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//Lecture de la base de donnée contenant les propositions !
		Query query = new Query("Nombre");   
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		PreparedQuery pq = datastore.prepare(query);
		Iterator<Entity> entities = pq.asIterable().iterator();
		Entity entity = null;
       
       //Suppression de toute la base de donnée
       while(entities.hasNext()){
                entity = entities.next();
                datastore.delete(entity.getKey());
       }
       
       //Remplissage de la base de donnée avec des nombres aléatoires
		for(int i =0; i<10; i++){
			Random r = new Random();
	        int valeur = 1 + r.nextInt(20 - 1);
	        
	        Entity vEntiteNombre = new Entity("Nombre");
			 vEntiteNombre.setProperty("number", valeur);
			 datastore.put(vEntiteNombre);
		}
		
	}

}
