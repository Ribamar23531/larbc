package persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgis.PGgeometry;

import persistence.hibernate.HibernateConfig;
import persistence.util.Coordenates;

public class SpatialQueries {
	
private Connection dbConn;
	
	public SpatialQueries(){
		try {
			dbConn = ConnectionFactory.newConnection();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	/*
	SELECT the_geom 
	FROM geom_table 
	WHERE ST_Distance(the_geom, GeomFromText('POINT(100000 200000)', -1)) < 100
	
	SELECT COUNT(*)
	FROM wikicrimes.wikicrimestable t
	WHERE INTERSECTS(GeometryFromText('POLYGON((10.0 10.0, -10.0 10.0, -10.0 -10.0, 10.0 -10.0, 10.0 10.0))', -1) ,t.localizacao)
	
	*/
	
	public int qtePointsByDistance(Coordenates coordenates, double distance) throws SQLException{		
		String pointString = "POINT(" + coordenates.getLatitude() + " " + coordenates.getLongitude()  + ")";
//		String query = 	"SELECT COUNT(*) " + 
//						"FROM larbc_db." + HibernateConfig.getCurrentSchema() + ".points p " +
//						"WHERE ST_Distance(location, GeomFromText('" + pointString + "', -1)) < "+ distance + ";";
		String query = 	"SELECT location " + 
		"FROM larbc_db." + HibernateConfig.getCurrentSchema() + ".points p " +
		"WHERE ST_Distance(location, GeomFromText('" + pointString + "', -1)) <= "+ distance + ";";
		PreparedStatement s = dbConn.prepareStatement(query);
		ResultSet rs = s.executeQuery();
		List<PGgeometry> points = new ArrayList<PGgeometry>();
		while(rs.next()){
			points.add((PGgeometry) (rs.getObject("location")));
		}
		return points.size();
	}
	
//	public List<Coordenates> getLocation(long id) throws SQLException {	
//
//		String sqlQuery = "SELECT p.location " + "FROM larbc_db."
//				+ HibernateConfig.getCurrentSchema() + ".polygons p "
//				+ "WHERE p.id_polygon = " + id + ";";
//
//		PreparedStatement s = dbConn.prepareStatement(sqlQuery);
//		ResultSet rs = s.executeQuery();
//
//		PGgeometry pg = null;
//		while(rs.next()){
//			pg = (PGgeometry) (rs.getObject("location"));
//		}
//		Polygon polygon = getPolygonByPGgeometry(pg);		
//		List<Coordenates> coordenates = new ArrayList<Coordenates>(polygon.numPoints());
//		for (int i = 0; i < polygon.numPoints(); i++) {
//			Point p = polygon.getPoint(i);
//			coordenates.add(new Coordenates(p.getX(), p.getY()));			
//		}		
//		s.close();
//		return coordenates;
//	}

}
