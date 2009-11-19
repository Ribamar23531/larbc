package persistenciaTestes;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.GerenteDePersistencia;
import persistence.util.Coordinates;
import beans.poi.Polygon;

public class PolygonTests {
	
	private static GerenteDePersistencia gerente;
	private static Polygon polygon;
	
	@BeforeClass
	public static void configure(){
		gerente = GerenteDePersistencia.getInstance(true);
		polygon = new Polygon();
		polygon.setName("name");
		polygon.setObs("obs");
		polygon.setType("type");
		
		List<Coordinates> coordinates = new ArrayList<Coordinates>();
		coordinates.add(new Coordinates(0, 0));
		coordinates.add(new Coordinates(0, 1));
		coordinates.add(new Coordinates(0, 2));
		coordinates.add(new Coordinates(0, 3));
		polygon.setVertexes(coordinates);
	}
	
	@AfterClass
	public static void deleteAll(){		
		gerente.removePolygon(polygon);		
		
	}
	
	@Test
	public void testSave(){		
		try{
			gerente.savePolygon(polygon);			
		}catch(Exception e){
			assertTrue(false);
		}
		assertTrue(true);
	}
	
	@Test
	public void getAllLines(){
		List<Polygon> polygons = new ArrayList<Polygon>();
		try{
			polygons = gerente.getPolygons();			
		}catch(Exception e){
			assertTrue(false);
		}
		if(polygons.size() == 1){
			assertTrue(true);			
		}else{
			assertTrue(false);
		}
	}

}
