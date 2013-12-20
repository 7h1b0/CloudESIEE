package com.example.entre1et20;

import java.io.IOException;
import java.util.Iterator;

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
public class Post extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	}
	
	 public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
         
		 String vStrNumber = req.getParameter("newNumber"); // Retourne forcement un String
		 long vMoyenne = 0;
		 int vNombre;
		 DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		 
		 //On place la proposition dans la DB AVANT d'appeler la queue -- Pourtant ça pose probleme ...
		 Entity vEntiteNombre = new Entity("Nombre");
		 vEntiteNombre.setProperty("number", vStrNumber);
		 datastore.put(vEntiteNombre);
		 
		 //Preparation de la requete
		 Query query = new Query("Moyenne");   
	     PreparedQuery pq = datastore.prepare(query);
	     Iterator<Entity> entities = pq.asIterable().iterator();
	     Entity entity = null;
	       
	      //Lecture de la moyenne
	      while(entities.hasNext()){
	    	  entity = entities.next();
	    	  vMoyenne = (long) entity.getProperty("solution");
	       }
	      vNombre = Integer.parseInt(vStrNumber);
	      
	      if((long) vNombre == vMoyenne){ //Le joueur gagne
	    	  resp.sendRedirect("win.html");
	      } else{ // Le joueur perd
	    	  Queue queue = QueueFactory.getDefaultQueue();
			  queue.add(TaskOptions.Builder.withUrl("/calcul").method(Method.POST));
			  
			  resp.sendRedirect("lose.html");
	      }   // if-else    
	 }
}
