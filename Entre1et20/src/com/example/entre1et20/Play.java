package com.example.entre1et20;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

@SuppressWarnings("serial")
public class Play extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//...
	}
	
	 public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		 String pseudo = req.getParameter("pseudo");
		 int value = Integer.parseInt(req.getParameter("value"));
		 String propositions ="Dernières Propositions : "; // String afficher par le JSP
		 
		 
		 
		//On stocke le pseudo et son nombre d'essai dans le cache
		try {
			Cache cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());			
			cache.put(pseudo,value);		
		} catch (CacheException e) {
			e.printStackTrace();
		}
		 	 
		
		//Appel de la base de donnée NOSQL contenant les nombres/propositions	
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
        Query query = new Query("Nombre");
        query.addSort("date", SortDirection.DESCENDING); //On trie par date
      
        PreparedQuery pq = datastore.prepare(query);
        List<Entity> entities = pq.asList(FetchOptions.Builder.withLimit(8)); //On ne recupère que les 8 premiers nombres
        Iterator<Entity> ite = entities.iterator();
        Entity entity = null;
       
        int k = 0;
        
        //Lecture des résultats et traitement
        while(ite.hasNext()){
                entity = ite.next();
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
                	req.setAttribute("pseudo",  pseudo);
        			req.setAttribute("propositions", propositions);               
                    getServletContext().getRequestDispatcher("/play.jsp").forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                }
        }
	 }

}
