package persistence.util;

public class Coordenates {
	
	private double latitude;
	private double longitude;
	
	public Coordenates(){
		this.setLatitude(0);
		this.setLongitude(0);
	}
	
	public Coordenates(double latitude, double longitude){
		this.setLatitude(latitude);
		this.setLongitude(longitude);
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
	
	public String toString(){
		return this.latitude + " " + this.longitude;
	}

}
