package com.googlecode.projeto1.client.beans;

import java.io.Serializable;
import java.util.List;

public class PolygonBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idPolygon;
	private String obs;
	private List<String> location;
	
	public PolygonBean(){}
	
	public PolygonBean(List<String> location){
		this.setObs("");
		this.setLocation(location);
	}

	public void setIdPolygon(long idPolygon) {
		this.idPolygon = idPolygon;
	}

	public long getIdPolygon() {
		return idPolygon;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getObs() {
		return obs;
	}

	public void setLocation(List<String> location) {
		this.location = location;
	}

	public List<String> getLocation() {
		return location;
	}

}
