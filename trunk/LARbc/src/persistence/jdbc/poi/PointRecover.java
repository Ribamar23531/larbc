package persistence.jdbc.poi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgis.PGgeometry;
import org.postgis.Point;

import persistence.hibernate.HibernateConfig;
import persistence.jdbc.ConnectionFactory;
import persistence.util.Coordenates;

public class PointRecover {
	
	private Connection dbConn;
	
	public PointRecover(){
		try {
			dbConn = ConnectionFactory.newConnection();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}	
	
	private Point getPointByPGgeometry(PGgeometry pg) throws SQLException{		
    	String aux[] = pg.toString().split(" ");
    	String coordinates = aux[0].substring(6) + " " + aux[1].substring(0, aux[1].length() - 1);
    	Point p = new Point(coordinates); 
    	return p;
	}
	
	public Coordenates getLocation(long id) throws SQLException {	

		String sqlQuery = "SELECT p.location " + "FROM larbc_db."
				+ HibernateConfig.getCurrentSchema() + ".points p "
				+ "WHERE p.id_point = " + id + ";";

		PreparedStatement s = dbConn.prepareStatement(sqlQuery);
		ResultSet rs = s.executeQuery();

		PGgeometry pg = null;
		while(rs.next()){
			pg = (PGgeometry) (rs.getObject("location"));
		}
		Point p = getPointByPGgeometry(pg);
		Coordenates coordenates = new Coordenates(p.getX(), p.getY());		
		return coordenates;
	}
	
	public boolean exists(double latitude, double longitude) throws SQLException {
		
		String sqlQuery = "SELECT p.location " + "FROM larbc_db."
		+ HibernateConfig.getCurrentSchema() + ".points p " + ";";

		PreparedStatement s = dbConn.prepareStatement(sqlQuery);
		ResultSet rs = s.executeQuery();

		List<PGgeometry> pg = new ArrayList<PGgeometry>();
		while (rs.next()) {
			pg.add((PGgeometry) (rs.getObject("location")));
		}
		List<Point> points = new ArrayList<Point>();		
		for (PGgeometry pGgeometry : pg) {
			points.add(getPointByPGgeometry(pGgeometry));		
		}
		for (Point point : points) {
			if(point.getX() == latitude && point.getY() == longitude){
				return true;
			}
		}
		return false;
	}


}
