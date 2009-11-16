package persistence.jdbc.poi;

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
import persistence.jdbc.ConnectionFactory;
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
	
	private LineString getLineStringByPGgeometry(PGgeometry pg) throws SQLException{
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


}
