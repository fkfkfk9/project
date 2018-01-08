package bean.menu04;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GalleyDAOBean {
	/*
		목차
		작업1 Singleton Start
		작업2 DB연결작업 	
	 */
	
	//작업1 Singleton Start-------------------->
	private static GalleyDAOBean instance = new GalleyDAOBean();
	//Singleton패턴 구성 하나의 클래스로 하나의 객체만 생성하여 작업
	public static GalleyDAOBean getInstance() {
		return instance;
	}
	//END Singleton ----------------------------->

	/*작업2 DB연결작업 ---------------------------------*/
	private GalleyDAOBean() {}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/homepage");
		return ds.getConnection();
	}
	/*END DB Connection------------------------------*/
}
