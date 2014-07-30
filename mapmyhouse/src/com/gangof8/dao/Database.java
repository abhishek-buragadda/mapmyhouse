package com.gangof8.dao;

import java.sql.Connection;

/**
 * @author Abhishek
 *
 */
public interface Database {
	
	public Connection getConnection() throws Exception;

}
