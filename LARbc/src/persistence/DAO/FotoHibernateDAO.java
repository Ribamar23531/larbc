package persistence.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import beans.Administrador;
import beans.Foto;
import exceptions.FotoAlreadySavedException;
import exceptions.FotoNotFoundException;

public class FotoHibernateDAO extends HibernateDAO implements FotoDAO{
	
	public FotoHibernateDAO(boolean testing){
		super(testing);
	}
	
	public FotoHibernateDAO(){
		super(false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Foto getFoto(long idCaso, String path) throws FotoNotFoundException {
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.getNamedQuery("foto.idcaso_e_path");
        query.setLong("id_caso", idCaso);
        query.setString("path", path);
        List<Foto> fotos = query.list();
        transaction.commit();
        session.close();
        if(fotos.size() == 0){
            throw new FotoNotFoundException();
        }
        return fotos.get(0);
//		Session session = sf.openSession();
//		Transaction transaction = session.beginTransaction();
//		String consulta = "SELECT * FROM " 
//			+ this.schema + ".fotos f WHERE f.id_caso = '" + idCaso + "' AND f.path = '" + path + "';";
//		List<Object[]> foto = session.createSQLQuery(consulta).list();
//		transaction.commit();
//		session.close();
////		Foto foto = (Foto) session.load(Foto.class, new Long(id));
//		if(foto.size() == 0){
//			throw new FotoNotFoundException();
//		}
//		String longSTR = foto.get(0)[0].toString();
//		long idFoto = Long.parseLong(longSTR);		
//		String pathFoto = (String) foto.get(0)[1];
//		return new Foto(idFoto, pathFoto);
	}
	
	@Override
	public void saveFoto(Foto foto) throws FotoAlreadySavedException {
		try {
			Foto f = getFoto(foto.getIdCaso(), foto.getPath());
			if(f.getPath().equals(foto.getPath())){
				throw new FotoAlreadySavedException();
			}
		} catch (FotoNotFoundException e) {}
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		session.save(foto);
		transaction.commit();
		session.close();
	}

	@Override
	public void removeFoto(Foto foto) throws FotoNotFoundException {
		getFoto(foto.getIdCaso(), foto.getPath());//verifica se existe a foto a ser removida
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(foto);		
		transaction.commit();
		session.close();
		
	}	

	@Override
	public void updateFoto(Foto oldPicture, Foto newPicture) throws FotoNotFoundException, FotoAlreadySavedException {
		getFoto(oldPicture.getIdCaso(), oldPicture.getPath());//verifica se existe a foto a ser removida
		removeFoto(oldPicture);
		saveFoto(newPicture);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void removeAllFotos() {
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();		
		List<Administrador> fotos = session.createQuery("from " + Foto.class.getCanonicalName()).list();
		for (int i = 0; i < fotos.size(); i++) {		
			session.delete(fotos.get(i));
			transaction.commit();
			transaction = session.beginTransaction();
		}
		session.close();
		
	}

}
