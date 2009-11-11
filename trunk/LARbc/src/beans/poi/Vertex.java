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
	
	public Vertex(long idVertex, int index, double latitude, double longitude){
		this.myComposedKey = new MyComposedKey(idVertex, index, latitude, longitude);
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

	public void setLatitude(double latitude){
		this.myComposedKey.setLatitude(latitude);
	}
	
	public double getLatitude(){
		return this.myComposedKey.getLatitude();
	}
	
	public void setLongitude(double longitude){
		this.myComposedKey.setLongitude(longitude);
	}
	
	public double getLongitude(){
		return this.myComposedKey.getLongitude();
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
		private double latitude;
		
		@Column(updatable = true, nullable = false)
		private double longitude;
		
		public MyComposedKey(){}
		
		public MyComposedKey(long idVertex, int index, double latitude, double longitude){
			this.idVertex = idVertex;
			this.index = index;
			this.setLatitude(latitude);
			this.setLongitude(longitude);
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

}
