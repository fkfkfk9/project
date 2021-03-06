package bean.menu06;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OtoRippleDAOBean {
	/*
		목차
		작업1 Singleton Start
		작업2 DB연결작업 	
	 */
	
	//작업1 Singleton Start-------------------->
	private static OtoRippleDAOBean instance = new OtoRippleDAOBean();
	//Singleton패턴 구성 하나의 클래스로 하나의 객체만 생성하여 작업
	public static OtoRippleDAOBean getInstance() {
		return instance;
	}
	//END Singleton ----------------------------->

	/*작업2 DB연결작업 ---------------------------------*/
	private OtoRippleDAOBean() {}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/homepage");
		return ds.getConnection();
	}
	/*END DB Connection------------------------------*/
}
