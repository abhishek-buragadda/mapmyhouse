package com.gangof8.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Abhishek
 *
 */
public class StateMapperUtil {
	
	private static final Map<String,String> stateMap ; 
	
	static {
		
		 Map<String , String> map = new HashMap<String, String>();
		 map.put("Andhra Pradesh", "AP");
		 map.put("Arunachal Pradesh","AR");
		 map.put("Assam", "AS");
		 map.put("Bihar", "BR");
		 map.put("Chhattisgarh", "CT");
		 map.put("Goa", "GA");
		 map.put("Gujarat", "GJ");
		 map.put("Haryana" ,"HR" );
		 map.put("Himachal Pradesh","HP" );
		 map.put("Jammu and Kashmir", "JK");
		 map.put("Jharkhand", "JH");
		 map.put("Karnataka", "KA");
		 map.put("Kerala", "KL");
		 map.put("Madhya Pradesh", "MP");
		 map.put("Maharashtra", "MP");
		 map.put("Manipur", "MN");
		 map.put("Meghalaya", "ML");
		 map.put("Mizoram", "MZ");
		 map.put("Nagaland", "NL");
		 map.put("Odisha", "OR");
		 map.put("Orrisa", "OR");
		 map.put("Punjab", "PB");
		 map.put("Rajasthan", "RJ");
		 map.put("Sikkim", "SK");
		 map.put("Tamil Nadu", "TN");
		 map.put("Telangana", "TS");
		 map.put("Tripura", "TR");
		 map.put("Uttar Pradesh", "UP");
		 map.put("Uttarakhand", "UK");
		 map.put("West Bengal", "WB");
		 map.put("New Delhi", "Delhi");
		 map.put("Andaman and Nicobar Islands", "AN");
		 map.put("Chandigarh", "CH");		 
		 map.put("Dadra and Nagar Haveli", "DN");
		 map.put("Daman and Diu", "DD");	
		 map.put("Lakshadweep", "LD");
		 map.put("National Capital Territory of Delhi", "DL");
		 map.put("Puducherry", "PY");
		 
		 stateMap = Collections.unmodifiableMap(map);
		 
		 
		
		
		
	}
	public static Map<String,String > getStateMap()
	{		
		return stateMap ; 
	}
	

}
