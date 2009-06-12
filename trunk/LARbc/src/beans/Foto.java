package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fotos")
public class Foto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_foto", updatable = false, nullable = false)
	private long idFoto;
	@Column(updatable = true, nullable = false)
	private String path;
	
	//construtor vazio necessario pelo hibernate
	public Foto(){}
	
	public Foto(String path){
		this.path = path;
	}
	
	public long getIdFoto() {
		return idFoto;
	}
	public void setIdFoto(long idFoto) {
		this.idFoto = idFoto;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

}
