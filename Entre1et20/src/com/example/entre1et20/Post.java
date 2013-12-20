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
public class Post extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	}
	
	 public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
         
		 String vStrNumber = req.getParameter("newNumber"); // Retourne forcement un String
		 int vNombre = Integer.parseInt(vStrNumber);
		 long vMoyenne = 0;
		 
		 if(vNombre < 1 || vNombre > 20 ){    
		        try {
		                req.setAttribute("titre", "Merci d'entré un nombre entre 1 et 20");
		                getServletContext().getRequestDispatcher("/Content.jsp").forward(req, resp);
		        } catch (ServletException e) {
		                e.printStackTrace();
		        }
		} else{
		 
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
		      
		      
		      if((long) vNombre == vMoyenne){ //Le joueur gagne
		    	  resp.sendRedirect("win.html");
		      } else{ // Le joueur perd
		    	  Queue queue = QueueFactory.getDefaultQueue();
				  queue.add(TaskOptions.Builder.withUrl("/calcul").method(Method.POST));
				  
				  //Envoi des résultats à la JSP
			        try {
			                req.setAttribute("titre", "Perdu !");
			                getServletContext().getRequestDispatcher("/Content.jsp").forward(req, resp);
			        } catch (ServletException e) {
			                e.printStackTrace();
			        }
		      }   // if-else  
		} // else condition vNombre
	 }
}
