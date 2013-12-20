package com.example.entre1et20;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Winner  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String winners ="";
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		//Appel de la base de donnée NOSQL Nombre
        Query query = new Query("Winner");
      
        PreparedQuery pq = datastore.prepare(query);
        Iterator<Entity> entities = pq.asIterable().iterator();
        Entity entity = null;
       
        int k = 0;
        //Lecture des résultats et traitement
        while(entities.hasNext() && k < 5){
                entity = entities.next();
                winners += "<li>" + entity.getProperty("name") +"</li>";
                k++;
        }
        
      //Envoi des résultats à la JSP
        if(entity != null){    
                try {
                        req.setAttribute("winners", winners);
                        getServletContext().getRequestDispatcher("/winner.jsp").forward(req, resp);
                } catch (ServletException e) {
                        e.printStackTrace();
                }
        }
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// ...
	}
}
