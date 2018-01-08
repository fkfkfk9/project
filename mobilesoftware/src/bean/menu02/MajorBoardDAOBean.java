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
		목차
		작업1 Singleton Start
		작업2 DB연결작업 	
		작업3 해당과목의 게시판의 글의 수 받아오기
		
	 */
	
	//작업1 Singleton Start-------------------->
	private static MajorBoardDAOBean instance = new MajorBoardDAOBean();
	//Singleton패턴 구성 하나의 클래스로 하나의 객체만 생성하여 작업
	public static MajorBoardDAOBean getInstance() {
		return instance;
	}
	//END Singleton ----------------------------->

	/*작업2 DB연결작업 ---------------------------------*/
	private MajorBoardDAOBean() {}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/homepage");
		return ds.getConnection();
	}
	/*END DB Connection------------------------------*/
	
	/*작업3 해당과목의 게시판의 글의 수 받아오기-----------------------*/
	public int getarticleCnt(String classes) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		int rtIv = 0;
		
		try {
            conn = getConnection();
            System.out.println("컬럼 수 디비 연결됨");
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
	/*END 게시판의 글의 수 받아오기-----------------------*/
	
	/*작업4 해당과목 게시판의 모든 내용 받아오기---------------------*/
	public List<MajorBoardDTOBean> getArticle(String classes, Paging paging) {
		//Paging 클래스는 페이지에 따른 가져올 컬럼을 결정한다.
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql="";
        List<MajorBoardDTOBean> list = null;//데이터를 가져올 변수 리턴 값
        try {
            conn = getConnection();
            System.out.println("글목록 DB 연결됨");
            //원하는 컬럼만 가져오기 위한 sql문 rownum을 이용해 정렬한 후 rownum을 기준으로 가져올 범위를 정한다.
            sql = "SELECT mbnum, id, name, subject,content, regist_day,classes , hit FROM (" + 
            		" SELECT mbnum, id, name, subject, content, regist_day,classes , hit" + 
            		" , ROW_NUMBER() OVER(ORDER BY mbnum DESC) board_seq" + 
            		" FROM majorboard where classes=?) DS" + 
            		" WHERE board_seq BETWEEN ? AND ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, classes);
            //paging 빈을 이용해서 select문을 가져올 부분과 끝낼부분을 지정한다.
            pstmt.setInt(2, paging.getStartQuery());
            pstmt.setInt(3, paging.getEndQuery());
            rs = pstmt.executeQuery();
            System.out.println(paging.toString());
            if(rs.next()) {
            	list = new ArrayList<MajorBoardDTOBean>(paging.getPageSize());
            	//ArrayList 크기와 가져갈 데이터의 크기인 pageSize를 맞춰준다.
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
	/*END 게시판의 모든 내용 받아오기----------------------*/
}
