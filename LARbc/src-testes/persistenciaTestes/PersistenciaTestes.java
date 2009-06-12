package persistenciaTestes;

import static org.junit.Assert.assertTrue;
import modelo.Administrador;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.AdministradorNaoEncontradoException;

import persistencia.GerenteDePersistencia;

public class PersistenciaTestes {
	
	private static GerenteDePersistencia gerente;

	@BeforeClass
	public static void configurarTudo(){
		gerente = GerenteDePersistencia.getInstance(true);		
		gerente.apagarTodosAdministradores();
	}
	
	@AfterClass
	public static void zerarTudo(){
		gerente.apagarTodosAdministradores();
	}
	
	@Test
	public void testaGerenteDePersistencia(){
		Administrador a = new Administrador("login1", "senha1", "nome1");
		try {
			gerente.gravarAdministrador(a);
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
			gerente.atualizarAdministrador(a);
			if(gerente.getAdministrador(a.getLogin()).equals("fulaninho")){
				assertTrue(true);
			}
		} catch (AdministradorNaoEncontradoException e1) {
			a.setNome("fulaninho");
		}
		try {
			gerente.removerAdministrador(a);
			assertTrue(true);
		} catch (AdministradorNaoEncontradoException e) {
			assertTrue(false);
		}
	}
	
	
}
