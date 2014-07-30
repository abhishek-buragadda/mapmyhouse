package com.gangof8.webservice;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.gangof8.controller.MapController;
import com.gangof8.di.MapMyHouseModule;
import com.gangof8.model.MapHouseBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Abhishek
 *This is a REST API that is exposed to the user inorder to perform all CRUD operations on the DB . 
 */
@Path("/MapMyHouse")
public class MapResource {
	
	Injector injector = Guice.createInjector(new MapMyHouseModule());
	MapController mapController =  injector.getInstance(MapController.class) ;
	
	
	private String convertToJSON( List<MapHouseBean>  list  )
	{
		Gson gson = new Gson();
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}
	
	
	/**
	 * @return all data in Db in JSON format 
	 */
	@GET
	@Path("/GetAllData")
	@Produces("application/json")
	public String getAllMapData()
	{
		String mapDataJson  = null;
		try 
		{
			List<MapHouseBean> mapList = null;
			//MapController mapController= new MapController();
			mapList = mapController.getAllMapData();			
			mapDataJson = convertToJSON(mapList);

		} catch (Exception e)
		{
			System.out.println("error");
			System.out.println(e.getMessage());
		}
		return mapDataJson;
	}

	/**
	 * @param id
	 * @return record based on ID 
	 */
	@GET
	@Path("/GetDataOfID")
	@Produces("application/json")
	public String getMapDataofID(@QueryParam("id") String id )
	{
		String mapDataJson  = null;
		try 
		{
			List<MapHouseBean> mapList = null;
			//MapController  mapController= new MapController();
			mapList = mapController.getDataofID(id);			
			mapDataJson = convertToJSON(mapList);

		} catch (Exception e)
		{
			System.out.println("error");
			System.out.println(e.getMessage());
		}
		return mapDataJson;
	}
	/**
	 * @param data
	 * @return  if data is added succesfully or not . 
	 */
	@POST
	@Path("/addMapData")	
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes("application/json")
	public String setMapData( String data  )
	{
			
		Gson gson = new Gson(); 				
		LinkedHashMap result = gson.fromJson(data , LinkedHashMap.class);
		
		/*List<MapHouseBean> list=null;
		Type listType = new TypeToken<List<MapHouseBean>>(){}.getType();
		list = gson.fromJson(data,listType);
		System.out.println(list);*/
		// TO-DO change the mapping login here 
		
		MapHouseBean bean = new MapHouseBean(result.get("latitude").toString(),result.get("longitude").toString(),result.get("address").toString(),Long.toString(System.currentTimeMillis()) ,result.get("reserved_1").toString(), result.get("phoneNumber").toString(),result.get("state").toString());
		 
		return  (mapController.addData(bean)) ;
		
		
		
	}
	/**
	 *  gets a record based on the phone number given 
	 * @param phoneNumber
	 * @return record based on phone number .
	 */
	@GET
	@Path("/GetDataByPh")
	@Produces("application/json")
	public String getMapDataByPh(@QueryParam("ph") String phoneNumber )
	{
		String mapDataJson  = null;
		try 
		{
			List<MapHouseBean> mapList = null;
			//MapController  mapController= new MapController();
			mapList = mapController.getDataByPh(phoneNumber);			
			mapDataJson = convertToJSON(mapList);

		} catch (Exception e)
		{
			System.out.println("error");
			System.out.println(e.getMessage());
		}
		return mapDataJson;
	}

	@POST
	@Path("/updateMapData")	
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes("application/json")
	public String updateMapData( String data  )
	{
			
		Gson gson = new Gson(); 				
		LinkedHashMap result = gson.fromJson(data , LinkedHashMap.class);
		
		/*List<MapHouseBean> list=null;
		Type listType = new TypeToken<List<MapHouseBean>>(){}.getType();
		list = gson.fromJson(data,listType);
		System.out.println(list);*/
		// TO-DO change the mapping login here 
		
		
		MapHouseBean bean = new MapHouseBean(result.get("latitude").toString(),result.get("longitude").toString(),result.get("address").toString(),Long.toString(System.currentTimeMillis()) ,result.get("reserved_1").toString(), result.get("phoneNumber").toString(),result.get("state").toString());
		bean.setMy_house_id(result.get("my_house_id").toString());
		return  (mapController.updateData(bean)) ;
		
		
		
	}


}
