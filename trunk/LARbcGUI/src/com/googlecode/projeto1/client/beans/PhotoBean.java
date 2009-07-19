package com.googlecode.projeto1.client.beans;

import java.io.Serializable;

public class PhotoBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idCaso;
	private String path;
	
	public PhotoBean(){}
	
	
	public long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(long idCaso) {
		this.idCaso = idCaso;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	

}
