package com.fkfkfk9.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fkfkfk9.dao.BoardDAO;
import com.fkfkfk9.domain.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDAOTest {

	@Inject
	private BoardDAO dao;
	
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Test
	public void testCreate() throws Exception{
		BoardVO vo = new BoardVO();
		vo.setWriter("fkfkfk9");
		vo.setSubject("테스트용 제목1");
		vo.setPasswd("1111");
		vo.setRe_step(0);
		vo.setRe_level(0);
		vo.setContent("테스트용 본문1");
		vo.setIp("127.0.0.1");
		dao.create(vo);
	}
	
	@Test
	public void testCount() throws Exception{
		System.out.println("게시물의 숫자는"+dao.count()+"개 입니다.");
	}
	@Test
	public void testListAll() throws Exception{
		List<BoardVO> list = dao.listAll();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
	@Test
	public void testReCreate() throws Exception{
		BoardVO vo = new BoardVO();
		vo.setWriter("fkfkfk9");
		vo.setSubject("테스트용 제목1");
		vo.setPasswd("1111");
		vo.setRe_step(0);
		vo.setRe_level(0);
		vo.setContent("테스트용 본문1");
		vo.setIp("127.0.0.1");
		vo.setRef(1);
		dao.reCreate(vo);
	}
	@Test
	public void testdelete() throws Exception{
		int num = 1;
		dao.delete(num);
	}
	@Test
	public void testcheck() throws Exception{
		String str = dao.check(2);
		System.out.println(str);
	}
	@Test
	public void testread() throws Exception{
		BoardVO vo = dao.read(2);
		System.out.println(vo.getSubject()+", "+ vo.getContent());
	}
	@Test
	public void testupdate() throws Exception{
		dao.update(2, "테스트용 제목1을 수정", "테스트용 본문1을 수정");
		testListAll();
	}
}
