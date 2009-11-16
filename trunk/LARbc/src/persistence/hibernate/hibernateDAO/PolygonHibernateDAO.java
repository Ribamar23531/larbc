package persistence.hibernate.hibernateDAO;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import persistence.DAO.PolygonDAO;
import persistence.hibernate.HibernateConfig;
import persistence.jdbc.poi.PolygonRecover;
import beans.poi.Polygon;

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
			getLocation(polygon);			
		}
		return polygons;
	}	

	@Override
	public void removePolygon(Polygon polygon) {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(polygon);
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
		
		setLocation(polygon);

	}
	
	private void setLocation(Polygon polygon) {
		//places the geometry column in the case witch has just been saved
		Session session = sf.openSession();
		String sqlQuery = 	"UPDATE larbc_db." + HibernateConfig.getCurrentSchema() + ".polygons " +
							"SET location = GeometryFromText('POLYGON((" + polygon.getLocation() +"))',-1) " +
							"WHERE id_polygon = " + polygon.getIdPolygon() + ";";
		session.createSQLQuery(sqlQuery).executeUpdate();	
		session.close();		
	}	
	
	private void getLocation(Polygon polygon) {
		PolygonRecover pr = new PolygonRecover();
		try {
			polygon.setVertexes(pr.getLocation(polygon.getIdPolygon()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
