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
@NamedQueries( {@NamedQuery(name = "getPoint", query = "select p from Point p where p.latitude = :latitude and p.longitude = :longitude")})
public class Point {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_point", updatable = false, nullable = false)
	private long idPoint;
	@Column(updatable = true, nullable = false)
	private String type;
	@Column(updatable = true, nullable = false)
	private String obs;
	@Column(updatable = true, nullable = false)
	private double latitude;
	@Column(updatable = true, nullable = false)
	private double longitude;
	
	
	//construtor vazio necessario pelo hibernate
	public Point(){}
	
	public Point(double latitude, double longitude){
		this.setObs("");
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

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
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
