package com.googlecode.projeto1.client.beans;

import java.io.Serializable;
import java.util.List;

public class PolygonBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idPolygon;
	private Type type;
	private String obs;
	private List<String> location;
	
	public PolygonBean(){}
	
	public PolygonBean(Type type, List<String> location){
		this.type = type;
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

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

}
