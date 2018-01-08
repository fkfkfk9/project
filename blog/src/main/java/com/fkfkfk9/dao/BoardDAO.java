package com.fkfkfk9.dao;

import java.util.List;

import com.fkfkfk9.domain.BoardVO;
import com.fkfkfk9.domain.Paging;
import com.fkfkfk9.domain.SearchPaging;

public interface BoardDAO {
	//게시판의 모든 글의 수를 구해온다.
	public int count() throws Exception;
	//게시판의 모든 내용을 가져온다.
	public List<BoardVO> listAll()throws Exception;
	//게시판의 글을 만든다.
	public void create(BoardVO vo)throws Exception;
	//게시판의 댓글을 만든다.
	public void reCreate(BoardVO vo)throws Exception;
	//댓글작성시 업데이트 되는 부분
	public void reUpdate(int ref, int re_step)throws Exception;
	//게시판의 글을 지운다.
	public void delete(int num)throws Exception;
	//게시판의 암호를 체크한다.
	public String check(int num)throws Exception;
	//게시판을 수정하기위한 정보를 불러온다.
	public BoardVO read(int num)throws Exception;
	//게시판을 수정한다.
	public void update(int num, String subject, String content)throws Exception;
	//게시판의 조회수를 증가시킨다.
	public void readcount(int num)throws Exception;
	//게시판의 원하는곳의 정보만 가져온다.
	public List<BoardVO> listPage(Paging paging)throws Exception;
	//게시판에 서치기능까지 추가
	public List<BoardVO> searchListPage(SearchPaging paging) throws Exception;
	//게시판의 검색된 글의 수를 구해온다.
	public int searchCount(SearchPaging paging) throws Exception;
}
