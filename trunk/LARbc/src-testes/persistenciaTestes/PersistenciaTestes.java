package persistenciaTestes;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.GerenteDePersistencia;
import beans.Administrador;
import beans.Foto;
import exceptions.AdministradorNotFoundException;

public class PersistenciaTestes {
	
	private static GerenteDePersistencia gerente;

	@BeforeClass
	public static void configurarTudo(){
		gerente = GerenteDePersistencia.getInstance(true);		
		removeEveryThing();
	}
	
	@AfterClass
	public static void zerarTudo(){
		removeEveryThing();
	}
	
	private static void removeEveryThing(){
		gerente.removeAllAdministradores();
		gerente.removeAllFotos();
	}
	
	@Test
	public void testOperationsUnderAdministrador(){
		Administrador a = new Administrador("login1", "senha1", "nome1");
		try {
			gerente.saveAdministrador(a);
			Administrador admin = gerente.getAdministrador(a.getLogin());
			if(admin.getLogin().equals(a.getLogin()) && admin.getPassword().equals(a.getPassword()) && admin.getNome().equals(a.getNome())){
				assertTrue(true);
			}else{
				assertTrue(false);
			}
		} catch (Exception e) {
			
		}
		a.setNome("fulaninho");
		try {
			gerente.updateAdministrador(a);
			if(gerente.getAdministrador(a.getLogin()).equals("fulaninho")){
				assertTrue(true);
			}
		} catch (AdministradorNotFoundException e1) {
			a.setNome("fulaninho");
		}
		try {
			gerente.removeAdministrador(a);
			assertTrue(true);
		} catch (AdministradorNotFoundException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testOperationsUnderFotos(){
		long idCaso = 1;
		String path = "path";
		Foto foto = new Foto(idCaso, path);
		try {
			gerente.saveFoto(foto);
			Foto f = gerente.getFoto(idCaso, path);
			if(f.getIdCaso() == idCaso && f.getPath().equals(path)){
				assertTrue(true);
			}else{
				assertTrue(false);
			}
			Foto oldPicture = new Foto(f.getIdCaso(), f.getPath());
			f.setPath("differentPath");
			gerente.updateFoto(oldPicture, f);
			assertTrue(true);
			gerente.removeFoto(f);
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}		
	}	
	
}
