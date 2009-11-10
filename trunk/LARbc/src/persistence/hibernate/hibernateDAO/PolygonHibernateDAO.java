package persistence.hibernate.hibernateDAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import persistence.DAO.PolygonDAO;
import beans.poi.Polygon;
import beans.poi.Vertex;

public class PolygonHibernateDAO extends HibernateDAO implements PolygonDAO {

	public PolygonHibernateDAO(boolean testing) {
		super(testing);
	}
	
	public PolygonHibernateDAO(){
		super(false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Polygon> getPolygons() {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		List<Polygon> polygons = session.createQuery("from " + Polygon.class.getCanonicalName()).list();
		transaction.commit();
		session.close();
		for (Polygon polygon : polygons) {
			List<Vertex> vertexes = getVertexes(polygon);
			polygon.setVertexes(vertexes);
		}
		return polygons;
	}
	
	@SuppressWarnings("unchecked")
	private List<Vertex> getVertexes(Polygon polygon) {
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.getNamedQuery("getVertexes");
        query.setLong("idVertex", polygon.getIdPolygon());
        List<Vertex> vertexes = query.list();
        transaction.commit();
        session.close();
        return vertexes;
    }

	@Override
	public void removePolygon(Polygon polygon) {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(polygon);
		transaction.commit();
		session.close();
		
		removeVertexes(polygon);

	}
	
	private void removeVertexes(Polygon polygon) {
		List<Vertex> vertexes = getVertexes(polygon);
		for (Vertex vertex : vertexes) {
			removeVertex(vertex);
		}
		
	}

	private void removeVertex(Vertex vertex) {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(vertex);
		transaction.commit();
		session.close();
		
	}

	@Override
	public void savePolygon(Polygon polygon) {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		session.save(polygon);
		transaction.commit();
		session.close();
		
		saveVertexes(polygon);

	}
	
	private void saveVertexes(Polygon polygon) {
		List<Vertex> vertexes = polygon.getVertexes();
		for (Vertex vertex : vertexes) {
			vertex.setIdVertex(polygon.getIdPolygon());
			saveVertex(vertex);
		}
		
	}

	private void saveVertex(Vertex vertex) {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		session.save(vertex);
		transaction.commit();
		session.close();		
	}

	@Override
	public void updatePolygon(Polygon polygon) {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(polygon);
		transaction.commit();
		session.close();
		
		updateVertexes(polygon);

	}
	
	private void updateVertexes(Polygon polygon) {
		List<Vertex> vertexes = getVertexes(polygon);
		for (Vertex vertex : vertexes) {
			updateVertex(vertex);
		}
		
	}

	private void updateVertex(Vertex vertex) {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(vertex);
		transaction.commit();
		session.close();
		
	}

}
