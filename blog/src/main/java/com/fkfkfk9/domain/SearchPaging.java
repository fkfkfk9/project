package com.fkfkfk9.domain;

public class SearchPaging extends Paging{
	private String searchType;
	private String keyword;
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "SearchPaging [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
	
}
