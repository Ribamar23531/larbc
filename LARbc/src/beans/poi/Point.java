package beans.poi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import persistence.util.Coordinates;

//@NamedQueries( {@NamedQuery(name = "getPoint", query = "select p from Point p where p.latitudeStr = :latitude AND p.longitudeStr = :longitude")})
@Entity
@Table(name = "points")
public class Point {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_point", updatable = false, nullable = false)
	private long idPoint;
	@Column(updatable = true, nullable = false)
	private String name;
	@Column(updatable = true, nullable = false)
	private String type;
	@Column(updatable = true, nullable = false)
	private String obs;
	@Transient
	private Coordinates coordenate;	
	
	//construtor vazio necessario pelo hibernate
	public Point(){}
	
	public Point(double latitude, double longitude){
		this.setObs("");
		this.coordenate = new Coordinates(latitude, longitude);
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

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}	

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	

	public void setLatitude(String latitude) {
		if(coordenate == null){
			this.coordenate = new Coordinates();
		}
		this.coordenate.setLatitude(Double.parseDouble(latitude));
	}
	
	public void setLatitude(double latitude) {
		if(coordenate == null){
			this.coordenate = new Coordinates();
		}
		this.coordenate.setLatitude(latitude);
	}
	
	public double getLatitude(){
		return this.coordenate.getLatitude();
	}
	
	public void setLongitude(String longitude) {
		if(coordenate == null){
			this.coordenate = new Coordinates();
		}
		this.coordenate.setLongitude(Double.parseDouble(longitude));
	}
	
	public void setLongitude(double longitude) {
		if(coordenate == null){
			this.coordenate = new Coordinates();
		}
		this.coordenate.setLongitude(longitude);
	}
	
	public double getLongitude(){
		return this.coordenate.getLongitude();
	}

	public void setCoordenate(Coordinates coordenate) {
		this.coordenate = coordenate;
	}

	public Coordinates getCoordenate() {
		return coordenate;
	}
	
	public String toString(){
		return this.coordenate.getLatitude() + " " + this.coordenate.getLongitude();
	}
	
//	public void setLatitudeStr(double latitude) {
//		this.latitudeStr = latitude + "";
//	}
//
//	public String getLatitudeStr() {
//		return latitudeStr;
//	}
//
//	public void setLongitudeStr(String longitudeStr) {
//		this.longitudeStr = longitudeStr;
//	}
//	
//	public void setLongitudeStr(double longitude) {
//		this.longitudeStr = longitude + "";
//	}
//
//	public String getLongitudeStr() {
//		return longitudeStr;
//	}
//	
//	public double getLatitude(){
//		return Double.parseDouble(latitudeStr);
//	}
//	
//	public double getLongitude(){
//		return Double.parseDouble(longitudeStr);
//	}
	
}
