package RBCTestes;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.AdministradorNotFoundException;
import exceptions.LoginAlreadyRegisteredException;

import beans.Administrador;
import beans.Caso;

import persistence.GerenteDePersistencia;
import persistence.util.Estado;
import rbcCycle.GerenteDeRBC;
import rbcCycle.caseElement.ImmobileSolution;

public class RetrieveTest {
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
		int tipoNegocio = 8;

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
		return caso;
	}
	
	private static Caso getCaso2() {
		String estado = Estado.PE.toString();
		String cidade = "cidade";
		String bairro = "bairro2";
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
		int tipoNegocio = 8;

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
		return caso;
	}

	@BeforeClass
	public static void configure(){
		persistenceManager = new GerenteDePersistencia(true);
		persistenceManager.removeAllAdministradores();
		persistenceManager.removeAllCasos();
		CBRManager = new GerenteDeRBC(true);
		caso1 = getCaso1();
		caso2 = getCaso2();
		administrador = new Administrador("login1", "senha1", "nome1");
		try {
			persistenceManager.saveAdministrador(administrador);
			persistenceManager.createCaso(administrador, caso1);
			persistenceManager.createCaso(administrador, caso2);
		} catch (AdministradorNotFoundException e) {
			e.printStackTrace();
		} catch (LoginAlreadyRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRetrieve(){
		List<ImmobileSolution> queryResult= CBRManager.doQuery(1, "", "", "bairro2", "", "", 0, 0, 0, 0, 0, 0, "", 0, 0);
		assertTrue(queryResult.size() == 1);
		for (ImmobileSolution immobileSolution : queryResult) {
			System.out.println(immobileSolution.toString());
			System.out.println();
		}
		persistenceManager.removeAllAdministradores();
		persistenceManager.removeAllCasos();
	}
	
	
}
