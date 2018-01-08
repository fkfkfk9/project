package product.goods;

public class ProductInfoBean {
	/*
	create table product(
    	p_id    NUMBER          primary key,
    	p_name  VARCHAR2(20)    not null,
    	p_price NUMBER          not null,
    	p_img1  VARCHAR2(20)    not null,
    	p_img2  VARCHAR2(20)    not null
	);
	*/
	private int p_id;
	private String p_name;
	private int p_price;
	private String p_img1;
	private String p_img2;
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public String getP_img1() {
		return p_img1;
	}
	public void setP_img1(String p_img1) {
		this.p_img1 = p_img1;
	}
	public String getP_img2() {
		return p_img2;
	}
	public void setP_img2(String p_img2) {
		this.p_img2 = p_img2;
	}	
}
