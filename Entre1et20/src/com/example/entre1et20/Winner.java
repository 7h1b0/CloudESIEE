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
import com.google.appengine.api.datastore.Query.SortDirection;

/*
 * Classe servant � affichier la liste des gagnants
 */
public class Winner  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String winners ="";
		
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		//Appel de la base de donn�e NOSQL Winners
        Query query = new Query("Winner");
        query.addSort("date", SortDirection.DESCENDING); //On trie par date
      
        PreparedQuery pq = datastore.prepare(query);
        Iterator<Entity> entities = pq.asIterable().iterator();
        Entity entity = null;
      
        //Lecture des r�sultats
        while(entities.hasNext()){
                entity = entities.next();
                winners += "<li>" + entity.getProperty("name") +"</li>";
        }
        
      //Envoi des r�sultats � la JSP
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
