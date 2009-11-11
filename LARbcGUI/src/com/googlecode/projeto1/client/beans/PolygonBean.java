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
	private List<Double> latitudes;
	private List<Double> longitudes;
	
	public PolygonBean(){}
	
	public PolygonBean(Type type, List<Double> latitudes, List<Double> longitudes){
		this.type = type;
		this.setObs("");
		this.setLatitudes(latitudes);
		this.setLongitudes(longitudes);
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

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public void setLatitudes(List<Double> latitudes) {
		this.latitudes = latitudes;
	}

	public List<Double> getLatitudes() {
		return latitudes;
	}

	public void setLongitudes(List<Double> longitudes) {
		this.longitudes = longitudes;
	}

	public List<Double> getLongitudes() {
		return longitudes;
	}

}
