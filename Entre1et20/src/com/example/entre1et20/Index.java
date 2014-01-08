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

@SuppressWarnings("serial")
public class Index extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String propositions ="Propositions : "; // String afficher par le JSP afficher à l'accueil du site
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
		//Appel de la base de donnée NOSQL contenant les nombres/propositions
        Query query = new Query("Nombre");
      
        PreparedQuery pq = datastore.prepare(query);
        Iterator<Entity> entities = pq.asIterable().iterator();
        Entity entity = null;
       
        int k = 0; // Compteur servant à n'afficher que 5 propositions
        //Lecture des résultats et traitement
        while(entities.hasNext() && k < 5){
                entity = entities.next();
                if(k==0){
                	propositions += entity.getProperty("number");
                }else{
                	propositions += ", " + entity.getProperty("number");
                }
                k++;
        }
       
       //Envoi des résultats à la JSP
        if(entity != null){    
                try {
                        req.setAttribute("propositions", propositions);
                        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
                } catch (ServletException e) {
                        e.printStackTrace();
                }
        }
	}
	
	 public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
         // ...
	 }

}
