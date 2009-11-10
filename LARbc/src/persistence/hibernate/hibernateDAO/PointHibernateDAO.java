package persistence.hibernate.hibernateDAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import persistence.DAO.PointDAO;

import beans.poi.Point;
import exceptions.PointAlreadySavedException;
import exceptions.PointNotFoundException;

public class PointHibernateDAO extends HibernateDAO implements PointDAO {

	public PointHibernateDAO(boolean testing) {
		super(testing);
	}
	
	public PointHibernateDAO(){
		super(false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Point> getPoints() {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		List<Point> points = session.createQuery("from " + Point.class.getCanonicalName()).list();
		transaction.commit();
		session.close();
		return points;
	}

	@Override
	public void removePoint(Point point) throws PointNotFoundException {
		if(!exists(point)){
			throw new PointNotFoundException();
		}
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(point);
		transaction.commit();
		session.close();

	}

	@SuppressWarnings("unchecked")
	private boolean exists(Point point) {
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.getNamedQuery("getPoint");
        query.setString("location", point.getLocation());
        List<Point> points = query.list();
        transaction.commit();
        session.close();
        if(points.size() != 0){
            return true;
        }
        return false;
		
	}

	@Override
	public void savePoint(Point point) throws PointAlreadySavedException {
		if(exists(point)){
			throw new PointAlreadySavedException();
		}
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		session.save(point);
		transaction.commit();
		session.close();

	}

	@Override
	public void updatePoint(Point point) throws PointAlreadySavedException {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		if(exists(point)){
			throw new PointAlreadySavedException();
		}
		session.update(point);
		transaction.commit();
		session.close();

	}

}
