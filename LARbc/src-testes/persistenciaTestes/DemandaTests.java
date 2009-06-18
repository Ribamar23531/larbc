package persistenciaTestes;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.GerenteDePersistencia;
import persistence.util.Estado;
import beans.Demanda;
import exceptions.DemandaNotFoundException;

public class DemandaTests {
	
	private static GerenteDePersistencia gerente;
	private static Demanda d;

	@BeforeClass
	public static void configurarTudo(){
		gerente = GerenteDePersistencia.getInstance(true);
		d = new Demanda();
		gerente.removeAllDemandas();
	}
	
	@AfterClass
	public static void zerarTudo(){
		gerente.removeAllDemandas();
	}	
	
	@Test
	public void testSave(){
		
		float areaConst = 1;
		float areaTotal = 2;
		int baneiros = 3;
		String bairro = "bairro";
		String cidade = "cidade";
		String eMailCliente = "emailcliente";
		String estado = Estado.PE.toString();
		boolean jahModerado = false;
		String nome = "nome";
		String nomeCliente = "nomecliente";
		int numero = 4;
		float preco = 5;
		int quartos = 6;
		String rua = "rua";
		int suites = 7;
		String telefone = "telefone";
		String tipo = "tipo";
		int tipoNegocio = 8;
		int vagasGaragem = 9;		
		d.setAreaConstruida(areaConst);
		d.setAreaTotal(areaTotal);
		d.setBanheiros(baneiros);
		d.setBairro(bairro);
		d.setCidade(cidade);
		d.setEmailCliente(eMailCliente);
		d.setEstado(estado);
		d.setJahModerado(jahModerado);
		d.setNome(nome);
		d.setNomeCliente(nomeCliente);
		d.setNumero(numero);
		d.setPreco(preco);
		d.setQuartos(quartos);
		d.setRua(rua);
		d.setSuites(suites);
		d.setTelefone(telefone);
		d.setTipo(tipo);
		d.setTipoNegocio(tipoNegocio);
		d.setVagasGaragem(vagasGaragem);
		gerente.saveDemanda(d);
//		d = gerente.getDemandas().get(0);
		List<Demanda> demandas = gerente.getDemandas();
		int size = demandas.size(); 
		if(size == 0){
			assertTrue(false);
		}else{
			assertTrue(true);			
		}		
		
	}

	@Test
	public void testUpdate(){
		d.setCidade("differentCidade");
		try {
			gerente.updateDemanda(d);
			long id = d.getIdDemanda();
			Demanda dAux = gerente.getDemanda(id);
			if(dAux.getCidade().equals("differentCidade")){
				assertTrue(true);
			}else{
				assertTrue(false);
			}
		} catch (DemandaNotFoundException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void TestRemove(){
		try {
			List<Demanda> demandas = gerente.getDemandas();
			int size = demandas.size(); 
			gerente.removeDemanda(d);
			if(gerente.getDemandas().size() == size - 1){
				assertTrue(true);
			}else{
				assertTrue(false);
			}
		} catch (DemandaNotFoundException e) {
			assertTrue(false);
		}
	}
	
}
