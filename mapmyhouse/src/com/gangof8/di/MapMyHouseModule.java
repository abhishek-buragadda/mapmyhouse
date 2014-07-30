package com.gangof8.di;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.gangof8.dao.MapHouseDao;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * @author Abhishek
 *
 */
public class MapMyHouseModule extends AbstractModule {

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 * 
	 */
	@Override
	protected void configure() {
		
	//	bind(Database.class).to( DatabaseImpl.class).in(Singleton.class) ; 	
	 bind(MapHouseDao.class);
	}
		/**
		 * @return
		 * Guice will use this method whenever it needs DataSource object i.e whenever the "@Inject DataSource" code is present .  
		 */
		@Provides @Singleton
		DataSource getDataSource()
		{			
			String JDBC_URL= "jdbc:mysql://localhost:3306/abhionso_mapmyhouse";
			String DRIVERNAME="com.mysql.jdbc.Driver"; 
			String USERNAME = "root"; 
			//String PASSWORD = "amazon" ; 
			String PASSWORD = "accolite" ;
			PoolProperties  p = new PoolProperties();  // creating a Connection Pool Properties and setting the required properties 
			p.setUrl(JDBC_URL);
		    p.setDriverClassName(DRIVERNAME);
		    p.setUsername(USERNAME);
		    p.setPassword(PASSWORD);
		    p.setJmxEnabled(true);
		    p.setTestWhileIdle(false);
		    p.setTestOnBorrow(true);    
		    p.setTestOnReturn(false);
		    p.setValidationInterval(30000);
		    p.setTimeBetweenEvictionRunsMillis(30000);
		    p.setMaxActive(100);
		    p.setInitialSize(10);
		    p.setMaxWait(10000);
		    p.setRemoveAbandonedTimeout(60);
		    p.setMinEvictableIdleTimeMillis(30000);
		    p.setMinIdle(10);
		    p.setLogAbandoned(true);
		    p.setRemoveAbandoned(true);
		    p.setJdbcInterceptors(
		      "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
		      "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		
		    /* creating the DataSource which will give create the connection pool with the setting above , and which will return the connection Object 
		    	whenever we do datasource.getConnection(). When we do connection.close() the connection object will return back to the pool , so in this 
		    	way we are preventing unnecessary creation of the Connection object which is resource Consuming . 
		    	We are using tomcat jdbc-pool  for maintaining connection pool.(tomcat-jdbc.jar)  
		    *
		    */
			DataSource datasource = new DataSource(); 
			datasource.setPoolProperties(p);
			return datasource ; 
			
			
		}
		
		
		
		
	

}
