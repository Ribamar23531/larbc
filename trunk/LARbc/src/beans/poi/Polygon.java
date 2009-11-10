package beans.poi;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "polygons")
public class Polygon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_polygon", updatable = false, nullable = false)
	private long idPolygon;
	@Column(updatable = true, nullable = false)
	private String obs;
	@Transient
	private List<Vertex> vertexes;
	
	public Polygon(){}

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

	public void setVertexes(List<Vertex> vertexes) {
		this.vertexes = vertexes;
	}

	public List<Vertex> getVertexes() {
		return vertexes;
	}
	
	

}
