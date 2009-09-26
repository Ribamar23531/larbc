package com.googlecode.projeto1.client.beans;

import java.io.Serializable;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class CaseBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String state;
	private String city;
	private String neighborhood;
	private String street;
	private int number;
	private String name;
	private float builtArea;
	private float totalArea;
	private int garageSpace;
	private int bedroom;
	private int suite;
	private int bathroom;
	private String type;
	private float price;
	private int businessType;
	private String location;
	
	public CaseBean(){}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getBuiltArea() {
		return builtArea;
	}

	public void setBuiltArea(float builtArea) {
		this.builtArea = builtArea;
	}

	public float getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(float totalArea) {
		this.totalArea = totalArea;
	}

	public int getGarageSpace() {
		return garageSpace;
	}

	public void setGarageSpace(int garageSpace) {
		this.garageSpace = garageSpace;
	}

	public int getBedroom() {
		return bedroom;
	}

	public void setBedroom(int bedroom) {
		this.bedroom = bedroom;
	}

	public int getSuite() {
		return suite;
	}

	public void setSuite(int suite) {
		this.suite = suite;
	}

	public int getBathroom() {
		return bathroom;
	}

	public void setBathroom(int bathroom) {
		this.bathroom = bathroom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getBusinessType() {
		return businessType;
	}

	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}	
	
	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public String toString(){
		String newLine = "\n";
		String result = "Id: " + getId() + newLine +
						"State: + " + getState() + newLine + 
						"City: " + getCity() + newLine +
						"Neighborhood: " + getNeighborhood() + newLine +
						"Street: " + getStreet() + newLine +
						"Number: " + getNumber() + newLine +
						"Built area: " + getBuiltArea() + newLine +
						"Total area: " + getTotalArea() + newLine +
						"Garage: " + getGarageSpace() + newLine +
						"Bedroom: " + getBedroom() + newLine +
						"Suite: " + getSuite() + newLine +
						"Bathroom: " + getBathroom() + newLine +
						"Type: " + getType() + newLine +
						"Price: " + getPrice() + newLine +
						"Business type: " + getBusinessType() + newLine +
						"Location: " + getLocation() + newLine;
		return result;
	}
}
