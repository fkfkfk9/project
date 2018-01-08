package bean.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import enumfile.ReturnInt;
import work.crypt.BCrypt;
import work.crypt.SHA256;

public class MemberDAOBean {
	/*
		목차
		작업1 Singleton Start
		작업2 DB연결작업 	
		작업3 로그인 정보 확인
		작업4 id중복체크
		작업5 member테이블에 회원가입폼 정보 insert
		작업6 update에서 회원정보를 보여주기 위한 select
		작업7 회원정보수정폼에서 받아온 데이터로 update문 실행
	*/
	//작업1 Singleton Start-------------------->
	private static MemberDAOBean instance = new MemberDAOBean();
	//Singleton패턴 구성 하나의 클래스로 하나의 객체만 생성하여 작업
	public static MemberDAOBean getInstance() {
    	return instance;
    }
	//END Singleton ----------------------------->
	
	/*작업2 DB연결작업 ---------------------------------*/
    private MemberDAOBean() {}
   
    private Connection getConnection() throws Exception {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource)envCtx.lookup("jdbc/homepage");
        return ds.getConnection();
    }
    /*END DB Connection------------------------------*/
    
    /*작업3 로그인 정보 확인------------------------------*/
    //서블릿으로 부터 id와 pass를 받아 DB와 일치하는지 확인 후 결과값과 m_level을 int형 배열로 반환
    public int[] getLoginCheck(String id, String pass) {
    	System.out.println("로그인 DAO진입" + id + pass);
    	int iArr[] = new int[2];//리턴할 int배열
    	int rtIv = ReturnInt.FAIL.getValue();//리턴을 위한 int값 상태를 리턴해준다.
    	//ReturnInt는 리턴값 상수를 열거형으로 미리 정의해놓았다.
    	int m_level=0;
    	String sql = "";
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	SHA256 sha = SHA256.getInsatnce();
    	
    	try {
    		conn = getConnection();//DB와 웹페이지를 연결
    		
    		String orgPass = pass;
            String shaPass = sha.getSha256(orgPass.getBytes());
    		
    		sql = "select pass, m_level from member where id=?";//아이디를 통해 패스워드를 가져오는 sql문
    		ps = conn.prepareStatement(sql);//sql문을 넣어준다.
    		ps.setString(1, id);//sql문에 id를 설정
    		rs = ps.executeQuery();//DB에서 쿼리를 실행시켜 결과값을 rs로 받아온다.
    		System.out.println("로그인 sql쿼리 실행");
    		if(rs.next()) {//DB상에 id에 해당하는 값이 있을 경우
    			String dbpass= rs.getString("pass");
    			m_level = rs.getInt("m_level");
				if(BCrypt.checkpw(shaPass,dbpass)) rtIv = ReturnInt.SUCCESS.getValue();
				//암호가 같으면 1을 리턴
				else rtIv = ReturnInt.FAIL.getValue();
				//암호가 틀리면 -1 리턴
    		}else rtIv = ReturnInt.ERROR.getValue();
    		//0을 리턴함으로서 없는 아이디라는것을 알린다. 
    	}catch(Exception ex) {ex.printStackTrace();} 
    	finally {
            if (rs != null)	try { rs.close(); } catch(SQLException ex) {}
            if (ps != null)	try { ps.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }    	
    	iArr[0] = rtIv; iArr[1] = m_level;//0에는 성공여부 1에는 사용자 레벨을 저장하여 리턴
    	return iArr;//결과 값을 리턴
    }
    /*END  로그인 정보 확인------------------------------*/
    
    /*작업4 id중복체크----------------------------------*/
    public int idCheck(String id) {//리턴값은 int에 매개변수로 id를 받는다. 회원가입폼에서 id중복체크용으로 사용된다.
    	int rtIv = ReturnInt.FAIL.getValue();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String sql = "";
    	
    	try {
    		conn = getConnection();//DB와 웹페이지를 연결
    		sql = "select count(*) from member where id=?";
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, id);
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {//리턴받는값이 있는경우 count를 사용했기 때문에 중복이 없더라도 0이 리턴된다.
    			if(rs.getInt(1)==1) rtIv = ReturnInt.FAIL.getValue();//같은id가 1개 있으므로 실패
    			else if(rs.getInt(1)==0) rtIv = ReturnInt.SUCCESS.getValue(); //같은id가 없으므로 실패
    			else rtIv = ReturnInt.ERROR.getValue();//둘모두 없을경우는 에러 id는 pk이기 때문에 1개이상이 불가능
    		}else rtIv = ReturnInt.ERROR.getValue();//리턴값이 없는경우는 에러가 발생했을때 뿐이다. 				
    	}catch(Exception ex) {ex.printStackTrace();} 
    	finally {
            if (rs != null)	try { rs.close(); } catch(SQLException ex) {}
            if (ps != null)	try { ps.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    	return rtIv;
    }
    /*END id중복체크-----------------------------------*/
    
    /*작업5 member테이블에 회원가입폼 정보 insert------------*/
    public int memberinsert(MemberDTOBean mdtb) {
    	int rtIv = ReturnInt.FAIL.getValue();//리턴할 int값 초기화
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = "";
    	SHA256 sha = SHA256.getInsatnce();//암호를 암호화하기 위한 객체생성
    	
    	try {
    		String orgPass = mdtb.getPass();//매개변수로 받아온 객체에서 암호를 받아온다.
            String shaPass = sha.getSha256(orgPass.getBytes());
        	String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
        	//암호화 끝
        	conn = getConnection();
        	sql = "insert into MEMBER values(?, ?, ?, ?, ?, ?, ?)";
        	ps = conn.prepareStatement(sql);
        	//매개변수로 받아온 객체의 데이터를 넣어준다.
        	ps.setString(1, mdtb.getId());ps.setString(2, bcPass);
        	ps.setString(3, mdtb.getName());ps.setString(4, mdtb.getHp());
        	ps.setString(5, mdtb.getEmail());ps.setTimestamp(6, mdtb.getRegist_day());
        	ps.setInt(7, mdtb.getM_level());
        	rtIv = ps.executeUpdate();//성공하면 1이 리턴된다.
    	}catch(Exception ex) {ex.printStackTrace();} 
    	finally {            
            if (ps != null)	try { ps.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    	
    	return rtIv;
    }
    /*END  member테이블에 회원가입폼 정보 insert------------*/
    
    /*작업6 update에서 회원정보를 보여주기 위한 select--------*/
    public MemberDTOBean updateinfo(String id) {
    	System.out.println("회원정보 얻어오는작업중 진입시 id : " + id);
    	MemberDTOBean mdtb = new MemberDTOBean();//리턴할 객체 생성
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String sql = "";
    	
    	try {
    		conn = getConnection();//DB와 웹페이지를 연결
    		sql = "select * from member where id=?";
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, id);
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {//리턴받는값이 있는경우 count를 사용했기 때문에 중복이 없더라도 0이 리턴된다.
    			mdtb.setName(rs.getString("name"));
    			mdtb.setHp(rs.getString("hp"));
    			mdtb.setEmail(rs.getString("email"));
    			mdtb.setM_level(rs.getInt("m_level"));
    		} 				
    	}catch(Exception ex) {ex.printStackTrace();} 
    	finally {
            if (rs != null)	try { rs.close(); } catch(SQLException ex) {}
            if (ps != null)	try { ps.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    	return mdtb;
    }
    /*END  update에서 회원정보를 보여주기 위한 select--------*/
    
    /*작업7 회원정보수정폼에서 받아온 데이터로 update문 실행-----*/
    public int memberupdate(MemberDTOBean mdtb) {
    	int rtIv = ReturnInt.FAIL.getValue();//리턴할 int값 초기화
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = "";
    	SHA256 sha = SHA256.getInsatnce();//암호를 암호화하기 위한 객체생성
    	
    	try {
    		String orgPass = mdtb.getPass();//매개변수로 받아온 객체에서 암호를 받아온다.
            String shaPass = sha.getSha256(orgPass.getBytes());
        	String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
        	//암호화 끝
        	conn = getConnection();
        	sql = "update member set pass=?,name=?,hp=?,email=? where id=?";
        	ps = conn.prepareStatement(sql);
        	//매개변수로 받아온 객체의 데이터를 넣어준다.
        	ps.setString(1, bcPass);ps.setString(2, mdtb.getName());
        	ps.setString(3, mdtb.getHp());ps.setString(4, mdtb.getEmail());
        	ps.setString(5, mdtb.getId());
        	rtIv = ps.executeUpdate();//성공하면 1이 리턴된다.
    	}catch(Exception ex) {ex.printStackTrace();} 
    	finally {            
            if (ps != null)	try { ps.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    	
    	return rtIv;
    }
    /*END  회원정보수정폼에서 받아온 데이터로 update문 실행-----*/
   
}
