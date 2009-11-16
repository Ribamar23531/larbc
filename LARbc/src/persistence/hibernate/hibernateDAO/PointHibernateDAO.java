package persistence.hibernate.hibernateDAO;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import persistence.DAO.PointDAO;
import persistence.hibernate.HibernateConfig;
import persistence.jdbc.poi.PointRecover;
import beans.poi.Point;
import exceptions.PointNotFoundException;

public class PointHibernateDAO extends HibernateDAO implements PointDAO {

	public PointHibernateDAO(boolean testing) {
		super(testing);
		createDowntownIfNeeded();
	}
	
	public PointHibernateDAO(){
		super(false);
		createDowntownIfNeeded();
	}

	private void createDowntownIfNeeded() {
		Point downtown = new Point(-7.219900245183879, -35.88412642478943);
		downtown.setName("");
		downtown.setObs("Centro comercial");
		downtown.setType("DOWNTOWN");		
		
		if(!exists(-7.219900245183879, -35.88412642478943)){
			savePoint(downtown);			
		}
		

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Point> getPoints() {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		List<Point> points = session.createQuery("from " + Point.class.getCanonicalName()).list();
		transaction.commit();
		session.close();
		
		for (Point point : points) {
			getLocation(point);
		}
		
		return points;
	}

	private void getLocation(Point point) {
		PointRecover pr = new  PointRecover();
		try {
			point.setCoordenate(pr.getLocation(point.getIdPoint()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void removePoint(Point point) throws PointNotFoundException {
		try{
			Session session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(point);
			transaction.commit();
			session.close();			
		}catch(Exception e){
			throw new PointNotFoundException();
			
		}		

	}
	
	private boolean exists(double latitude, double longitude) {
		PointRecover pr = new PointRecover();
		try {
			if(pr.exists(latitude, longitude)){
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}
	
	private void setLocation(Point point){
		//places the geometry column in the case witch has just been saved
		Session session = sf.openSession();
		String sqlQuery = 	"UPDATE larbc_db." + HibernateConfig.getCurrentSchema() + ".points " +
							"SET location = GeometryFromText('POINT(" + point.toString() +")',-1) " +
							"WHERE id_point = " + point.getIdPoint() + ";";
		session.createSQLQuery(sqlQuery).executeUpdate();	
		session.close();
	}

	@Override
	public void savePoint(Point point){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		session.save(point);
		transaction.commit();
		session.close();
		
		setLocation(point);

	}

}
