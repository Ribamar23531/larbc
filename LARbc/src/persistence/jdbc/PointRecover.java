package persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgis.PGgeometry;
import org.postgis.Point;

import persistence.hibernate.HibernateConfig;

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
	
	public String getLocation(long id) throws SQLException {	

		String sql = "SELECT c.location " + "FROM larbc_db."
				+ HibernateConfig.getCurrentSchema() + ".casos c "
				+ "WHERE c.id_caso = " + id + ";";

		PreparedStatement s = dbConn.prepareStatement(sql);
		ResultSet rs = s.executeQuery();

		PGgeometry pg = null;
		while(rs.next()){
			pg = (PGgeometry) (rs.getObject("location"));			
		}
		Point p = getPointByPGgeometry(pg);
		String location = p.getX() + " " + p.getY();
		s.close();
		return location;
	}

}
