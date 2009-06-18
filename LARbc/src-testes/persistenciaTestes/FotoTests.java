package persistenciaTestes;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.FotoNotFoundException;

import persistence.GerenteDePersistencia;
import beans.Foto;

public class FotoTests {
	
	private static GerenteDePersistencia gerente;
	private static Foto foto;
	private static long idCaso;
	private static String path;

	@BeforeClass
	public static void configurarTudo(){
		gerente = GerenteDePersistencia.getInstance(true);	
		idCaso = 1;
		path = "path";
		foto = new Foto(idCaso, path);
		gerente.removeAllFotos();
	}
	
	@AfterClass
	public static void zerarTudo(){
		gerente.removeAllFotos();
	}
	
	@Test
	public void testSave(){		
		try {
			gerente.saveFoto(foto);
			Foto f = gerente.getFoto(idCaso, path);
			if(f.getIdCaso() == idCaso && f.getPath().equals(path)){
				assertTrue(true);
			}else{
				assertTrue(false);
			}			
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void testUpdate(){
		Foto f;
		try {
			f = gerente.getFoto(idCaso, path);
			path = "differentPath";
			Foto oldPicture = new Foto(f.getIdCaso(), f.getPath());
			f.setPath(path);
			gerente.updateFoto(oldPicture, f);
			if(gerente.getFoto(f.getIdCaso(), f.getPath()).getPath().equals(path)){
				assertTrue(true);				
			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testRemove(){
		Foto f;
		try {
			f = gerente.getFoto(idCaso, path);
			gerente.removeFoto(f);
		} catch (FotoNotFoundException e) {
			assertTrue(false);
		}
		assertTrue(true);
	}
	
}
