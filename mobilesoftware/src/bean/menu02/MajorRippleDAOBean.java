package bean.menu02;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MajorRippleDAOBean {
	/*
		����
		�۾�1 Singleton Start
		�۾�2 DB�����۾� 	
	 */
	
	//�۾�1 Singleton Start-------------------->
	private static MajorRippleDAOBean instance = new MajorRippleDAOBean();
	//Singleton���� ���� �ϳ��� Ŭ������ �ϳ��� ��ü�� �����Ͽ� �۾�
	public static MajorRippleDAOBean getInstance() {
		return instance;
	}
	//END Singleton ----------------------------->

	/*�۾�2 DB�����۾� ---------------------------------*/
	private MajorRippleDAOBean() {}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/homepage");
		return ds.getConnection();
	}
	/*END DB Connection------------------------------*/
}
