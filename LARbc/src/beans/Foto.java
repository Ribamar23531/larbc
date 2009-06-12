package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fotos")
public class Foto {
	
	@Id	
	@Column(name = "id_caso", updatable = false, nullable = false)
	private long idCaso;
//	@Id
	@Column(updatable = true, nullable = false)
	private String path;
	
	//construtor vazio necessario pelo hibernate
	public Foto(){}
	
	public Foto(long idCaso, String path){
		this.idCaso = idCaso;
		this.path = path;
	}
	
	public long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(long idFoto) {
		this.idCaso = idFoto;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String toString(){
		return this.idCaso + ";" + this.path;
	}

}
