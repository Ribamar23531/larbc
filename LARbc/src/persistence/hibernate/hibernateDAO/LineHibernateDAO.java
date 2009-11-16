package persistence.hibernate.hibernateDAO;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import persistence.DAO.LineDAO;
import persistence.hibernate.HibernateConfig;
import persistence.jdbc.poi.LineRecover;
import beans.poi.Line;

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
			getLocation(line);
		}
		return lines;
	}
	
	@Override
	public void removeLine(Line line){		
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(line);
		transaction.commit();
		session.close();
		
	}
	
	private void getLocation(Line line) {
		LineRecover lr = new LineRecover();
		try {
			line.setVertexes(lr.getLocation(line.getIdLine()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void setLocation(Line line){
		//places the geometry column in the case witch has just been saved
		Session session = sf.openSession();
		String sqlQuery = 	"UPDATE larbc_db." + HibernateConfig.getCurrentSchema() + ".lines " +
							"SET location = GeometryFromText('LINESTRING(" + line.getLocation() +")',-1) " +
							"WHERE id_line = " + line.getIdLine() + ";";
		session.createSQLQuery(sqlQuery).executeUpdate();	
		session.close();
	}
	
	@Override
	public void saveLine(Line line){		
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		session.save(line);
		transaction.commit();
		session.close();
		
		setLocation(line);
	}

}
