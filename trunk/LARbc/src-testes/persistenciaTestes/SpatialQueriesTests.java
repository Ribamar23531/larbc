package persistenciaTestes;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.GerenteDePersistencia;
import persistence.jdbc.SpatialQueries;
import persistence.util.Coordenates;
import beans.poi.Point;
import exceptions.PointAlreadySavedException;
import exceptions.PointNotFoundException;

public class SpatialQueriesTests {
	
	private static GerenteDePersistencia gerente;
	private static Point point1;
	private static Point point2;
	private static Point point3;
	private static Point point4;
	
	@BeforeClass
	public static void configure(){
		gerente = GerenteDePersistencia.getInstance(true);
		point1 = new Point();
		point1.setName("name");
		point1.setObs("obs");
		point1.setType("type");		
		point1.setCoordenate(new Coordenates(0, 0));
		
		point2 = new Point();
		point2.setName("name");
		point2.setObs("obs");
		point2.setType("type");		
		point2.setCoordenate(new Coordenates(0, 1));
		
		point3 = new Point();
		point3.setName("name");
		point3.setObs("obs");
		point3.setType("type");		
		point3.setCoordenate(new Coordenates(1, 0));
		
		point4 = new Point();
		point4.setName("name");
		point4.setObs("obs");
		point4.setType("type");		
		point4.setCoordenate(new Coordenates(-1, 0));
		
		savePoints();
	}
	
	@AfterClass
	public static void deleteAll(){		
		try {
			gerente.removePoint(point1);
			gerente.removePoint(point2);
			gerente.removePoint(point3);
			gerente.removePoint(point4);
		} catch (PointNotFoundException e) {
			assertTrue(false);
		}
		
	}
		
	private static void savePoints(){		
		try {
			gerente.savePoint(point1);
			gerente.savePoint(point2);
			gerente.savePoint(point3);
			gerente.savePoint(point4);
		} catch (PointAlreadySavedException e) {
			assertTrue(false);
		}		
	}
	
	@Test
	public void testDistance(){
		SpatialQueries sq = new SpatialQueries();
		int qte = 0;
		try {
			qte = sq.qtePointsByDistance(new Coordenates(0, 0), 1);			
		} catch (SQLException e) {
			assertTrue(false);
		}
		if(qte == 4){
			assertTrue(true);
		}
	}

}
