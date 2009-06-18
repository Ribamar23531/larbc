package persistenciaTestes;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.GerenteDePersistencia;
import persistence.util.Estado;
import beans.Administrador;
import beans.Caso;
import exceptions.CasoNotFoundException;

public class CasoTests {
	
	private static GerenteDePersistencia gerente;
	private static Caso caso;

	@BeforeClass
	public static void configurarTudo(){
		gerente = GerenteDePersistencia.getInstance(true);
		caso = new Caso();
		gerente.removeAllCasos();
	}
	
	@AfterClass
	public static void zerarTudo(){
		gerente.removeAllCasos();
	}	
	
	@Test
	public void testSave(){
		
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
		Administrador inseridoPor = new Administrador("login", "password", "nome");		
		
		
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
//		caso.setInseridoPor(inseridoPor);
		gerente.saveCaso(caso);		 
		if(gerente.getCasos().size() == 1){
			assertTrue(true);			
		}else{
			assertTrue(false);
		}		
		
	}

	@Test
	public void testUpdate(){
		caso.setCidade("differentCidade");
		try {
			gerente.updateCaso(caso);
			long id = caso.getIdCaso();			
			if(gerente.getCaso(id).getCidade().equals("differentCidade")){
				assertTrue(true);
			}else{
				assertTrue(false);
			}
		} catch (CasoNotFoundException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void TestRemove(){
		try {
			gerente.removeCaso(caso);
			if(gerente.getCasos().size() == 0){
				assertTrue(true);
			}else{
				assertTrue(false);
			}
		} catch (CasoNotFoundException e) {
			assertTrue(false);
		}
	}
	
}
