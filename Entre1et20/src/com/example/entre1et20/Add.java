package com.example.entre1et20;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Transaction;

/*
 * Classe servant à ajouter un gagnant dans la base de donnée des gagnants
 */
@SuppressWarnings("serial")
public class Add extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// ...
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Date date = new Date();
		
		String vName = req.getParameter("pseudo"); // Retourne forcement un String
		String vValue = req.getParameter("value"); // Retourne forcement un String
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		//Utilisation d'une transaction pour ajouter un Gagnant
		Transaction txn = datastore.beginTransaction();
		
		//Ajout d'un Gagnant dans la base de donnée contenant les gagnants
		Entity vEntiteNombre = new Entity("Winner");
		vEntiteNombre.setUnindexedProperty("name", vName); //On n'indexe pas le nom du joueur car pas besoin de le trié après = gain de performance
		vEntiteNombre.setProperty("value", vValue);
		vEntiteNombre.setProperty("date", date);
		datastore.put(vEntiteNombre);
		txn.commit();	
	}
}
