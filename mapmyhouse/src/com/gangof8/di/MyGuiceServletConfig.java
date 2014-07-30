package com.gangof8.di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.server.impl.container.servlet.ServletAdaptor;
/**
 * @author Abhishek
 *
 */
public class MyGuiceServletConfig extends GuiceServletContextListener{

	/* (non-Javadoc)
	 * @see com.google.inject.servlet.GuiceServletContextListener#getInjector()
	 * Injecting the Servlet Module . Generally the ServletAdaptor will be present in the web.xml and serves the requests . That is replaced by a 
	 * GUICE filter and GuiceServletConfig and the actual ServletModule is injected via GUICE.   
	 */
	@Override
	protected Injector getInjector() {
		// TODO Auto-generated method stub
			return Guice.createInjector(new ServletModule(){

				@Override
				protected void configureServlets() {
					// TODO Auto-generated method stub
					super.configureServlets();
									 					
					serve("/*").with(new ServletAdaptor());
					
				}				
			} );
	}

}
