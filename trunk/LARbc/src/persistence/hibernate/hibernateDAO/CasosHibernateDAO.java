package persistence.hibernate.hibernateDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import persistence.DAO.CasoDAO;
import persistence.hibernate.HibernateConfig;
import persistence.jdbc.PointRecover;
import beans.Administrador;
import beans.Caso;
import beans.Foto;
import exceptions.CasoNotFoundException;

public class CasosHibernateDAO extends HibernateDAO implements CasoDAO{
	
	public CasosHibernateDAO(boolean testing){
		super(testing);
	}
	
	public CasosHibernateDAO(){
		super(false);
	}
	
	private void setLocation(Caso caso){
		//places the geometry column in the case witch has just been saved
		Session session = sf.openSession();
		String sqlQuery = 	"UPDATE larbc_db." + HibernateConfig.getCurrentSchema() + ".casos " +
							"SET location = GeometryFromText('POINT(" + caso.getLocation().replaceAll(",", "")+")',-1) " +
							"WHERE id_caso = " + caso.getIdCaso() + ";";
		session.createSQLQuery(sqlQuery).executeUpdate();	
		session.close();
	}
	
	@Override
	public void saveCaso(Caso caso) {
		//creates the case
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		session.save(caso);		
		transaction.commit();
		session.close();
		
		setLocation(caso);
		
	}
	
	@Override
	public void removeCaso(Caso caso) throws CasoNotFoundException {
		getCaso(caso.getIdCaso());//verifica se existe o caso a ser removido
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(caso);
		transaction.commit();
		session.close();
		
	}

	@Override
	public Caso getCaso(long idCaso) throws CasoNotFoundException {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		Caso caso = (Caso) session.get(Caso.class, new Long(idCaso));
		if(caso == null){
			throw new CasoNotFoundException();
		}
		transaction.commit();
		session.close();
		return caso;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Caso> getAllCasos(){
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		List<Caso> casos = session.createQuery("from " + Caso.class.getCanonicalName()).list();
		transaction.commit();
		session.close();
		PointRecover pr = new PointRecover();
		try {
			Map<Long, String> locations = pr.getLocations();
			for (Caso caso : casos) {
				caso.setLocation(locations.get(caso.getIdCaso()));
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return casos;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Caso> getCasos(long adminId) {
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.getNamedQuery("getCasosByAdmin");
        query.setLong("idAdministrador", adminId);        
        List<Caso> casos = query.list();
        transaction.commit();
        session.close();        
        return casos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Foto> getFotos(Caso caso) {
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.getNamedQuery("getFotos");
        query.setLong("idCaso", caso.getIdCaso());
        List<Foto> casos = query.list();
        transaction.commit();
        session.close();        
        return casos;
	}	
	
	@Override
	public void updateCaso(Caso caso) throws CasoNotFoundException {
		if(caso.getIdCaso() == 0){
			caso.setIdCaso(getId(caso));
		}
		getCaso(caso.getIdCaso());//verifica se existe o caso a ser removida
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(caso);		
		transaction.commit();
		session.close();
		
		setLocation(caso);
		
	}

	@SuppressWarnings("unchecked")
	private long getId(Caso caso) {
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.getNamedQuery("getId");
        query.setString("estado", caso.getEstado());
        query.setString("cidade", caso.getCidade());
        query.setString("bairro", caso.getBairro());
        query.setString("rua", caso.getRua());
        query.setInteger("numero", caso.getNumero());
        query.setString("nome", caso.getNome());
        query.setFloat("areaConstruida", caso.getAreaConstruida());
        query.setFloat("areaTotal", caso.getAreaTotal());
        query.setInteger("vagasGaragem", caso.getVagasGaragem());
        query.setInteger("quartos", caso.getQuartos());
        query.setInteger("suites", caso.getSuites());
        query.setInteger("banheiros", caso.getBanheiros());
        query.setString("tipo", caso.getTipo());
        query.setFloat("preco", caso.getPreco());
        query.setString("tipoNegocio", caso.getTipoNegocio());
        List<Caso> casos = query.list();
        transaction.commit();
        session.close();
        if(casos.size() > 0){
        	return casos.get(0).getIdCaso();
        }
		return 0;
	}
	
	@Override
	public String getCasoLocation(long id) throws SQLException {
		PointRecover pr = new PointRecover();
		String location = pr.getLocation(id);		
		return location;


	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeAllCasos() {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		List<Administrador> casos = session.createQuery("from " + Caso.class.getCanonicalName()).list();
		for (int i = 0; i < casos.size(); i++) {		
			session.delete(casos.get(i));
			transaction.commit();
			transaction = session.beginTransaction();
		}
		session.close();
		
	}	

}
