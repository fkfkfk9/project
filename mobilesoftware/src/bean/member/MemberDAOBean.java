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
		����
		�۾�1 Singleton Start
		�۾�2 DB�����۾� 	
		�۾�3 �α��� ���� Ȯ��
		�۾�4 id�ߺ�üũ
		�۾�5 member���̺� ȸ�������� ���� insert
		�۾�6 update���� ȸ�������� �����ֱ� ���� select
		�۾�7 ȸ���������������� �޾ƿ� �����ͷ� update�� ����
	*/
	//�۾�1 Singleton Start-------------------->
	private static MemberDAOBean instance = new MemberDAOBean();
	//Singleton���� ���� �ϳ��� Ŭ������ �ϳ��� ��ü�� �����Ͽ� �۾�
	public static MemberDAOBean getInstance() {
    	return instance;
    }
	//END Singleton ----------------------------->
	
	/*�۾�2 DB�����۾� ---------------------------------*/
    private MemberDAOBean() {}
   
    private Connection getConnection() throws Exception {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource)envCtx.lookup("jdbc/homepage");
        return ds.getConnection();
    }
    /*END DB Connection------------------------------*/
    
    /*�۾�3 �α��� ���� Ȯ��------------------------------*/
    //�������� ���� id�� pass�� �޾� DB�� ��ġ�ϴ��� Ȯ�� �� ������� m_level�� int�� �迭�� ��ȯ
    public int[] getLoginCheck(String id, String pass) {
    	System.out.println("�α��� DAO����" + id + pass);
    	int iArr[] = new int[2];//������ int�迭
    	int rtIv = ReturnInt.FAIL.getValue();//������ ���� int�� ���¸� �������ش�.
    	//ReturnInt�� ���ϰ� ����� ���������� �̸� �����س��Ҵ�.
    	int m_level=0;
    	String sql = "";
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	SHA256 sha = SHA256.getInsatnce();
    	
    	try {
    		conn = getConnection();//DB�� ���������� ����
    		
    		String orgPass = pass;
            String shaPass = sha.getSha256(orgPass.getBytes());
    		
    		sql = "select pass, m_level from member where id=?";//���̵� ���� �н����带 �������� sql��
    		ps = conn.prepareStatement(sql);//sql���� �־��ش�.
    		ps.setString(1, id);//sql���� id�� ����
    		rs = ps.executeQuery();//DB���� ������ ������� ������� rs�� �޾ƿ´�.
    		System.out.println("�α��� sql���� ����");
    		if(rs.next()) {//DB�� id�� �ش��ϴ� ���� ���� ���
    			String dbpass= rs.getString("pass");
    			m_level = rs.getInt("m_level");
				if(BCrypt.checkpw(shaPass,dbpass)) rtIv = ReturnInt.SUCCESS.getValue();
				//��ȣ�� ������ 1�� ����
				else rtIv = ReturnInt.FAIL.getValue();
				//��ȣ�� Ʋ���� -1 ����
    		}else rtIv = ReturnInt.ERROR.getValue();
    		//0�� ���������μ� ���� ���̵��°��� �˸���. 
    	}catch(Exception ex) {ex.printStackTrace();} 
    	finally {
            if (rs != null)	try { rs.close(); } catch(SQLException ex) {}
            if (ps != null)	try { ps.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }    	
    	iArr[0] = rtIv; iArr[1] = m_level;//0���� �������� 1���� ����� ������ �����Ͽ� ����
    	return iArr;//��� ���� ����
    }
    /*END  �α��� ���� Ȯ��------------------------------*/
    
    /*�۾�4 id�ߺ�üũ----------------------------------*/
    public int idCheck(String id) {//���ϰ��� int�� �Ű������� id�� �޴´�. ȸ������������ id�ߺ�üũ������ ���ȴ�.
    	int rtIv = ReturnInt.FAIL.getValue();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String sql = "";
    	
    	try {
    		conn = getConnection();//DB�� ���������� ����
    		sql = "select count(*) from member where id=?";
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, id);
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {//���Ϲ޴°��� �ִ°�� count�� ����߱� ������ �ߺ��� ������ 0�� ���ϵȴ�.
    			if(rs.getInt(1)==1) rtIv = ReturnInt.FAIL.getValue();//����id�� 1�� �����Ƿ� ����
    			else if(rs.getInt(1)==0) rtIv = ReturnInt.SUCCESS.getValue(); //����id�� �����Ƿ� ����
    			else rtIv = ReturnInt.ERROR.getValue();//�Ѹ�� �������� ���� id�� pk�̱� ������ 1���̻��� �Ұ���
    		}else rtIv = ReturnInt.ERROR.getValue();//���ϰ��� ���°��� ������ �߻������� ���̴�. 				
    	}catch(Exception ex) {ex.printStackTrace();} 
    	finally {
            if (rs != null)	try { rs.close(); } catch(SQLException ex) {}
            if (ps != null)	try { ps.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    	return rtIv;
    }
    /*END id�ߺ�üũ-----------------------------------*/
    
    /*�۾�5 member���̺� ȸ�������� ���� insert------------*/
    public int memberinsert(MemberDTOBean mdtb) {
    	int rtIv = ReturnInt.FAIL.getValue();//������ int�� �ʱ�ȭ
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = "";
    	SHA256 sha = SHA256.getInsatnce();//��ȣ�� ��ȣȭ�ϱ� ���� ��ü����
    	
    	try {
    		String orgPass = mdtb.getPass();//�Ű������� �޾ƿ� ��ü���� ��ȣ�� �޾ƿ´�.
            String shaPass = sha.getSha256(orgPass.getBytes());
        	String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
        	//��ȣȭ ��
        	conn = getConnection();
        	sql = "insert into MEMBER values(?, ?, ?, ?, ?, ?, ?)";
        	ps = conn.prepareStatement(sql);
        	//�Ű������� �޾ƿ� ��ü�� �����͸� �־��ش�.
        	ps.setString(1, mdtb.getId());ps.setString(2, bcPass);
        	ps.setString(3, mdtb.getName());ps.setString(4, mdtb.getHp());
        	ps.setString(5, mdtb.getEmail());ps.setTimestamp(6, mdtb.getRegist_day());
        	ps.setInt(7, mdtb.getM_level());
        	rtIv = ps.executeUpdate();//�����ϸ� 1�� ���ϵȴ�.
    	}catch(Exception ex) {ex.printStackTrace();} 
    	finally {            
            if (ps != null)	try { ps.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    	
    	return rtIv;
    }
    /*END  member���̺� ȸ�������� ���� insert------------*/
    
    /*�۾�6 update���� ȸ�������� �����ֱ� ���� select--------*/
    public MemberDTOBean updateinfo(String id) {
    	System.out.println("ȸ������ �������۾��� ���Խ� id : " + id);
    	MemberDTOBean mdtb = new MemberDTOBean();//������ ��ü ����
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String sql = "";
    	
    	try {
    		conn = getConnection();//DB�� ���������� ����
    		sql = "select * from member where id=?";
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, id);
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {//���Ϲ޴°��� �ִ°�� count�� ����߱� ������ �ߺ��� ������ 0�� ���ϵȴ�.
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
    /*END  update���� ȸ�������� �����ֱ� ���� select--------*/
    
    /*�۾�7 ȸ���������������� �޾ƿ� �����ͷ� update�� ����-----*/
    public int memberupdate(MemberDTOBean mdtb) {
    	int rtIv = ReturnInt.FAIL.getValue();//������ int�� �ʱ�ȭ
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = "";
    	SHA256 sha = SHA256.getInsatnce();//��ȣ�� ��ȣȭ�ϱ� ���� ��ü����
    	
    	try {
    		String orgPass = mdtb.getPass();//�Ű������� �޾ƿ� ��ü���� ��ȣ�� �޾ƿ´�.
            String shaPass = sha.getSha256(orgPass.getBytes());
        	String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
        	//��ȣȭ ��
        	conn = getConnection();
        	sql = "update member set pass=?,name=?,hp=?,email=? where id=?";
        	ps = conn.prepareStatement(sql);
        	//�Ű������� �޾ƿ� ��ü�� �����͸� �־��ش�.
        	ps.setString(1, bcPass);ps.setString(2, mdtb.getName());
        	ps.setString(3, mdtb.getHp());ps.setString(4, mdtb.getEmail());
        	ps.setString(5, mdtb.getId());
        	rtIv = ps.executeUpdate();//�����ϸ� 1�� ���ϵȴ�.
    	}catch(Exception ex) {ex.printStackTrace();} 
    	finally {            
            if (ps != null)	try { ps.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    	
    	return rtIv;
    }
    /*END  ȸ���������������� �޾ƿ� �����ͷ� update�� ����-----*/
   
}
