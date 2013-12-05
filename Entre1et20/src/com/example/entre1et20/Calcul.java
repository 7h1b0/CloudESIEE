package com.example.entre1et20;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Calcul extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// ...
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int k=0;
		int somme = 0;
		
		//Lecture de la base de donnée contenant les propositions !
		Query query = new Query("Nombre");   
       DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
       PreparedQuery pq = datastore.prepare(query);
       Iterator<Entity> entities = pq.asIterable().iterator();
       Entity entity = null;
       
       //Lecture des résultats et traitement
       while(entities.hasNext()){
                entity = entities.next();
                somme += Integer.parseInt( (String)entity.getProperty("number")); // Marche
                k++;
       }
       
       //Calcul de la moyenne
       int moyenne = somme / k ; // Comme c'est un int, on fait une division entiere;
		
       //Ajout dans la base de donnée
       Entity nombre = new Entity("Moyenne");
	  
       //Suppression -- marche pas
       //Key nombreKey = nombre.getKey();
	   //datastore.delete(nombreKey);
	   
	   //Ajout
	   nombre.setProperty("solution", somme);
	   datastore.put(nombre);
	}
	
}
