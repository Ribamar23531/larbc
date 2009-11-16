package persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgis.LineString;
import org.postgis.PGgeometry;
import org.postgis.Point;

import persistence.hibernate.HibernateConfig;
import persistence.util.Coordenates;

public class LineRecover {
	
	private Connection dbConn;
	
	public LineRecover(){
		try {
			dbConn = ConnectionFactory.newConnection();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
//	private Point getPointByPGgeometry(PGgeometry pg) throws SQLException{		
//    	String aux[] = pg.toString().split(" ");
//    	String coordinates = aux[0].substring(6) + " " + aux[1].substring(0, aux[1].length() - 1);
//    	Point p = new Point(coordinates); 
//    	return p;
//	}
	
	private LineString getLineStringByPGgeometry(PGgeometry pg) throws SQLException{		
//    	String pointsSTR[] = pg.toString().split(",");
//    	Point[] points = new Point[pointsSTR.length];
//    	for (int i = 0; i < pointsSTR.length; i++) {
//			points[i] = new Point(pointsSTR[i]);
//		}
    	LineString ls = new LineString(pg.toString());
    	return ls;
	}
	
	public List<Coordenates> getLocation(long id) throws SQLException {	

		String sqlQuery = "SELECT l.location " + "FROM larbc_db."
				+ HibernateConfig.getCurrentSchema() + ".lines l "
				+ "WHERE l.id_line = " + id + ";";

		PreparedStatement s = dbConn.prepareStatement(sqlQuery);
		ResultSet rs = s.executeQuery();

		PGgeometry pg = null;
		while(rs.next()){
			pg = (PGgeometry) (rs.getObject("location"));
		}
		LineString ls = getLineStringByPGgeometry(pg);		
		Point[] points = ls.getPoints();
		List<Coordenates> coordenates = new ArrayList<Coordenates>(points.length);
		for (Point point : points) {
			coordenates.add(new Coordenates(point.getX(), point.getY()));
		}
		s.close();
		return coordenates;
	}
	
//	public Map<Long, String> getLocations() throws SQLException{
//		Map<Long, String> result = new HashMap<Long, String>();
//		String sqlQuery = "SELECT c.id_caso, c.location " + "FROM larbc_db."
//		+ HibernateConfig.getCurrentSchema() + ".casos c;";
//		PreparedStatement s = dbConn.prepareStatement(sqlQuery);
//		ResultSet rs = s.executeQuery();
//		while(rs.next()){			
//			Long id = new Long(rs.getLong("id_caso"));
//			PGgeometry pg = (PGgeometry) (rs.getObject("location"));
//			Point p = getPointByPGgeometry(pg);
//			String location = p.getX() + " " + p.getY();
//			result.put(id, location);
//		}
//		s.close();
//		return result;
//	}

}
