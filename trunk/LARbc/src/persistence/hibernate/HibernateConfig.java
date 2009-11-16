package persistence.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import beans.Administrador;
import beans.Caso;
import beans.Demanda;
import beans.Foto;
import beans.poi.Line;
import beans.poi.Point;
import beans.poi.Polygon;
import beans.poi.Vertex;


public class HibernateConfig {
	
	private static boolean testing = false;
	private static SessionFactory sessionFactory = null;	
	private static String currentSchema;
	private static final String USER_NAME = "larbc";
	private static final String PASSWORD = "123456";
	private static final String URL_DRIVER = "org.postgresql.Driver";
	private static final String URL_DB = "jdbc:postgresql://localhost:5432/larbc_db";
	
	private static void configure(){
		try {
			AnnotationConfiguration config = new AnnotationConfiguration();			
			config.setProperty("hibernate.dialect",	"org.hibernate.dialect.PostgreSQLDialect");
			config.setProperty("hibernate.connection.driver_class",	URL_DRIVER);
			config.setProperty("hibernate.connection.url", URL_DB);
			if(!testing){
				currentSchema = "larbcschema";
				config.setProperty("hibernate.default_schema", currentSchema);				
			}else{				
				currentSchema = "larbctestschema";
				config.setProperty("hibernate.default_schema", currentSchema);
			}
			config.setProperty("hibernate.connection.username", USER_NAME);
			config.setProperty("hibernate.connection.password", PASSWORD);
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
			config.addAnnotatedClass(Point.class);
			config.addAnnotatedClass(Line.class);
			config.addAnnotatedClass(Polygon.class);
			config.addAnnotatedClass(Vertex.class);		
			
			sessionFactory = config.buildSessionFactory();
			createGeometryColumns(currentSchema, sessionFactory);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	private static void createGeometryColumns(String currentSchema, SessionFactory sf){
		String casosColumn = "SELECT AddGeometryColumn('" + currentSchema + "','casos','location',-1,'POINT',2);";
		String pointColumn = "SELECT AddGeometryColumn('" + currentSchema + "','points','location',-1,'POINT',2);";
		String lineColumn = "SELECT AddGeometryColumn('" + currentSchema + "','lines','location',-1,'LINESTRING',2);";
		try{
			sf.openSession().createSQLQuery(casosColumn).list();
			sf.openSession().createSQLQuery(pointColumn).list();
			sf.openSession().createSQLQuery(lineColumn).list();
		}catch(Exception e){}
	}
	
	public static SessionFactory getSessionFactory(boolean testando) {
		if(sessionFactory == null){
			testing = testando;
			configure();
		}
		return sessionFactory;
	}
	
	public static String getUserName(){
		return USER_NAME;
	}
	
	public static String getPassword(){
		return PASSWORD;
	}
	
	public static String getCurrentSchema(){
		return currentSchema;
	}
	
	public static String getUrlDriver(){
		return URL_DRIVER;
	}

	public static String getUrlDb(){
		return URL_DB;
	}
	
}