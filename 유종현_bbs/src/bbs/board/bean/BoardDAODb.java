package bbs.board.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bbs.member.bean.MemberDAODb;

public class BoardDAODb {
	/*
		목차
		작업1 Singleton Start
		작업2 DB연결작업 
		작업3 자유게시판의 글의 수 받아오기
		작업4 자유게시판의 모든 내용 받아오기
		작업5 자유게시판 INSERT
		작업6 자유게시판 DELETE
		작업7 자유게시판 update할 내용 불러오기
		작업8 자유게시판 수정 후 내용 update
	*/
	
	//작업1 Singleton Start-------------------->
	private static BoardDAODb instance = new BoardDAODb();
	//Singleton패턴 구성 하나의 클래스로 하나의 객체만 생성하여 작업
	public static BoardDAODb getInstance() {return instance;}
		
	private BoardDAODb(){}
	//END Singleton ----------------------------->
		
	/*작업2 DB연결작업 ---------------------------------*/
	private Connection getConnection() throws Exception{
		//DBCP Pool API
		Context con = new InitialContext();
		Context envcon = (Context)con.lookup("java:comp/env");
		DataSource ds = (DataSource)envcon.lookup("jdbc/jsptest");
		//jspstudy는 집 학원 : jsptest
		return ds.getConnection();
	}
	/*END DB Connection------------------------------*/
	
