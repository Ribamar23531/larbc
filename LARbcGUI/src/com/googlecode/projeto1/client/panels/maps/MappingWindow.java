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
	
//	protected static MapWidget myMap = null;
	protected MapWidget myMap;
	private final LatLng CAMPINA_GRANDE_POINT = LatLng.newInstance(-7.22, -35.88);
	private final int ZOOM = 13;
	
	public MappingWindow(){
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
		mapPanel.add(this.myMap);
		this.add(mapPanel);
	}

	private void getMap() {
		this.myMap = new MapWidget(CAMPINA_GRANDE_POINT, ZOOM);
		this.myMap.setSize("630px", "500px");
		this.myMap.setUIToDefault();
		this.myMap.setContinuousZoom(true);
		this.myMap.setScrollWheelZoomEnabled(true);
		this.myMap.setCurrentMapType(MapType.getNormalMap());	
		
	}
	
	protected void setDefaultMap(){
		this.myMap.setCenter(CAMPINA_GRANDE_POINT);
		this.myMap.setZoomLevel(ZOOM);
		this.myMap.setCurrentMapType(MapType.getNormalMap());	
	}	

}
