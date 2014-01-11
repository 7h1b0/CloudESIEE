package com.example.entre1et20;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
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
import com.google.appengine.api.datastore.Transaction;

/*
 * Classe appelé après un post d'une proposition par le joueur
 * 
 */
@SuppressWarnings("serial")
public class Post extends HttpServlet {
	
		//Vérification des entrés utilisateurs
		public int verif(String pNombre){
			try{
				 return Math.round(Integer.parseInt(pNombre));
			} catch(NumberFormatException e){
				 return -1;
			}
		}
	
        public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        	//..
        }
        
         public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
         
        	 String pseudo = req.getParameter("pseudo"); // On récupère le pseudo du joueur
	         String vStrNumber = req.getParameter("newNumber"); // On récupère la proposition du joueur
	         int value = 0;     
	         int vNombre = verif(vStrNumber);  // On vérifie les entrés utilisateurs -- pas de string, de boolean ..etc   
	         long vMoyenne = 0;
               
	         //On vérifie que la proposition est bien entre 1 et 20
	         if(vNombre < 1 || vNombre > 20 ){
                 try {
                	 req.setAttribute("titre", "Merci d'entrer un nombre entier entre 1 et 20");
                	 req.setAttribute("pseudo", pseudo);
                	 req.setAttribute("value", value);
                	 getServletContext().getRequestDispatcher("/Content.jsp").forward(req, resp);
                 } catch (ServletException e) {
                	 e.printStackTrace();
                 }
                 
            } else{ //Si tout vas bien
            	
            	 //On recupère le nombre d'essai du joueur dans le cache
	   	         try {
	   	 			Cache cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());
	   	 			value = (int)cache.get(pseudo); 
	   	         }catch (CacheException e) {
	   	 			e.printStackTrace();
	   	 		}
	   	         
	   	         //On incremente le nombre d'essai du joueur
	   	         value ++;
                
	   	         //Recuperation de la date
            	 Date date = new Date();
            	 
            	 //On ajoute sa proposition dans la base de donnée des propositions
                 DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
                 
                 //Utilisation d'une transation pour aller plus vite
                 Transaction txn = datastore.beginTransaction();             
                 try {
	                 Entity vEntiteNombre = new Entity("Nombre");
	                 vEntiteNombre.setUnindexedProperty("number", vNombre); //On n'indexe pas vNombre car pas besoin de le trié après = gain de performance
	                 vEntiteNombre.setProperty("date", date);
	                 datastore.put(vEntiteNombre);
	                 
	                 txn.commit();
                 } finally {
            	    if (txn.isActive()) {
            	        txn.rollback();
            	    }
                }

                
                 //On récupère la moyenne
                 Query query = new Query("Moyenne");
                 PreparedQuery pq = datastore.prepare(query);
                 Iterator<Entity> entities = pq.asIterable().iterator();
                 Entity entity = null;
                
                 //Lecture de la moyenne
                 while(entities.hasNext()){
                          entity = entities.next();
                          vMoyenne = (long) entity.getProperty("solution");
                 }
                
                //On compare la moyenne avec la proposition
                 
                 if((long) vNombre == vMoyenne){ //Le joueur gagne
                	//Envoi des résultats à la JSP
                     try {
                    	 req.setAttribute("pseudo", pseudo);
                    	 req.setAttribute("value", value);
                    	 getServletContext().getRequestDispatcher("/win.jsp").forward(req, resp);
                     } catch (ServletException e) {
                    	 e.printStackTrace();
                     }
                     Queue queue = QueueFactory.getDefaultQueue();
                     queue.add(TaskOptions.Builder.withUrl("/add").method(Method.POST).param("pseudo", pseudo).param("value", ""+value));  // Appel de la queue qui va ajouter le nom du joueur au gagnant;
                     queue.add(TaskOptions.Builder.withUrl("/restore").method(Method.POST)); // Appel de la queue qui va remplacer la base de donnée
                     queue.add(TaskOptions.Builder.withUrl("/calcul").method(Method.POST)); // Appel de la queue qui va re-calculer la moyenne
                                
                 } else{ // Le joueur perd
                      	Queue queue = QueueFactory.getDefaultQueue();
                         queue.add(TaskOptions.Builder.withUrl("/calcul").method(Method.POST)); // Appel de la queue qui va re-calculer la moyenne
                                
                         //Envoi des résultats à la JSP
                         try {
                        	 req.setAttribute("titre", "Perdu !");
                        	 req.setAttribute("pseudo", pseudo);
                        	 req.setAttribute("value", value);
                        	 getServletContext().getRequestDispatcher("/Content.jsp").forward(req, resp);
                         } catch (ServletException e) {
                        	 e.printStackTrace();
                         }
                        
                 } // if-else
           } // else condition vNombre
     }
}