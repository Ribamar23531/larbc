package beans.poi;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "vertexes")
@NamedQueries( { @NamedQuery(name = "getVertexes", query = "select v from Vertex v where v.myComposedKey.idVertex = :idVertex") })
public class Vertex {	
	
	@EmbeddedId
	private MyComposedKey myComposedKey;
	
	public Vertex(){}
	
	public Vertex(long idVertex, int index, String location){
		this.myComposedKey = new MyComposedKey(idVertex, index, location);
	}
	
	public void setMyComposedKey(MyComposedKey myComposedKey) {
		this.myComposedKey = myComposedKey;
	}

	public MyComposedKey getMyComposedKey() {
		return myComposedKey;
	}
	
	public void setIdVertex(long idVertex) {
		this.myComposedKey.idVertex = idVertex;
	}

	public long getIdVertex() {
		return myComposedKey.idVertex;
	}
	
	public void setIndex(long index) {
		this.myComposedKey.index = index;
	}
	
	public long getIndex() {
		return myComposedKey.index;
	}

	public void setLocation(String location) {
		this.myComposedKey.location = location;
	}

	public String getLocation() {
		return myComposedKey.location;
	}
	
	@Embeddable	
	public static class MyComposedKey implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		
		@Column(name = "id_vertex", updatable = true, nullable = false)
		private long idVertex;
		
		@Column(name = "index", updatable = true, nullable = false)
		private long index;

		@Column(updatable = true, nullable = false)
		private String location;
		
		public MyComposedKey(){}
		
		public MyComposedKey(long idVertex, int index, String location){
			this.idVertex = idVertex;
			this.index = index;
			this.location = location;
		}

		public void setIdVertex(long idVertex) {
			this.idVertex = idVertex;
		}

		public long getIdVertex() {
			return idVertex;
		}

		public void setIndex(long index) {
			this.index = index;
		}
		
		public long getIndex() {
			return index;
		}
		
		public void setLocation(String location) {
			this.location = location;
		}

		public String getLocation() {
			return location;
		}

		
	}

}
