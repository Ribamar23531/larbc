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
@Table(name = "lines")
public class Line {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_line", updatable = false, nullable = false)
	private long idLine;
	@Column(updatable = true, nullable = false)
	private String type;
	@Column(updatable = true, nullable = false)
	private String obs;
	@Transient
	private List<Vertex> vertexes;
	
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

	public void setVertexes(List<Vertex> vertexes) {
		this.vertexes = vertexes;
	}

	public List<Vertex> getVertexes() {
		return vertexes;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
