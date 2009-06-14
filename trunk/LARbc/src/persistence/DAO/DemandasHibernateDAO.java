package persistence.DAO;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import beans.Demanda;
import exceptions.DemandaNotFoundException;

public class DemandasHibernateDAO extends HibernateDAO implements DemandaDAO{
	
	public DemandasHibernateDAO(boolean testing){
		super(testing);
	}
	
	@Override
	public Demanda getDemanda(long idDemanda) throws DemandaNotFoundException {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		Demanda demanda = (Demanda) session.load(Demanda.class, new Long(idDemanda));
		if(demanda == null){
			throw new DemandaNotFoundException();
		}
		transaction.commit();
		session.close();
		return demanda;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Demanda> getDemandas(){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
//		String consulta = "SELECT * FROM " 
//			+ this.schema + ".demandas a WHERE a.login = " + idDemanda + ";";
		List<Demanda> demandas = session.createQuery("from " + Demanda.class.getCanonicalName()).list();
//		List<Object[]> demanda = session.createSQLQuery(consulta).list();
		transaction.commit();
		session.close();
//		if(demanda.size() == 0){
////			throw new AdministradorNotFoundException();
//		}
//		String areaConst = (String) demanda.get(0)[1];
//		String areaTotal = (String) demanda.get(0)[2];
//		String bairro = (String) demanda.get(0)[3];
//		String banheiros = (String) demanda.get(0)[4];
//		String cidade = (String) demanda.get(0)[5];
//		String eMailCliente = (String) demanda.get(0)[6];
//		String estado = (String) demanda.get(0)[7];
//		String jahModerado = (String) demanda.get(0)[8];
//		String nome = (String) demanda.get(0)[9];
//		String nomeCliente = (String) demanda.get(0)[10];
//		String numero = (String) demanda.get(0)[11];
//		String preco = (String) demanda.get(0)[12];
//		String quartos = (String) demanda.get(0)[13];
//		String rua = (String) demanda.get(0)[14];
//		String suites = (String) demanda.get(0)[15];
//		String telefone = (String) demanda.get(0)[16];
//		String tipo = (String) demanda.get(0)[17];
//		String tipoNegocio = (String) demanda.get(0)[18];
//		String vagasGaragem = (String) demanda.get(0)[19];
//		
//		Demanda d = new Demanda();
//		d.setAreaConstruida(Float.parseFloat(areaConst));
//		d.setAreaTotal(Float.parseFloat(areaTotal));
//		d.setBairro(bairro);
//		d.setBanheiros(Integer.parseInt(banheiros));
//		d.setCidade(cidade);
//		d.setEmailCliente(eMailCliente);
//		d.setEstado(estado);
//		d.setJahModerado(Boolean.parseBoolean(jahModerado));
//		d.setNome(nome);
//		d.setNomeCliente(nomeCliente);
//		d.setNumero(Integer.parseInt(numero));
//		d.setPreco(Float.parseFloat(preco));
//		d.setQuartos(Integer.parseInt(quartos));
//		d.setRua(rua);
//		d.setSuites(Integer.parseInt(suites));
//		d.setTelefone(telefone);
//		d.setTipo(tipo);
//		d.setTipoNegocio(Integer.parseInt(tipoNegocio));
//		d.setVagasGaragem(Integer.parseInt(vagasGaragem));
		
		return demandas;
	}
	
	@Override
	public void saveDemanda(Demanda demanda) {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		session.save(demanda);
		transaction.commit();
		session.close();
		
	}

	@Override
	public void removeAllDemandas() {
		List<Demanda> demandas = getDemandas();
		for (Demanda demanda : demandas) {
			try {
				removeDemanda(demanda);
			} catch (DemandaNotFoundException e) {				
			}
		}
		
	}

	@Override
	public void removeDemanda(Demanda demanda) throws DemandaNotFoundException {
		getDemanda(demanda.getIdDemanda());//verifica se existe a foto a ser removida
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(demanda);
		transaction.commit();
		session.close();		
	}	

	@Override
	public void updateDemanda(Demanda demanda) throws DemandaNotFoundException {
		getDemanda(demanda.getIdDemanda());//verifica se existe a foto a ser removida
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(demanda);
		transaction.commit();
		session.close();		
	}	

}
