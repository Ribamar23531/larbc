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
@NamedQueries( {@NamedQuery(name = "getPoint", query = "select p from Point p where p.location = :location")})
public class Point {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_point", updatable = false, nullable = false)
	private long idPoint;
	@Column(updatable = true, nullable = false)
	private String obs;
	@Column(updatable = true, nullable = false)
	private String location;	
	
	//construtor vazio necessario pelo hibernate
	public Point(){}
	
	public Point(String location){
		this.setObs("");
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

}
