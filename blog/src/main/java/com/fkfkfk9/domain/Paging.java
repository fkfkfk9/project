package com.fkfkfk9.domain;

public class Paging {//페이징 작업을 하는데 필요한 데이터 모음
	//선택한 페이지, 이동할 페이지
	private int pageNum;
	//한페이지에서 출력될 데이터의 숫자
	private int pageSize;
	
	public Paging() {//아무 설정이 없다면 첫페이지 이므로 기본값을 넣어준다.
		super();
		this.pageNum = 1;
		this.pageSize = 10;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		if(pageNum<=0) {//페이지는 0보다 작을수가 없으므로 제한을 건다.
			this.pageNum = 1;
			return;
		}
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize <=0 || pageSize >= 50) {//한 페이지의 출력될 데이터를 50까지만 가능하게 했다.
			pageSize = 10;
			return;
		}
		this.pageSize = pageSize;
	}
	public int getStartQuery() {//DB에서 데이터를 뽑아올때 시작되는 위치
		return (this.pageNum-1)*this.pageSize+1;
	}
	public int getEndQuery() {//DB에서 데이터를 뽑아올때 끝나는 위치 오라클은 limit가 없기 때문에 작업
		return getStartQuery()+this.pageSize-1;
	}
	@Override
	public String toString() {
		return "Paging [pageNum=" + pageNum + ", pageSize=" + pageSize + ", StartQuery=" + getStartQuery()
				+ ", EndQuery=" + getEndQuery() + "]";
	}	
	
}
