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
	private double latitude;
	private double longitude;
	
	public PointBean(){}
	
	public PointBean(Type type, double latitude, double longitude){
		this.setObs("");
		this.type = type;
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}

	public void setIdPoint(long idPoint) {
		this.idPoint = idPoint;
	}

	public long getIdPoint() {
		return idPoint;
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

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLongitude() {
		return longitude;
	}

}
