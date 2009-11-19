package beans.poi;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import persistence.util.Coordinates;

@Entity
@Table(name = "lines")
public class Line {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_line", updatable = false, nullable = false)
	private long idLine;
	@Column(updatable = true, nullable = false)
	private String name;
	@Column(updatable = true, nullable = false)
	private String type;
	@Column(updatable = true, nullable = false)
	private String obs;
	@Transient
	private List<Coordinates> vertexes;
	
	public Line(){}

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
	
	public void setVertexes(List<Coordinates> vertexes) {
		this.vertexes = vertexes;
	}

	public List<Coordinates> getVertexes() {
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
		for (Coordinates coordenates : this.vertexes) {
			location += coordenates.toString() + ",";
		}
		int endIndex = location.lastIndexOf(",");
		return location.substring(0, endIndex);
	}

}
