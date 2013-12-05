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
public class Home extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String propositions ="Dernières propositions : "; // String afficher par le JSP index
		String init_moyenne = "5"; //Parce que Google fait n'importe quoi et que ce magnifique int se transforme en LONG dans la base de donné et que le cast d'un long en int marche pas sans que Eclipse me crache à la guele
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		//Initialisation base de donnée moyenne
		Entity moyenne = new Entity("Moyenne");
		moyenne.setProperty("solution", init_moyenne);
	    datastore.put(moyenne);
	
		//Appel de la base de donnée NOSQL
        Query query = new Query("Nombre");
      
        PreparedQuery pq = datastore.prepare(query);
        Iterator<Entity> entities = pq.asIterable().iterator();
        Entity entity = null;
       
        //Lecture des résultats et traitement
        while(entities.hasNext()){
                entity = entities.next();
                propositions += " " + entity.getProperty("number");
                //k++;
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
