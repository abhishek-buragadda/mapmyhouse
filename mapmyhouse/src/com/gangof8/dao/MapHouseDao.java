package com.gangof8.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import com.gangof8.model.MapHouseBean;
import com.gangof8.util.StateMapperUtil;

/**
 * @author Abhishek
 *
 */
public class MapHouseDao {
	
	
	private static final String ALLDATA = "SELECT * from my_house_location" ;
	private static final String GETDATAOFID= "select * from my_house_location where my_house_id=?";
	private static final String ADD_DATA= "insert into my_house_location values (?,?,?,?,?,?,?)";
	private static final String GETDATABYPHNUMBER="select * from my_house_location where phoneNumber=?";
	private static final String CHECKIDEXISTS="select * from my_house_location where my_house_id=? ";
	private static final String UPDATE_DATA= "update my_house_location set my_house_latitude=?,my_house_longitude=?, my_home_address=? , updateTime=? , reserved_1=? , phoneNumber= ?  where id=?";	
	/**
	 * This method maps the resultset to the bean objects .
	 * @param ResultSet 
	 * @returns the List of MapHouseBean objects 
	 * 
	 */
	private  List<MapHouseBean> resultSetMapper (ResultSet rs   )
	{	
		
		List<MapHouseBean> mapData = new ArrayList<MapHouseBean>(); 
		try {
			while(rs.next())
			{				
				MapHouseBean mapBean = new MapHouseBean() ; 
				mapBean.setMy_house_id(rs.getString(1));
				mapBean.setMy_house_latitude(rs.getString(2)); 
				mapBean.setMy_house_longitude(rs.getString(3));
				mapBean.setMy_house_address(rs.getString(4));
				mapBean.setUpdatetime(rs.getString(5));
				mapBean.setReserved_1(rs.getString(6));
				mapBean.setPhoneNumber(rs.getString(7));
				mapData.add(mapBean);												
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return mapData  ;	
	}
	/**
	 * @param connection
	 * @return All the records from the DB 
	 * @throws Exception
	 */
	public List<MapHouseBean> getMapData(Connection connection) throws Exception
	{		
		List<MapHouseBean> mapData ;
		try
		{			
			PreparedStatement ps = connection.prepareStatement(ALLDATA);	
			ResultSet rs = ps.executeQuery();
			mapData = resultSetMapper(rs ); 
				return mapData  ;
		}
		catch(Exception e)
		{
			throw e;
		}
		finally {
			connection.close(); 
		}
	}

	/**
	 * search a record based on the ID
	 * @param connection
	 * @param id
	 * @return the record with the ID provided .    
	 */
	public List<MapHouseBean> getMapDataofId(Connection connection,
			String id) {
		// TODO Auto-generated method stub
		List<MapHouseBean> mapData= null   ; 
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(GETDATAOFID);
			ps.setString(1,id);
			ResultSet rs = ps.executeQuery();
			mapData =  resultSetMapper(rs); 
		 							
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return mapData;
	}
	/**
	 * Add data to the Database 
	 * @param conn
	 * @param bean
	 * @return true or false based on whether the addition is successful or not . 
	 */
	public String addData(Connection conn, MapHouseBean bean) {
		// TODO Auto-generated method stubs
		try {
			String randomNumber = getRandomNumber(conn,bean.getState()) ; 
		//	boolean flag =checkAvailability(conn , "KA" + randomNumber);   // change the hardcoded city name to the arguement from 
			
			
			PreparedStatement ps = conn.prepareStatement(ADD_DATA);
			ps.setString(1, randomNumber);
			ps.setDouble(2, new Double(bean.getMy_house_latitude()));
			ps.setDouble(3, new Double( bean.getMy_house_longitude()));
			ps.setString(4, bean.getMy_house_address());
			ps.setTimestamp(5, new Timestamp(Long.valueOf(bean.getUpdatetime()))); 
			ps.setString(6, bean.getReserved_1()) ; // this defaults to null
			ps.setString(7,bean.getPhoneNumber());
			 int result = ps.executeUpdate() ; //returns the number of rows effected in the query . 
			 if(result > 0 )
			 {
				 return randomNumber ; 
			 }
			 else 
				 return null ; 
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;  
		}
		
		
	}
	private boolean checkAvailability(Connection conn , String id) {
		// TODO Auto-generated method stub
			
		 
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(CHECKIDEXISTS); 
			ps.setString(1, id);
			ResultSet rs =  ps.executeQuery(); 
			if(rs != null && rs.next())
			{
				return false  ; 
			}
			else 
			{
				return true ;  
			}
		
		}
		catch(Exception e )
		{
			return false ; 
		}
		finally{
			
		}
		
		
	
	}
	private String getRandomNumber(Connection conn,String state ) {
		// TODO Auto-generated method stub
		Map<String, String> stateMap = StateMapperUtil.getStateMap(); 
		String number = RandomStringUtils.random(8, "0123456789");
		while (true )
		{
			if ( checkAvailability(conn, stateMap.get(state)+number) )
			{				
				return stateMap.get(state)+number ; 
			}
			else {
				number=RandomStringUtils.randomAlphanumeric(8);
			}

		}			
				
	}
	/**
	 * returns a record with give phone number . 
	 * @param connection
	 * @param phoneNumber
	 * @return  a record based with given  phonenumber
	 */
	public List<MapHouseBean> getMapDataofPh(Connection connection,
			String phoneNumber) {
		// TODO Auto-generated method stub
		List<MapHouseBean> mapData = null  ; 
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(GETDATABYPHNUMBER);
			ps.setString(1,phoneNumber);
			ResultSet rs = ps.executeQuery();
			mapData =  resultSetMapper(rs);  							
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		return mapData;
	}
	public String updateData(Connection connection, MapHouseBean bean) {
		// TODO Auto-generated method stub

		Boolean status= false  ; 
		try {
					
			PreparedStatement ps = connection.prepareStatement(UPDATE_DATA);			
			ps.setDouble(1, new Double(bean.getMy_house_latitude()));
			ps.setDouble(2, new Double( bean.getMy_house_longitude()));
			ps.setString(3, bean.getMy_house_address());
			ps.setTimestamp(4, new Timestamp(Long.valueOf(bean.getUpdatetime()))); 
			ps.setString(5, bean.getReserved_1()) ; // this defaults to null
			ps.setString(6,bean.getPhoneNumber());
			ps.setString(7, bean.getMy_house_id());
			 int result = ps.executeUpdate() ; //returns the number of rows effected in the query . 
			 if(result > 0 )
			 {
				 status = true ; 
				 return  status.toString() ; 
			 }
			 else 
			 {
				 	
				 return status.toString() ;
			 }				  			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;  
		}
		catch(Exception e )
		{
			e.printStackTrace();
			return null ; 
		}
		


	}
}
	

