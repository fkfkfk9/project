package bean.menu03;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class NoticeDAOBean {
	/*
		����
		�۾�1 Singleton Start
		�۾�2 DB�����۾� 	
	 */
	
	//�۾�1 Singleton Start-------------------->
	private static NoticeDAOBean instance = new NoticeDAOBean();
	//Singleton���� ���� �ϳ��� Ŭ������ �ϳ��� ��ü�� �����Ͽ� �۾�
	public static NoticeDAOBean getInstance() {
		return instance;
	}
	//END Singleton ----------------------------->

	/*�۾�2 DB�����۾� ---------------------------------*/
	private NoticeDAOBean() {}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/homepage");
		return ds.getConnection();
	}
	/*END DB Connection------------------------------*/
}
