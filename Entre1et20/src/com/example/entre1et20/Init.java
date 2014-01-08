package com.example.entre1et20;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;


/*
 * Classe qui sert à initialiser les bases de données pour que fonctionne correctement
 * 
 */
public class Init extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		//Remplissage de la base de donnée avec des nombres aléatoires
		for(int i =0; i<10; i++){
			Random r = new Random();
	        int nombre = 1 + r.nextInt(20 - 1);
	        Date date = new Date();
	        
	        Entity vEntiteNombre = new Entity("Nombre");
			 vEntiteNombre.setProperty("number", nombre);
			 vEntiteNombre.setProperty("date", date);
			 datastore.put(vEntiteNombre);
		}
		
		// Appel de la queue qui va calculer la moyenne des nombres générés précédements
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(TaskOptions.Builder.withUrl("/calcul").method(Method.POST)); 
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//..
	}
}
