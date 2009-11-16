package persistenciaTestes;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.GerenteDePersistencia;
import persistence.util.Coordenates;
import beans.poi.Point;
import exceptions.PointAlreadySavedException;
import exceptions.PointNotFoundException;

public class PointTests {
	
	private static GerenteDePersistencia gerente;
	private static Point point;
	
	@BeforeClass
	public static void configure(){
		gerente = GerenteDePersistencia.getInstance(true);
		point = new Point();
		point.setName("name");
		point.setObs("obs");
		point.setType("type");
		
		point.setCoordenate(new Coordenates(0, 0));
	}
	
	@AfterClass
	public static void deleteAll(){		
		try {
			gerente.removePoint(point);
		} catch (PointNotFoundException e) {
			assertTrue(false);
		}
		
	}
	
	@Test
	public void testSave(){		
		try {
			gerente.savePoint(point);
		} catch (PointAlreadySavedException e) {
			assertTrue(false);
		}
		assertTrue(true);
	}
	
	@Test
	public void getAllPoints(){
		List<Point> points = new ArrayList<Point>();
		try{
			points = gerente.getPoints();
		}catch(Exception e){
			assertTrue(false);
		}
		if(points.size() == 2){
			assertTrue(true);			
		}else{
			assertTrue(false);
		}
	}

}
