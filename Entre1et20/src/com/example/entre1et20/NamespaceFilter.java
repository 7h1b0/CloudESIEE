package com.example.entre1et20;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.google.appengine.api.NamespaceManager;
import com.sun.istack.internal.logging.Logger;

public class NamespaceFilter implements Filter{
//	private static final Logger log = Logger.getLogger(FormulaireServlet.class.getName()));
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		if(NamespaceManager.get() == null){
			String nameSpace = NamespaceManager.getGoogleAppsNamespace();
			NamespaceManager.set(nameSpace);
//			log.info("NamespaceFilter, nameSpace : " + nameSpace );
		}
		chain.doFilter(req,  res);
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
