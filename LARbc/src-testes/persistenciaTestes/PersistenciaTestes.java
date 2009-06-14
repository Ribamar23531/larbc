package persistenciaTestes;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.GerenteDePersistencia;
import persistence.util.Estado;
import beans.Administrador;
import beans.Demanda;
import beans.Foto;
import exceptions.AdministradorNotFoundException;
import exceptions.DemandaNotFoundException;

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
		gerente.removeAllDemandas();
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
	
	@Test
	public void testOperationsUnderDemandas(){
		
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
		Demanda d = new Demanda();
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
		d = gerente.getDemandas().get(0);
		List<Demanda> demandas = gerente.getDemandas();
		int size = demandas.size(); 
		if(size == 0){
			assertTrue(false);
		}else{
			assertTrue(true);
//			d.setCidade("differentCidade");
//			try {
//				gerente.updateDemanda(d);
//				if(gerente.getDemanda(d.getIdDemanda()).getCidade().equals("differentCidade")){
//					assertTrue(true);
//				}else{
//					assertTrue(false);
//				}
//			} catch (DemandaNotFoundException e) {
//				assertTrue(false);
//			}
			try {
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
		
//		gerente.getDemanda(idDemanda)
//		long idCaso = 1;
//		String path = "path";
//		Foto foto = new Foto(idCaso, path);
//		try {
//			gerente.saveFoto(foto);
//			Foto f = gerente.getFoto(idCaso, path);
//			if(f.getIdCaso() == idCaso && f.getPath().equals(path)){
//				assertTrue(true);
//			}else{
//				assertTrue(false);
//			}
//			Foto oldPicture = new Foto(f.getIdCaso(), f.getPath());
//			f.setPath("differentPath");
//			gerente.updateFoto(oldPicture, f);
//			assertTrue(true);
//			gerente.removeFoto(f);
//			assertTrue(true);
//		} catch (Exception e) {
//			assertTrue(false);
//		}		
	}	
	
}
