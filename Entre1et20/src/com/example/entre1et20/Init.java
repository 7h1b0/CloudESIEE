package com.example.entre1et20;

import java.io.IOException;
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

public class Init extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		//Remplissage de la base de donnée avec des nombres aléatoires
		for(int i =0; i<10; i++){
			Random r = new Random();
	        int valeur = 1 + r.nextInt(20 - 1);
	        
	        Entity vEntiteNombre = new Entity("Nombre");
			 vEntiteNombre.setProperty("number", valeur);
			 datastore.put(vEntiteNombre);
		}
		
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(TaskOptions.Builder.withUrl("/calcul").method(Method.POST)); // Appel de la queue qui va re-calculer la moyenne
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//..
	}
}
