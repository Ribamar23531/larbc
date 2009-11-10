package com.googlecode.projeto1.client.beans;

import java.io.Serializable;
import java.util.List;

public class LineBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long idLine;
	private Type type;
	private String obs;
	private List<String> location;
	
	public LineBean(){}
	
	public LineBean(Type type, List<String> location){
		this.type = type;
		this.obs = "";
		this.setLocation(location);
	}

	public void setIdLine(long idLine) {
		this.idLine = idLine;
	}

	public long getIdLine() {
		return idLine;
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
