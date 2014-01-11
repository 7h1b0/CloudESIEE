package com.example.entre1et20;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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
public class Index extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String propositions ="Derni�res Propositions : "; // String afficher par le JSP afficher � l'accueil du site
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
		//Appel de la base de donn�e NOSQL contenant les nombres/propositions
        Query query = new Query("Nombre");
        query.addSort("date", SortDirection.DESCENDING); //On trie par date
      
        PreparedQuery pq = datastore.prepare(query);
        List<Entity> entities = pq.asList(FetchOptions.Builder.withLimit(8)); //On ne recup�re que les 8 premiers nombres
        Iterator<Entity> ite = entities.iterator();
        Entity entity = null;
       
        int k = 0;
        //Lecture des r�sultats et traitement
        while(ite.hasNext()){
                entity = ite.next();
                if(k==0){
                	propositions += entity.getProperty("number");
                }else{
                	propositions += ", " + entity.getProperty("number");
                }
                k++;
        }
       
       //Envoi des r�sultats � la JSP
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
