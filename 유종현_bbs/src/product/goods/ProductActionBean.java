package product.goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductActionBean {
	
	//작업1 Singleton Start-------------------->
	private static ProductActionBean instance = new ProductActionBean();
	
	public static ProductActionBean getInstance() {return instance;}
	
	private ProductActionBean() {}
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
	
	/*작업3 DB Insert ---------------------------------*/
	public String insertGoodsData(ProductInfoBean pib) {
		String rtSv = "";
		Connection conn = null;
        PreparedStatement ps = null;
        String sql="";
        
        try {
        	conn = getConnection();
        	sql = "insert into product(p_id, p_name, p_price, p_img1)";
        	sql += "values(?,?,?,?)";
        	ps = conn.prepareStatement(sql);
        	ps.setInt(1, pib.getP_id());
        	ps.setString(2, pib.getP_name());
        	ps.setInt(3, pib.getP_price());
        	ps.setString(4, pib.getP_img1());
        	int result = ps.executeUpdate();
        	if(result == 1) rtSv = pib.getP_img1();
        	else rtSv="데이터 입력 실패";
        }catch(Exception ex) { ex.printStackTrace();
		}finally{
            if (ps != null) try{ ps.close(); }catch(SQLException ex) {}
            if (conn != null) try{ conn.close(); }catch(SQLException ex) {}
        }		
		return rtSv;
	}
	/*END DB Insert ---------------------------------*/	
}
