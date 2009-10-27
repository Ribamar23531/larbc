package com.googlecode.projeto1.client.panels.maps;

import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Window;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public abstract class MappingWindow extends Window{
	
	protected static MapWidget myMap = null;	
	private final LatLng CAMPINA_GRANDE_POINT = LatLng.newInstance(-7.22, -35.88);
	private final int ZOOM = 13;
	
	protected MappingWindow(){
		super();		
		this.setClosable(true);
		this.setPlain(true);		
		this.setPaddings(5);  
		this.setButtonAlign(Position.CENTER);			
		this.setCloseAction(Window.HIDE);		
		this.setResizable(false);
		this.setSize("650px", "500px");
		getMap();
		RootPanel mapPanel = RootPanel.get("map_div");
		mapPanel.clear();
		mapPanel.add(myMap);
		this.add(mapPanel);
	}

	protected void getMap() {
		if(myMap == null){
			myMap = new MapWidget(CAMPINA_GRANDE_POINT, ZOOM);			
		}
		myMap.setSize("630px", "500px");
		myMap.setUIToDefault();
		myMap.setContinuousZoom(true);
		myMap.setScrollWheelZoomEnabled(true);
		myMap.setCurrentMapType(MapType.getNormalMap());	
		
	}
	
	protected void setDefaultMap(){
		myMap.setCenter(CAMPINA_GRANDE_POINT);
		myMap.setZoomLevel(ZOOM);
		myMap.setCurrentMapType(MapType.getNormalMap());	
	}	

}
