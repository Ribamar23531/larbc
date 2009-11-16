package persistence.DAO;

import java.util.List;

import beans.poi.Point;
import exceptions.PointNotFoundException;

public interface PointDAO {
	
	public void savePoint(Point point);
	
	public void removePoint(Point point) throws PointNotFoundException;
	
	public List<Point> getPoints();
	
//	public void updatePoint(Point point) throws PointAlreadySavedException;

}
