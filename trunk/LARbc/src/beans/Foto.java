package beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fotos")
public class Foto {
	
	@EmbeddedId
	private MyComposedKey myComposedKey;

	//construtor vazio necessario pelo hibernate
	public Foto(){}
	
	public Foto(long idCaso, String path){
		this.myComposedKey = new MyComposedKey(idCaso, path);
	}
	
	public long getIdCaso() {
		return this.myComposedKey.getIdCaso();
	}
	public void setIdCaso(long idCaso) {
		this.myComposedKey.setIdCaso(idCaso);
	}
	public String getPath() {
		return this.myComposedKey.getPath();
	}
	public void setPath(String path) {
		this.myComposedKey.setPath(path);
	}
	
	public MyComposedKey getMyComposedKey() {
		return myComposedKey;
	}

	public void setMyComposedKey(MyComposedKey myComposedKey) {
		this.myComposedKey = myComposedKey;
	}
	
	public String toString(){
		return this.getIdCaso() + ";" + this.getPath();
	}
	
	@Embeddable
	private class MyComposedKey implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@Column(name = "id_caso", updatable = true, nullable = false)
		private long idCaso;

		@Column(updatable = true, nullable = false)
		private String path;
		
		public MyComposedKey(long idCaso, String path){
			this.idCaso = idCaso;
			this.path = path;
		}		

		public void setIdCaso(long idCaso) {
			this.idCaso = idCaso;
		}

		public long getIdCaso() {
			return idCaso;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getPath() {
			return path;
		}
		
	}

}
