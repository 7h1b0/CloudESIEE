package com.example.entre1et20;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

/*
 * Classe servant à affichier la liste des gagnants
 */
public class Winner  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {	
		String winners ="";
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		//Appel de la base de donnée NOSQL Winners
        Query query = new Query("Winner");
        query.addSort("value", SortDirection.ASCENDING); //On trie par score
        query.addSort("date", SortDirection.DESCENDING); //On trie par date
      
        PreparedQuery pq = datastore.prepare(query);
        List<Entity> entities = pq.asList(FetchOptions.Builder.withLimit(10)); //On ne recupère que les 10 derniers gagnants
        Iterator<Entity> ite = entities.iterator();
        Entity entity = null;
      
        //Lecture des résultats
        while(ite.hasNext()){
                entity = ite.next();
                winners += "<ul><li>" + entity.getProperty("name") +"</li><li>"+ entity.getProperty("value") + "</li></ul>";
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
