package com.fkfkfk9.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageBtn {//페이지 버튼을 위한 데이터 관리
	//DB상 데이터의 숫자 즉 select count(*) from tbl_board와 같다.
	private int totalcol;
	//첫번째 버튼에 들어갈 숫자
	private int startPage;
	//마지막 버튼에 들어갈 숫자
	private int endPage;
	//이전 버튼을 생성할것인가 판단
	private boolean prev;
	//다음 버턴을 생성할것인가 판단.
	private boolean next;
	//선택한 페이지 번호와 페이지당 데이터 크기를 넣어준다.
	private Paging paging;
	//버턴의 갯수
	private int btnSize = 10;
	
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	public void setTotalcol(int totalcol) {
		this.totalcol = totalcol;
		//지금까지 들어온 데이터를 통해 페이지관련 계산을 진행한다.
		pageMake();
	}
	
	private void pageMake() {
		//마지막 버튼값을 구한다. 총 페이지의 갯수로 선택한 페이지를 나누고 거기에 다시 페이지의 갯수를 곱하면 마지막 버튼의 값이 나온다.
		endPage = (int)(Math.ceil(paging.getPageNum()/(double)btnSize)*btnSize);
		//마지막 페이지에서 페이지 갯수를 빼고 1을 더하면 시작 페이지
		startPage = endPage-btnSize+1;
		//총 데이터의 갯수를 한 페이지에서 표현하는 데이터 갯수로 나눈걸 올림하면 최종 페이지
		int tempEndPage = (int)(Math.ceil(totalcol/(double)paging.getPageSize()));
		//마지막 버튼이 총 페이지보다 크다면 총페이지 크기를 마지막 버튼에 대입해 없는 번호를 부여하지 않게 한다.
		if(endPage>tempEndPage) endPage = tempEndPage;
		//처음나오는 버튼이 1이라면 이전버튼이 필요없지만 아니라면 있어야 한다.
		prev = startPage == 1 ? false : true;
		//현재 마지막버튼의 숫자와 한페이지당 데이터 갯수를 곱한것이 총 데이터의 갯수보다 크다면 다음버튼은 필요가 없다.
	    next = endPage * paging.getPageSize() >= totalcol ? false : true;
	}
	
	public int getTotalcol() {
		return totalcol;
	}
	
	public Paging getPaging() {
		return paging;
	}	
	
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public boolean isNext() {
		return next;
	}
	
	public String makeQuery(int pageNum) {//주소뒤에 붙을 uri를 만든다.
		//UriComponents는 ip주소뒤에 붙을 uri를 만든다. path는 해당 경로를 넣고 queryParam은 겟방식 데이터를 넣는다. 
	    UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("pageNum", paging.getPageNum())
	        .queryParam("pageSize", paging.getPageSize()).build();

	    return uriComponents.toUriString();
	}
	
	@Override
	public String toString() {
		return "PageBtn [totalcol=" + totalcol + ", startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev
				+ ", next=" + next + ", paging=" + paging + ", btnSize=" + btnSize + "]";
	}	
}
