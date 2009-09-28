package com.googlecode.projeto1.client.panels.manage;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class SelectedLocation {
	
	private static String location = null;
	
	public static String getLocation(){
		if(location == null){
			location = "";
		}
		return location;
	}
	
	public static void setLocation(String newLocation){
		location = newLocation;
	}

}
