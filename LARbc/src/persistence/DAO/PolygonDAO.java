package persistence.DAO;

import java.util.List;

import beans.poi.Polygon;

public interface PolygonDAO {
	
	public void savePolygon(Polygon polygon);
	
	public void removePolygon(Polygon polygon);
	
	public List<Polygon> getPolygons();
	
	public void updatePolygon(Polygon polygon);

}
