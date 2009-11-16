package persistenciaTestes;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.GerenteDePersistencia;
import persistence.util.Coordenates;
import beans.poi.Line;

public class LineTests {
	
	private static GerenteDePersistencia gerente;
	private static Line line;
	
	@BeforeClass
	public static void configure(){
		gerente = GerenteDePersistencia.getInstance(true);
		line = new Line();
		line.setName("name");
		line.setObs("obs");
		line.setType("type");
		
		List<Coordenates> coordinates = new ArrayList<Coordenates>();
		coordinates.add(new Coordenates(0, 0));
		coordinates.add(new Coordenates(0, 1));
		coordinates.add(new Coordenates(0, 2));
		coordinates.add(new Coordenates(0, 3));
		line.setVertexes(coordinates);
	}
	
	@AfterClass
	public static void deleteAll(){		
		gerente.removeLine(line);		
		
	}
	
	@Test
	public void testSave(){		
		try{
			gerente.saveLine(line);			
		}catch(Exception e){
			assertTrue(false);
		}
		assertTrue(true);
	}
	
	@Test
	public void getAllLines(){
		List<Line> lines = new ArrayList<Line>();
		try{
			lines = gerente.getLines();			
		}catch(Exception e){
			assertTrue(false);
		}
		if(lines.size() == 1){
			assertTrue(true);			
		}else{
			assertTrue(false);
		}
	}

}
