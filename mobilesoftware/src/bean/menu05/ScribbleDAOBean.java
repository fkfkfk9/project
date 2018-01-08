package bean.menu05;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ScribbleDAOBean {
	/*
		����
		�۾�1 Singleton Start
		�۾�2 DB�����۾� 	
	 */
	
	//�۾�1 Singleton Start-------------------->
	private static ScribbleDAOBean instance = new ScribbleDAOBean();
	//Singleton���� ���� �ϳ��� Ŭ������ �ϳ��� ��ü�� �����Ͽ� �۾�
	public static ScribbleDAOBean getInstance() {
		return instance;
	}
	//END Singleton ----------------------------->

	/*�۾�2 DB�����۾� ---------------------------------*/
	private ScribbleDAOBean() {}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/homepage");
		return ds.getConnection();
	}
	/*END DB Connection------------------------------*/
}
