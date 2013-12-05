package com.example.entre1et20;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

@SuppressWarnings("serial")
public class Entre1et20Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	}
	
	 public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
         
		 String newNumber = req.getParameter("newNumber"); // Retourne forcement un String
		 int moyenne;
		 DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		 
		 //Comparaison avec la moyenne stocker dans la base de donnée
/*		 Query query = new Query("Moyenne");   
	     PreparedQuery pq = datastore.prepare(query);
	     Iterator<Entity> entities = pq.asIterable().iterator();
	     Entity entity = null;*/
	       
	      //Lecture de la moyenne
/*	      while(entities.hasNext()){
	    	  entity = entities.next();
	    	  moyenne =  Integer.parseInt( (String) entity.getProperty("solution") );
	       }*/
		 
		 //Si le joueur à perdu
		 
			 //On appelle une queue pour calculer la nouvelle moyenne
			Queue queue = QueueFactory.getDefaultQueue();
	        queue.add(TaskOptions.Builder.withUrl("/calcul").method(Method.POST));
	         
	        Entity nombre = new Entity("Nombre");
	 	    nombre.setProperty("number", newNumber);
	 	    datastore.put(nombre);
	     
         //Sinon
	         //On appelle une queue pour regenerer la base de donnee
         
        


         resp.sendRedirect("/");
	 }
}
