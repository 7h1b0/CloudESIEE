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
 * Classe qui sert � initialiser les bases de donn�es pour que fonctionne correctement
 * 
 */
public class Init extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		//Remplissage de la base de donn�e avec des nombres al�atoires
		for(int i =0; i<10; i++){
			Random r = new Random();
	        int nombre = 1 + r.nextInt(20 - 1);
	        Date date = new Date();
	        
	        Entity vEntiteNombre = new Entity("Nombre");
			 vEntiteNombre.setUnindexedProperty("number", nombre); //On n'indexe pas vNombre car pas besoin de le tri� apr�s = gain de performance
			 vEntiteNombre.setProperty("date", date);
			 datastore.put(vEntiteNombre);
		}
		
		// Appel de la queue qui va calculer la moyenne des nombres g�n�r�s pr�c�dements
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(TaskOptions.Builder.withUrl("/calcul").method(Method.POST)); 
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//..
	}
}
