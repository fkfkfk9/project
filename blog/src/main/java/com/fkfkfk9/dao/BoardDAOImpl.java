package com.fkfkfk9.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.fkfkfk9.domain.BoardVO;
import com.fkfkfk9.domain.Paging;
import com.fkfkfk9.domain.SearchPaging;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.fkfkfk9.mapper.BoardMapper";

	@Override
	public int count() throws Exception {
		return session.selectOne(namespace+".count");
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return session.selectList(namespace+".listAll");
	}

	@Override
	public void create(BoardVO vo) throws Exception {
		session.insert(namespace+".create", vo);
	}

	@Override
	public void reCreate(BoardVO vo) throws Exception {
		session.insert(namespace+".reCreate", vo);
	}

	@Override
	public void reUpdate(int ref, int re_step) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ref", new Integer(ref));
		paramMap.put("re_step", new Integer(re_step));
		session.update(namespace+".reUpdate", paramMap);	
	}

	@Override
	public void delete(int num) throws Exception {
		session.delete(namespace+".delete", num);
	}

	@Override
	public String check(int num) throws Exception {
		return session.selectOne(namespace+".check", num);
	}

	@Override
	public BoardVO read(int num) throws Exception {
		return session.selectOne(namespace+".read", num);
	}

	@Override
	public void update(int num, String subject, String content) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("num", new Integer(num));
		paramMap.put("subject", subject);
		paramMap.put("content", content);
		session.update(namespace+".update", paramMap);	
	}

	@Override
	public void readcount(int num) throws Exception {
		session.update(namespace+".readcount", num);
	}

	@Override
	public List<BoardVO> listPage(Paging paging) throws Exception {
		System.out.println(paging.toString());
		return session.selectList(namespace+".listPage", paging);
	}
	
	@Override
	public List<BoardVO> searchListPage(SearchPaging paging) throws Exception {
		System.out.println(paging.toString());
		return session.selectList(namespace+".searchListPage", paging);
	}

	@Override
	public int searchCount(SearchPaging paging) throws Exception {
		return session.selectOne(namespace+".searchCount", paging);
	}

}
