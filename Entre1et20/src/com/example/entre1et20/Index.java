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
		String propositions ="Propositions Aléatoires : "; // String afficher par le JSP index
		//String init_moyenne = "5"; //Parce que Google fait n'importe quoi et que ce magnifique int se transforme en LONG dans la base de donné et que le cast d'un long en int marche pas sans que Eclipse 
		
		
		//Ou comment se peter les couilles avec un logiciel de manchot et un API de merde alors que le projet ce boucle en 5 min avec PHP et Javascript ...
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
		//Appel de la base de donnée NOSQL Nombre
        Query query = new Query("Nombre");
      
        PreparedQuery pq = datastore.prepare(query);
        Iterator<Entity> entities = pq.asIterable().iterator();
        Entity entity = null;
       
        int k = 0;
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
