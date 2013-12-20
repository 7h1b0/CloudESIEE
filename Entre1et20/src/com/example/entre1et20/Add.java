package com.example.entre1et20;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class Add extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// ...
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String vName = req.getParameter("name"); // Retourne forcement un String
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity vEntiteNombre = new Entity("Winner");
		vEntiteNombre.setProperty("name", vName);
		datastore.put(vEntiteNombre);
		
		resp.sendRedirect("/");
	}
}
