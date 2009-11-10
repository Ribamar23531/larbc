package com.googlecode.projeto1.client.beans;

import java.io.Serializable;

public class PointBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idPoint;
	private Type type;
	private String obs;
	private String location;
	
	public PointBean(){}
	
	public PointBean(Type type, String location){
		this.setObs("");
		this.type = type;
		this.setLocation(location);
	}

	public void setIdPoint(long idPoint) {
		this.idPoint = idPoint;
	}

	public long getIdPoint() {
		return idPoint;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getObs() {
		return obs;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

}
