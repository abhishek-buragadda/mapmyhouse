package com.gangof8.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author Abhishek 
 *this class is not used now . It is in-efficient as it creates connection object everytime when getConection is called .
 *So moved the implementation to ConnectionPool using jdbc-pool  
 */
public class DatabaseImpl implements Database {
	
	/* (non-Javadoc)
	 * @see com.gangof8.dao.Database#getConnection()
	 */
	public Connection getConnection() throws Exception
	{
		try
		{
		//String connectionURL = "jdbc:mysql://mysql3000.mochahost.com:3306/brahm357_mapmyhouse";			
		//String connectionURL = "jdbc:mysql://127.0.0.1:3306/abhionso_mapmyhouse";
		String connectionURL = "jdbc:mysql://localhost:3306/abhionso_mapmyhouse";
		Connection connection = null;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		//connection = DriverManager.getConnection(connectionURL, "brahm357_myhome", "myhomeapps");
		//connection = DriverManager.getConnection(connectionURL, "root", "accolite");
		connection = DriverManager.getConnection(connectionURL, "root", "amazon");
		System.out.println("connection created succesfully ");
	    return connection;
		}
		catch (SQLException e)
		{
		System.out.println(e.getMessage());
		throw e;	
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		throw e;	
		}
	}

}
