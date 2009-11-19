package persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.postgis.PGgeometry;
import org.postgis.Point;

import persistence.hibernate.HibernateConfig;
import persistence.util.Coordinates;

public class PointCasoRecover {
	
	private Connection dbConn;
	
	public PointCasoRecover(){
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
	
	public Coordinates getLocation(long id) throws SQLException {	

		String sqlQuery = "SELECT c.location " + "FROM larbc_db."
				+ HibernateConfig.getCurrentSchema() + ".casos c "
				+ "WHERE c.id_caso = " + id + ";";

		PreparedStatement s = dbConn.prepareStatement(sqlQuery);
		ResultSet rs = s.executeQuery();

		PGgeometry pg = null;
		while(rs.next()){
			pg = (PGgeometry) (rs.getObject("location"));
		}
		Point p = getPointByPGgeometry(pg);
		Coordinates location = new Coordinates(p.getX(), p.getY());
		s.close();
		return location;
	}
	
	public Map<Long, Coordinates> getLocations() throws SQLException{
		Map<Long, Coordinates> result = new HashMap<Long, Coordinates>();
		String sqlQuery = "SELECT c.id_caso, c.location " + "FROM larbc_db."
		+ HibernateConfig.getCurrentSchema() + ".casos c;";
		PreparedStatement s = dbConn.prepareStatement(sqlQuery);
		ResultSet rs = s.executeQuery();
		while(rs.next()){			
			Long id = new Long(rs.getLong("id_caso"));
			PGgeometry pg = (PGgeometry) (rs.getObject("location"));
			Point p = getPointByPGgeometry(pg);
			result.put(id, new Coordinates(p.getX(), p.getY()));			
		}
		s.close();
		return result;
	}

}
