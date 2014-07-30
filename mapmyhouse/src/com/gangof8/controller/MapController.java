package com.gangof8.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.gangof8.dao.MapHouseDao;
import com.gangof8.model.MapHouseBean;
import com.google.inject.Inject;
/**
 * @author Abhishek
 *
 */
public class MapController {
		
	 @Inject DataSource dataSource ;     
	 @Inject MapHouseDao mapDao ; 

	/** Gets all MapData from database .
	 * @return return all records in DB
	 * @throws Exception
	 */
	public List<MapHouseBean> getAllMapData()throws Exception {
		List<MapHouseBean> mapArray = null;
		Connection connection=null ; 
		try {			  
			     connection = dataSource.getConnection();
		//		MapHouseDao mapDao= new MapHouseDao();
				mapArray=mapDao.getMapData(connection);
		
		} catch (Exception e) {
			throw e;
		}
		finally{
			connection.close();
		}
		return mapArray;
	}
	
	

	/**
	 * Get map data based on ID
	 * @param id
	 * @return   record with ID
	 * @throws Exception
	 */
	public List<MapHouseBean> getDataofID(String id )throws Exception {
		List<MapHouseBean> mapArray = null;
		Connection connection=null ;
		try {			 
			    connection = dataSource.getConnection();
			//	MapHouseDao mapDao= new MapHouseDao();
				mapArray=mapDao.getMapDataofId(connection, id );
		
		} catch (Exception e) {
			throw e;
		}
		finally{
			connection.close();
		}
		return mapArray;
	}
	
	/**
	 * Add new record to the DB
	 * @param bean
	 * @return if addition is successful or not .
	 */
	public String addData (MapHouseBean bean ) 
	{				 
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			//MapHouseDao mapDao = new  MapHouseDao() ; 
			return mapDao. addData(connection , bean );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ; 
		} 
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}



	/**
	 * Get record by phone Number . 
	 * @param phoneNumber
	 * @return a record with matching phone number . 
	 * @throws Exception
	 */
	public List<MapHouseBean> getDataByPh(String phoneNumber) throws Exception {
		// TODO Auto-generated method stub
		List<MapHouseBean> mapArray = null;
		Connection connection=null ;
		try {
			  
			    connection = dataSource.getConnection();
				//MapHouseDao mapDao= new MapHouseDao();
				mapArray=mapDao.getMapDataofPh(connection,phoneNumber );
		
		} catch (Exception e) {
				throw e ; 
		}
		finally {
			connection.close(); 
		}
		return mapArray;
		
	}



	/**
	 * Add new record to the DB
	 * @param bean
	 * @return if addition is successful or not .
	 */
	public String updateData (MapHouseBean bean ) 
	{				 
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			//MapHouseDao mapDao = new  MapHouseDao() ; 
			return mapDao.updateData(connection , bean );
		}
		catch(SQLException e )
		{
				System.out.println(e.getErrorCode());
				
				e.printStackTrace();
				return new Integer(e.getErrorCode()).toString() ;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			return e.getMessage() ; 
		} 
		finally {
			try {
				connection.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}
		
	}

}
