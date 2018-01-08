package bbs.member.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import work.crypt.BCrypt;//work.
import work.crypt.SHA256;

public class MemberDAODb {
	static public final int CONFIRM = 1;//db작업이 정상 처리되었을때
	static public final int NOPASS = 0;//패스워드가 틀린경우
	static public final int NOID = -1;//아이디가 틀린경우
	/*
		목차
		작업1 Singleton Start
		작업2 DB연결작업 
		작업3 회원 인증
		작업4 아이디 중복 체크
		작업5 회원가입 정보 insert
		작업6 ID로 본인정보 가져오기
		작업7 ID로 데이터 삭제하기
		작업8 회원정보 업데이트하기
	*/
	
	//작업1 Singleton Start-------------------->
	private static MemberDAODb instance = new MemberDAODb();
	//Singleton패턴 구성 하나의 클래스로 하나의 객체만 생성하여 작업
	public static MemberDAODb getInstance() {return instance;}
		
	private MemberDAODb(){}
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
	
	/*작업3 회원 인증 ------------------------------*/
	public int userCheck(String id, String passwd) {//리턴값을 숫자로 해준다.
		String sql = "";//sql문을 정의하기 위한 변수
		int rtIv = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = getConnection();
			String shaPass = sha.getSha256(passwd.getBytes());
			sql = "select passwd from member2 where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String dbpasswd= rs.getString("passwd"); 
				if(BCrypt.checkpw(shaPass,dbpasswd))
					rtIv = CONFIRM; //인증성공
				else
					rtIv = NOPASS; //비밀번호 틀림
			}else {
				rtIv = NOID;//아이디 틀림
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (ps != null) try { ps.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return rtIv;//결과값 리턴
	}
	/*END 회원 인증 -------------------------------*/
	
	/*작업4 아이디 중복 체크 ------------------------------*/
	public int idCheck(String id) {
		String sql = "";//sql문을 정의하기 위한 변수
		int rtIv = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			sql = "select count(*) from member2 where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) >= 1) {
					rtIv = NOID; //중복된 아이디가 있음 -1
				}else if(rs.getInt(1) == 0){
					rtIv = CONFIRM; //중복된 아이디가 없음 1
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (ps != null) try { ps.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return rtIv;
	}
	/*END 아이디 중복 체크 ------------------------------*/
	
	/*작업5 회원가입 정보 insert -------------------------*/
	public int insertMember(String id, String passwd, String name, String addr, String tel) {
		String sql = "";//sql문을 정의하기 위한 변수
		int result = 0;
		Timestamp reg_date = new Timestamp(System.currentTimeMillis());
		Connection conn = null;
		PreparedStatement ps = null;
		SHA256 sha = SHA256.getInsatnce();
		try {
			String orgPass = passwd;
            String shaPass = sha.getSha256(orgPass.getBytes());
        	String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
			conn = getConnection();
			sql = "insert into MEMBER2 values(?, ?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, id);ps.setString(2, bcPass);ps.setString(3, name);
			ps.setTimestamp(4, reg_date);ps.setString(5, addr);ps.setString(6, tel);
			System.out.println(id + "," + name + "," + reg_date + "," + addr + "," + tel);
			System.out.println(bcPass);
			result = ps.executeUpdate();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
            if (ps != null) try { ps.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return result;
	}
	/*END 회원가입 정보 insert -------------------------*/
	
	/*작업6 ID로 본인정보 가져오기 -------------------------*/
	public MemberBeanDb MemberInfo(String id) {
		String sql = "";//sql문을 정의하기 위한 변수
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberBeanDb mbd = null;
		try {
			conn = getConnection();
			sql = "select * from member2 where id=?";			
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				mbd = new MemberBeanDb();
				mbd.setId(rs.getString(1));
				mbd.setPasswd(rs.getString(2));
				mbd.setName(rs.getString(3));
				mbd.setReg_date(rs.getTimestamp(4));
				mbd.setAddr(rs.getString(5));
				mbd.setTel(rs.getString(6));
			}
		}catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (ps != null) try { ps.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return mbd;
	}	
	/*END ID로 본인정보 가져오기 --------------------------*/
	
	/*작업7 ID로 데이터 삭제하기 --------------------------*/
	public int deleteMember(String id) {
		String sql = "";//sql문을 정의하기 위한 변수
		int rtIv = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = getConnection();
			sql = "delete from member2 where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rtIv = ps.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
            if (ps != null) try { ps.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return rtIv;
	}
	/*END ID로 데이터 삭제하기 --------------------------*/
	/*작업8 회원정보 업데이트하기 -------------------------*/
	public int updatemember(MemberBeanDb mbd, String newpasswd) {
		System.out.println(newpasswd+"메소드 진입 성공");
		String sql = "";//sql문을 정의하기 위한 변수
		int rtIv = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = getConnection();
			System.out.println(mbd.getId() + "DB연결 성공");
			String shaPass = sha.getSha256(mbd.getPasswd().getBytes());
			sql = "select passwd from member2 where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, mbd.getId());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("passwd"));
				String dbpasswd= rs.getString("passwd"); 
				if(BCrypt.checkpw(shaPass,dbpasswd)) {
					shaPass = sha.getSha256(newpasswd.getBytes());
					String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
					sql = "update member2 set passwd=?,name=?,addr=?,tel=? where id=?";
					ps = conn.prepareStatement(sql);
					System.out.println("UPDATE DB연결 성공");
					ps.setString(1, bcPass); ps.setString(2, mbd.getName());
					ps.setString(3, mbd.getAddr()); ps.setString(4, mbd.getTel());
					ps.setString(5, mbd.getId());
					rtIv = ps.executeUpdate();
				}else rtIv = NOPASS; //비밀번호 틀림
			}else {
				rtIv = NOID;//아이디 틀림
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
            if (ps != null) try { ps.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return rtIv;
	}
	/*END 회원정보 업데이트하기 --------------------------*/
	
}