	/*작업3 게시판의 글의 수 받아오기-----------------------*/
	public int getArticleCnt() {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		int rtIv = 0;
		
		try {
            conn = getConnection();
            System.out.println("컬럼 수 디비 연결됨");
            pstmt = conn.prepareStatement("select count(*) from board");
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
	
	/*작업4 게시판의 모든 내용 받아오기---------------------*/
	public List<BoardBeanDb> getArticle(int startRow, int pageSize) {
		//startRow는 셀렉트로 가져온 모든 열중 시작지점을 의미 만약 7이 왔고 전채 데이터가
		//15라면 7번째 데이터부터 읽어와야 한다. pageSize는 startRow부터 가져올 데이터의 갯수이다.
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BoardBeanDb> list = null;//데이터를 가져올 변수 리턴 값
        int rowCheck = 1;//startRow의 값에서 부터 데이터를 넣기 위해 횟수를 체크한다.
        try {
            conn = getConnection();
            System.out.println("글목록 DB 연결됨");
            pstmt = conn.prepareStatement(
            		"select * from board order by ref desc, re_step asc");
            rs = pstmt.executeQuery();
            System.out.println("시작점 : "+startRow);
            if(rs.next()) {
            	list = new ArrayList<BoardBeanDb>(pageSize);
            	//ArrayList 크기와 가져갈 데이터의 크기인 pageSize를 맞춰준다.
            	do{
            		if(rowCheck >= startRow) {
            			//rowCheck를 반복문에서 증가시키기 때문에 처음부터 증가시켜
            			//startRow와 숫자가 같아지면 데이터를 넣기 시작한다.
	            		BoardBeanDb bbd = new BoardBeanDb();
	                	bbd.setNum(rs.getInt(1));
	                	bbd.setWriter(rs.getString(2));
	                	bbd.setSubject(rs.getString(3));
	                	bbd.setPasswd(rs.getString(4));
	                	bbd.setReg_date(rs.getTimestamp(5));
	                	bbd.setReadcount(rs.getInt(6));
	                	bbd.setRef(rs.getInt(7));
	                	bbd.setRe_step(rs.getInt(8));
	                	bbd.setRe_level(rs.getInt(9));
	                	bbd.setContent(rs.getString(10));
	                	bbd.setIp(rs.getString(11));
	                	list.add(bbd);
            		}            		
                	rowCheck++;//횟수를 체크하기 위해 매 작업마다 증가시킴
                	if(rowCheck == pageSize+startRow) break;
                	//rowCheck와 pageSize+startRow가 같다면 더 이상 데이터를 넣을 필요가 없다.
            	} while(rs.next());           	      	
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
	
	/*작업5 게시판 INSERT------------------------------*/
	public int insertBoard(BoardBeanDb bbd) {
		int rtIv = 0;		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql="";
        
		int num = bbd.getNum();//제목글의 글번호 0일 경우 댓글이 아니다.
		int ref = bbd.getRef();//제목글의 그룹화 아이디
		int re_step = bbd.getRe_step();//그룹내의 글의순서
		int re_level = bbd.getRe_level();//글제목의 들여쓰기
		try {
            conn = getConnection();
            if (num!=0){//댓글 - 제목글의 글번호 가짐
  		      sql="update board set re_step=re_step+1 where ref= ? and re_step> ?";
              ps = conn.prepareStatement(sql);
              ps.setInt(1, ref);
  			  ps.setInt(2, re_step);
  			  ps.executeUpdate();
  			  re_step=re_step+1;
  			  re_level=re_level+1;
  		    }else{//제목글 - 글번호 없음
  			  re_step=0;
  			  re_level=0;
  		    }
            sql = "insert into board(num, writer,subject,passwd,reg_date,";
		    sql+="re_step,re_level, content, ip, ref) values(board_seq.nextval,?";
		    if(num == 0) sql+=",?,?,?,?,?,?,?,board_seq.currval)";
		    else sql+=",?,?,?,?,?,?,?,?)";
		    System.out.println(sql);
		    ps = conn.prepareStatement(sql);
		    ps.setString(1, bbd.getWriter());
		    ps.setString(2, bbd.getSubject());
		    ps.setString(3, bbd.getPasswd());
		    ps.setTimestamp(4, bbd.getReg_date());
		    ps.setInt(5, re_step);
		    ps.setInt(6, re_level);
		    ps.setString(7, bbd.getContent());
		    ps.setString(8, bbd.getIp());
		    if(num != 0) ps.setInt(9, ref);
		    rtIv = ps.executeUpdate();
		}catch(Exception ex) { ex.printStackTrace();
		}finally{
            if (ps != null) try{ ps.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
        }		
		return rtIv;
	}
	/*END 게시판 INSERT-------------------------------*/
	
	/*작업6 자유게시판 DELETE---------------------------*/
	public int deleteBoard(int num, int ref, String passwd) {
		int rtIv = 0;
		int refCnt= 0;
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql="";
        
        try {
        	conn = getConnection();        	
        	sql = "select passwd from board where num=?";
        	ps = conn.prepareStatement(sql);
        	ps.setInt(1, num);
        	rs = ps.executeQuery();
        	if(rs.next()) {//해당 값이 있다면
        		System.out.println(passwd);
        		System.out.println(rs.getString(1));
        		//암호가 일치 하는지 비교 일치하지 않다면 작업을 중지
        		if(!passwd.equals(rs.getString(1)))	rtIv=MemberDAODb.NOPASS;
	        	else {//암호가 일치할 경우 삭제작업을 계속한다.
	        		//계층구조가 있는지 확인해보기 위한 쿼리
	        		sql = "select count(*) from board where ref=?";
	        		ps = conn.prepareStatement(sql);
	        		ps.setInt(1, ref);
	        		rs = ps.executeQuery();
	        		if(rs.next()) {
	        			refCnt = rs.getInt(1);
	        			if(refCnt>1) {//계층이 존재한다면 계층을 위해 삭제처리 하지 않고 update처리
	        				System.out.println("refCnt : " + refCnt);
	        				System.out.println("계층존재 진입");	        				
	        				sql = "update board set subject=?, content=? where num=?";
	        				System.out.println(sql);
	        				ps = conn.prepareStatement(sql);
	        				System.out.println("delete - ps");
	        				ps.setString(1, "삭제된 글");
	        				ps.setString(2, "작성자가 삭제한 글입니다.");
	        				ps.setInt(3, num);
	        				System.out.println("num : " + num);
	        				rtIv = ps.executeUpdate();
	        				System.out.println("rtIv의 값 " + rtIv);
	        			}else if(refCnt==1) {//1일경우 혼자 있는 글로 삭제 처리한다.
	        				sql = "delete from board where num=?";
	        				ps = conn.prepareStatement(sql);
	        				ps.setInt(1, num);
	        				rtIv = ps.executeUpdate();//성공시 1이 리턴된다.
	        			}else rtIv = MemberDAODb.NOID;//ref값이 없을경우 1이 아닌수 리턴
	        		}//END IF select ref값을 받아올 때
	        	}//END ELSE 암호가 일치할 때
	        }//END IF select passwd값을 받아올 때
        }catch(Exception ex) { ex.printStackTrace();System.out.println("SQL에러");
		}finally{
			if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (ps != null) try{ ps.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
        }//END try-catch-finally
		return rtIv;
	}
	/*END  자유게시판 DELETE---------------------------*/
	
	/*작업7 자유게시판 update할 내용 불러오기---------------*/
	public BoardBeanDb updateGetInfo(int num) {
		BoardBeanDb bbd = new BoardBeanDb();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql="";
        
        try {
        	conn = getConnection();
        	sql = "select subject, content from board where num=?";
        	ps = conn.prepareStatement(sql);
        	ps.setInt(1, num);
        	rs = ps.executeQuery();
        	
        	if(rs.next()) {
        		bbd.setSubject(rs.getString(1));
        		bbd.setContent(rs.getString(2));
        	}
        }catch(Exception ex) { ex.printStackTrace();System.out.println("SQL에러");
		}finally{
			if (rs != null) try{ rs.close(); }catch(SQLException ex) {}
            if (ps != null) try{ ps.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
        }//END try-catch-finally
		return bbd;
	}
	/*END 자유게시판 update할 내용 불러오기----------------*/
	
	/*작업8 자유게시판 수정 후 내용 update-----------------*/
	public int updateBoard(int num, String subject, String content, String passwd) {
		int rtIv = 0;
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql="";
        
        try {
        	conn = getConnection();        	
        	sql = "select passwd from board where num=?";
        	ps = conn.prepareStatement(sql);
        	ps.setInt(1, num);
        	rs = ps.executeQuery();
        	if(rs.next()) {//해당 값이 있다면
        		System.out.println(passwd);
        		System.out.println(rs.getString(1));
        		//암호가 일치 하는지 비교 일치하지 않다면 작업을 중지
        		if(!passwd.equals(rs.getString(1)))	rtIv=MemberDAODb.NOPASS;
	        	else {//암호가 일치하는 경우
	        		sql = "update board set subject=?,content=? where num=?";
	        		ps = conn.prepareStatement(sql);
	        		ps.setString(1, subject);
	        		ps.setString(2, content);
	        		ps.setInt(3, num);
	        		rtIv = ps.executeUpdate();
	        	}
        	}//END IF SELECT로 찾은값이 있을 경우
        }catch(Exception ex) { ex.printStackTrace();System.out.println("SQL에러");
		}finally{			
            if (ps != null) try{ ps.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
        }//END try-catch-finally
		return rtIv;
	}
	/*END  자유게시판 수정 후 내용 update-----------------*/
}
