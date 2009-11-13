package beans.poi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "points")
@NamedQueries( {@NamedQuery(name = "getPoint", query = "select p from Point p where p.latitudeStr = :latitude AND p.longitudeStr = :longitude")})
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
	@Column(updatable = true, nullable = false)
	private String latitudeStr;
	@Column(updatable = true, nullable = false)
	private String longitudeStr;
	
	
	//construtor vazio necessario pelo hibernate
	public Point(){}
	
	public Point(double latitude, double longitude){
		this.setObs("");
		this.setLatitudeStr(latitude + "");
		this.setLongitudeStr(longitude + "");
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

	public void setLatitudeStr(String latitudeStr) {
		this.latitudeStr = latitudeStr;
	}
	
	public void setLatitudeStr(double latitude) {
		this.latitudeStr = latitude + "";
	}

	public String getLatitudeStr() {
		return latitudeStr;
	}

	public void setLongitudeStr(String longitudeStr) {
		this.longitudeStr = longitudeStr;
	}
	
	public void setLongitudeStr(double longitude) {
		this.longitudeStr = longitude + "";
	}

	public String getLongitudeStr() {
		return longitudeStr;
	}
	
	public double getLatitude(){
		return Double.parseDouble(latitudeStr);
	}
	
	public double getLongitude(){
		return Double.parseDouble(longitudeStr);
	}
	
}
