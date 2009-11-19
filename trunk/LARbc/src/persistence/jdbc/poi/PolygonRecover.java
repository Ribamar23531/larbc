package persistence.jdbc.poi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgis.PGgeometry;
import org.postgis.Point;
import org.postgis.Polygon;

import persistence.hibernate.HibernateConfig;
import persistence.jdbc.ConnectionFactory;
import persistence.util.Coordinates;

public class PolygonRecover {
	
	private Connection dbConn;
	
	public PolygonRecover(){
		try {
			dbConn = ConnectionFactory.newConnection();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	private Polygon getPolygonByPGgeometry(PGgeometry pg) throws SQLException{
		Polygon polygon = new Polygon(pg.toString());
    	return polygon;
	}
	
	public List<Coordinates> getLocation(long id) throws SQLException {	

		String sqlQuery = "SELECT p.location " + "FROM larbc_db."
				+ HibernateConfig.getCurrentSchema() + ".polygons p "
				+ "WHERE p.id_polygon = " + id + ";";

		PreparedStatement s = dbConn.prepareStatement(sqlQuery);
		ResultSet rs = s.executeQuery();

		PGgeometry pg = null;
		while(rs.next()){
			pg = (PGgeometry) (rs.getObject("location"));
		}
		Polygon polygon = getPolygonByPGgeometry(pg);		
		List<Coordinates> coordenates = new ArrayList<Coordinates>(polygon.numPoints());
		for (int i = 0; i < polygon.numPoints(); i++) {
			Point p = polygon.getPoint(i);
			coordenates.add(new Coordinates(p.getX(), p.getY()));			
		}		
		s.close();
		return coordenates;
	}


}
