package com.googlecode.projeto1.client.panels.modality;

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
