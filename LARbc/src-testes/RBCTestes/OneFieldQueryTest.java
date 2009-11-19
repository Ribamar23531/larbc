package RBCTestes;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.GerenteDePersistencia;
import persistence.util.Coordinates;
import persistence.util.Estado;
import rbcCycle.GerenteDeRBC;
import beans.Administrador;
import beans.Caso;
import exceptions.AdministradorNotFoundException;
import exceptions.LoginAlreadyRegisteredException;
import exceptions.PermissionDeniedException;

public class OneFieldQueryTest {
	private static GerenteDePersistencia persistenceManager;
	private static GerenteDeRBC CBRManager;
	private static Caso caso1;
	private static Caso caso2;
	private static Administrador administrador;
	
	private static Caso getCaso1() {
		String estado = Estado.PE.toString();
		String cidade = "cidade";
		String bairro = "bairro";
		String rua = "rua";
		int numero = 4;
		String nome = "nome";
		float areaConst = 1;
		float areaTotal = 2;
		int vagasGaragem = 9;
		int quartos = 6;
		int suites = 7;
		int baneiros = 3;
		String tipo = "tipo";
		float preco = 5;
		String tipoNegocio = "tipo negocio";
		Coordinates location = new Coordinates(0, 0);

		Caso caso = new Caso();
		caso.setAreaConstruida(areaConst);
		caso.setAreaTotal(areaTotal);
		caso.setBanheiros(baneiros);
		caso.setBairro(bairro);
		caso.setCidade(cidade);
		caso.setEstado(estado);
		caso.setNome(nome);
		caso.setNumero(numero);
		caso.setPreco(preco);
		caso.setQuartos(quartos);
		caso.setRua(rua);
		caso.setSuites(suites);
		caso.setTipo(tipo);
		caso.setTipoNegocio(tipoNegocio);
		caso.setVagasGaragem(vagasGaragem);
		caso.setLocation(location);
		return caso;
	}
	
	private static Caso getCaso2() {
		String estado = Estado.PE.toString();
		String cidade = "cidade";
		String bairro = "bairro";
		String rua = "rua";
		int numero = 4;
		String nome = "nome";
		float areaConst = 1;
		float areaTotal = 2;
		int vagasGaragem = 9;
		int quartos = 6;
		int suites = 7;
		int baneiros = 3;
		String tipo = "tipo";
		float preco = 6;
		String tipoNegocio = "tipo negocio";
		Coordinates location = new Coordinates(0, 0);

		Caso caso = new Caso();
		caso.setAreaConstruida(areaConst);
		caso.setAreaTotal(areaTotal);
		caso.setBanheiros(baneiros);
		caso.setBairro(bairro);
		caso.setCidade(cidade);
		caso.setEstado(estado);
		caso.setNome(nome);
		caso.setNumero(numero);
		caso.setPreco(preco);
		caso.setQuartos(quartos);
		caso.setRua(rua);
		caso.setSuites(suites);
		caso.setTipo(tipo);
		caso.setTipoNegocio(tipoNegocio);
		caso.setVagasGaragem(vagasGaragem);
		caso.setLocation(location);
		return caso;
	}

	@BeforeClass
	public static void configure(){
		persistenceManager = new GerenteDePersistencia(true);
		persistenceManager.removeNotRoots();
		persistenceManager.removeAllCasos();
//		persistenceManager.resetSystemPassword();
		CBRManager = new GerenteDeRBC(true);
		caso1 = getCaso1();
		caso2 = getCaso2();
		administrador = new Administrador("login1", "senha1", "nome1");
		try {
			persistenceManager.saveAdministrador(new Administrador("root", "root", "Root", "true"), administrador);
			persistenceManager.createCaso(administrador, caso1);
			persistenceManager.createCaso(administrador, caso2);
		} catch (AdministradorNotFoundException e) {
			assertTrue(false);
		} catch (LoginAlreadyRegisteredException e) {
			assertTrue(false);
		} catch (PermissionDeniedException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testRetrieve(){
		List<Caso> queryResult= CBRManager.doQuery(1, "", "", "bairro", "", "", 0, 0, 0, 0, 0, 0, "", 0, "");
		assertTrue(queryResult.size() == 1);
	}
	
	@Test
	public void testRetrieve2(){
		List<Caso> queryResult = CBRManager.doQuery(2, "", "", "", "", "", 2, 0, 0, 0, 0, 0, "", 0, "");
		assertTrue(queryResult.size() == 2);
	}
	
	@Test
	public void testRetrieve3(){
		List<Caso> queryResult = CBRManager.doQuery(2, "", "", "", "", "", 0, 0, 0, 0, 0, 0, "", 1, "");
		assertTrue(queryResult.size() == 2);
		for (Caso caseReturned : queryResult) {
			System.out.println("Price: " + caseReturned.getPreco());
		}
	}
	
	@AfterClass
	public static void clean(){
		persistenceManager.removeAllAdministradores();
		persistenceManager.removeAllCasos();
	}
	
	
}
