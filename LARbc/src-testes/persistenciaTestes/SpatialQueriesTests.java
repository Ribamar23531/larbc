package persistenciaTestes;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.GerenteDePersistencia;
import persistence.util.Coordenates;
import beans.poi.Line;
import beans.poi.Point;
import exceptions.PointAlreadySavedException;
import exceptions.PointNotFoundException;

public class SpatialQueriesTests {
	
	private static GerenteDePersistencia gerente;
	private static Point point1;
	private static Point point2;
	private static Point point3;
	private static Point point4;
	private static Line line;
	
	@BeforeClass
	public static void configure(){
		
		gerente = GerenteDePersistencia.getInstance(true);
		deleteAll();
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
		
		line = new Line();
		line.setName("name");
		line.setObs("obs");
		line.setType("type");
		List<Coordenates> vertexes = new ArrayList<Coordenates>();
		vertexes.add(new Coordenates(0, 1));
		vertexes.add(new Coordenates(1, 1));
		vertexes.add(new Coordenates(2, 1));
		vertexes.add(new Coordenates(3, 1));
		line.setVertexes(vertexes);
		
		saveAll();
		
	}	

	@AfterClass
	public static void clear(){		
		deleteAll();		
	}
	
	private static void deleteAll() {
		List<Point> points = gerente.getPoints();
		for (Point point : points) {
			try {
				gerente.removePoint(point);
			} catch (PointNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Line> lines = gerente.getLines();
		for (Line line : lines) {
			gerente.removeLine(line);
		}	
		
	}
		
	private static void saveAll(){		
		try {
			gerente.savePoint(point1);
			gerente.savePoint(point2);
			gerente.savePoint(point3);
			gerente.savePoint(point4);			
		} catch (PointAlreadySavedException e) {
			assertTrue(false);
		}
		gerente.saveLine(line);
	}
	
	@Test
	public void testDistance(){
		gerente.setPOIDistance(1);
		int qte = 0;
		try {
			qte = gerente.qteOfNearPOIByType(point1.getCoordenate(), point1.getType());			
		} catch (SQLException e) {
			assertTrue(false);
		}
		if(qte == 4){
			assertTrue(true);
		}else{
			assertTrue(false);
		}
	}

}
