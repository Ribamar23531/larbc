package persistence.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import beans.Administrador;
import beans.Caso;
import beans.Demanda;
import beans.Foto;


public class HibernateConfig {
	
	private static boolean testing = false;
	private static SessionFactory sessionFactory = null;	
	private static final String SCHEMA = "larbcschema";
	private static final String TESTE_SCHEMA = "larbctesteschema";
	
	private static void configure(){
		try {
			AnnotationConfiguration config = new AnnotationConfiguration();			
			config.setProperty("hibernate.dialect",	"org.hibernate.dialect.MySQLDialect");
			config.setProperty("hibernate.connection.driver_class",	"com.mysql.jdbc.Driver");
			if(!testing){
				config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/LARbcSchema");				
			}else{
				config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/larbctesteschema");
			}
			config.setProperty("hibernate.connection.username", "larbc");
			config.setProperty("hibernate.connection.password", "123456");
			config.setProperty("hibernate.connection.pool_size", "1");
			config.setProperty("hibernate.connection.autocommit", "true");
			config.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
			config.setProperty("hibernate.hbm2ddl.auto", "update");
			config.setProperty("hibernate.show_sql", "true");
			config.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");
			config.setProperty("hibernate.current_session_context_class", "thread");

			// Mapped classes
			config.addAnnotatedClass(Administrador.class);
			config.addAnnotatedClass(Foto.class);
			config.addAnnotatedClass(Demanda.class);
			config.addAnnotatedClass(Caso.class);
			
			sessionFactory = config.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory(boolean testando) {
		if(sessionFactory == null){
			testing = testando;
			configure();
		}
		return sessionFactory;
	}	

	public static String getSchema(){
		return SCHEMA;
	}
	
	public static String getTesteSchema(){
		return TESTE_SCHEMA;
	}
}