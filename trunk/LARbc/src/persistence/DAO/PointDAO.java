package persistence.DAO;

import java.util.List;

import exceptions.PointAlreadySavedException;
import exceptions.PointNotFoundException;

import beans.poi.Point;

public interface PointDAO {
	
	public void savePoint(Point point) throws PointAlreadySavedException;
	
	public void removePoint(Point point) throws PointNotFoundException;
	
	public List<Point> getPoints();
	
	public void updatePoint(Point point) throws PointAlreadySavedException;

}
