package beans.poi;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import persistence.util.Coordenates;


@Entity
@Table(name = "polygons")
public class Polygon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_polygon", updatable = false, nullable = false)
	private long idPolygon;
	@Column(updatable = true, nullable = false)
	private String name;
	@Column(updatable = true, nullable = false)
	private String type;
	@Column(updatable = true, nullable = false)
	private String obs;
	@Transient
	private List<Coordenates> vertexes;
	
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

	public void setVertexes(List<Coordenates> vertexes) {
		this.vertexes = vertexes;
	}

	public List<Coordenates> getVertexes() {
		return vertexes;
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
	
	public String getLocation(){
		String location = "";
		for (Coordenates coordenates : this.vertexes) {
			location += coordenates.toString() + ",";
		}		
		location += this.vertexes.get(0).toString();
		return location;
	}

}
