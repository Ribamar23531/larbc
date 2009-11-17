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

	private double distance;
	
	public SpatialQueries(){
		this.distance = 0.018045289547661728;
		try {
			dbConn = ConnectionFactory.newConnection();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	public int qteOfNearPOIByType(Coordenates coordenates, String type) throws SQLException{
		int qte = 0;
		qte += qteNear(coordenates, type, "points");
		qte += qteNear(coordenates, type, "lines");
		qte += qteNear(coordenates, type, "polygons");
		return qte;
	}
	
	public void setDistance(double distance){
		this.distance = distance;
	}
	
	private int qteNear(Coordenates coordenates, String type, String table) throws SQLException{
		String pointString = "POINT(" + coordenates.getLatitude() + " " + coordenates.getLongitude()  + ")";
		String query = 	"SELECT location " + 
		"FROM larbc_db." + HibernateConfig.getCurrentSchema() + "." + table + " p " +
		"WHERE ST_Distance(location, GeomFromText('" + pointString + "', -1)) <= "+ distance + " AND " +
				"p.type = " + type + " AND NOT equals(p.location, GeomFromText('" + pointString + "', -1));";
		PreparedStatement s = dbConn.prepareStatement(query);
		ResultSet rs = s.executeQuery();
		List<PGgeometry> pgGeometries = new ArrayList<PGgeometry>();
		while(rs.next()){
			pgGeometries.add((PGgeometry) (rs.getObject("location")));
		}
		return pgGeometries.size();
	}


}
