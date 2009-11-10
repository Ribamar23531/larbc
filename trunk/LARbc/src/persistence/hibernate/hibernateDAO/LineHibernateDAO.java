package persistence.hibernate.hibernateDAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import persistence.DAO.LineDAO;

import beans.poi.Line;
import beans.poi.Vertex;

public class LineHibernateDAO extends HibernateDAO implements LineDAO {

	public LineHibernateDAO(boolean testing) {
		super(testing);
	}
	
	public LineHibernateDAO() {
		super(false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Line> getLines() {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		List<Line> lines = session.createQuery("from " + Line.class.getCanonicalName()).list();
		transaction.commit();
		session.close();
		for (Line line : lines) {
			List<Vertex> vertexes = getVertexes(line);
			line.setVertexes(vertexes);
		}
		return lines;
	}

	@SuppressWarnings("unchecked")
	private List<Vertex> getVertexes(Line line) {
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.getNamedQuery("getVertexes");
        query.setLong("idVertex", line.getIdLine());        
        List<Vertex> vertexes = query.list();
        transaction.commit();
        session.close();
        return vertexes;
    }

	@Override
	public void removeLine(Line line){		
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(line);
		transaction.commit();
		session.close();
		
		removeVertexes(line);
	}	

	private void removeVertexes(Line line) {
		List<Vertex> vertexes = getVertexes(line);
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
	public void saveLine(Line line){		
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		session.save(line);
		transaction.commit();
		session.close();
		
		saveVertexes(line);
	}

	private void saveVertexes(Line line) {
		List<Vertex> vertexes = line.getVertexes();
		for (Vertex vertex : vertexes) {
			vertex.setIdVertex(line.getIdLine());
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
	public void updateLine(Line line){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(line);
		transaction.commit();
		session.close();
		
		updateVertexes(line);
	}

	private void updateVertexes(Line line) {
		List<Vertex> vertexes = getVertexes(line);
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
