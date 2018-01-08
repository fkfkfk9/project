package bean.menu02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.page.Paging;

public class MajorBoardDAOBean {
	/*
		����
		�۾�1 Singleton Start
		�۾�2 DB�����۾� 	
		�۾�3 �ش������ �Խ����� ���� �� �޾ƿ���
		
	 */
	
	//�۾�1 Singleton Start-------------------->
	private static MajorBoardDAOBean instance = new MajorBoardDAOBean();
	//Singleton���� ���� �ϳ��� Ŭ������ �ϳ��� ��ü�� �����Ͽ� �۾�
	public static MajorBoardDAOBean getInstance() {
		return instance;
	}
	//END Singleton ----------------------------->

	/*�۾�2 DB�����۾� ---------------------------------*/
	private MajorBoardDAOBean() {}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/homepage");
		return ds.getConnection();
	}
	/*END DB Connection------------------------------*/
	
	/*�۾�3 �ش������ �Խ����� ���� �� �޾ƿ���-----------------------*/
	public int getarticleCnt(String classes) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		int rtIv = 0;
		
		try {
            conn = getConnection();
            System.out.println("�÷� �� ��� �����");
            pstmt = conn.prepareStatement("select count(*) from majorboard where classes=?");
            pstmt.setString(1, classes);
            rs = pstmt.executeQuery();

            if (rs.next()) 
            	rtIv= rs.getInt(1);
            	System.out.println(rtIv);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
        }
		return rtIv;
	}
	/*END �Խ����� ���� �� �޾ƿ���-----------------------*/
	
	/*�۾�4 �ش���� �Խ����� ��� ���� �޾ƿ���---------------------*/
	public List<MajorBoardDTOBean> getArticle(String classes, Paging paging) {
		//Paging Ŭ������ �������� ���� ������ �÷��� �����Ѵ�.
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql="";
        List<MajorBoardDTOBean> list = null;//�����͸� ������ ���� ���� ��
        try {
            conn = getConnection();
            System.out.println("�۸�� DB �����");
            //���ϴ� �÷��� �������� ���� sql�� rownum�� �̿��� ������ �� rownum�� �������� ������ ������ ���Ѵ�.
            sql = "SELECT mbnum, id, name, subject,content, regist_day,classes , hit FROM (" + 
            		" SELECT mbnum, id, name, subject, content, regist_day,classes , hit" + 
            		" , ROW_NUMBER() OVER(ORDER BY mbnum DESC) board_seq" + 
            		" FROM majorboard where classes=?) DS" + 
            		" WHERE board_seq BETWEEN ? AND ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, classes);
            //paging ���� �̿��ؼ� select���� ������ �κа� �����κ��� �����Ѵ�.
            pstmt.setInt(2, paging.getStartQuery());
            pstmt.setInt(3, paging.getEndQuery());
            rs = pstmt.executeQuery();
            System.out.println(paging.toString());
            if(rs.next()) {
            	list = new ArrayList<MajorBoardDTOBean>(paging.getPageSize());
            	//ArrayList ũ��� ������ �������� ũ���� pageSize�� �����ش�.
            	while(rs.next()){            		
        			MajorBoardDTOBean mbdto = new MajorBoardDTOBean();
        			mbdto.setmBnum(rs.getInt(1));
        			mbdto.setId(rs.getString(2));
        			mbdto.setName(rs.getString(3));
        			mbdto.setSubject(rs.getString(4));
        			mbdto.setContent(rs.getString(5));
        			mbdto.setRegist_day(rs.getDate(6));
        			mbdto.setClasses(rs.getString(7));
        			mbdto.setHit(rs.getInt(8));
                	list.add(mbdto);            		            		
            	} 	     	      	
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (pstmt != null) try{ pstmt.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
        }
        return list;
	}
	/*END �Խ����� ��� ���� �޾ƿ���----------------------*/
}
