package com.fkfkfk9.controller;

import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fkfkfk9.domain.BoardVO;
import com.fkfkfk9.domain.PageBtn;
import com.fkfkfk9.domain.Paging;
import com.fkfkfk9.domain.SearchPaging;
import com.fkfkfk9.dao.BoardDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardDAO dao;
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	  public void listAll(@ModelAttribute("paging") SearchPaging paging, Model model) throws Exception {
	    logger.info("show all list......................");
	    
	    //현재 페이지에 출력해줄 데이터를 불러온다.
	    model.addAttribute("list", dao.searchListPage(paging));
	    //모든 컬럼의 숫자를 가져온다.
	    int aticleCnt = dao.searchCount(paging);
	    //페이지 버튼 클래스 생성
	    PageBtn pb = new PageBtn();
	    //현재 페이지 정보를 페이지버튼에 넣어준다.
	    pb.setPaging(paging);
	    //총 컬럼의 숫자를 페이지 버튼에 넣어준다.
	    pb.setTotalcol(aticleCnt);
	    
	    //현재 페이지에 만들 페이지버튼에 대한 정보를 보낸다.
	    model.addAttribute("pageBtn", pb);
	    //총 숫자도 페이지에 보낸다.
	    model.addAttribute("aticleCnt", aticleCnt);
	  }
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public void createGet(@ModelAttribute("paging") SearchPaging paging) throws Exception{
		logger.info("create Get......................");
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String createPost(BoardVO vo,RedirectAttributes rttr) throws Exception{
		logger.info("create POST......................");
	    logger.info(vo.toString());
	    vo.setRe_level(0);
	    vo.setRe_step(0);
	    vo.setIp("127.0.0.1");
	    dao.create(vo);
	    rttr.addFlashAttribute("msg", "SUCCESS");
	    return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public void readGet(@ModelAttribute("paging") SearchPaging paging,int num, Model model) throws Exception{
		logger.info("read Get..........."+paging.toString());
		//조회수를 1 올려준다.
		dao.readcount(num);
		BoardVO vo = dao.read(num);
		model.addAttribute("vo", vo);
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String deleteGet(@RequestParam("num") int num, @ModelAttribute("paging") SearchPaging paging, RedirectAttributes rttr)throws Exception{
		logger.info("deleteGet ......................");
		dao.delete(num);
		rttr.addAttribute("pageNum", paging.getPageNum());
	    rttr.addAttribute("pageSize", paging.getPageSize());
	    rttr.addAttribute("searchType", paging.getSearchType());
	    rttr.addAttribute("keyword", paging.getKeyword());
	    rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public void updateGet(@ModelAttribute("paging") SearchPaging paging,int num, Model model)throws Exception{
		logger.info("updateGet ......................" + num);
		
		BoardVO vo = dao.read(num);
		System.out.println(vo.toString());
		model.addAttribute("vo", vo);
	}
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String updatePost(BoardVO vo, @ModelAttribute("paging") SearchPaging paging, RedirectAttributes rttr)throws Exception{
		logger.info("updatePost ......................" + vo.toString());
		dao.update(vo.getNum(), vo.getSubject(), vo.getContent());
		rttr.addAttribute("pageNum", paging.getPageNum());
	    rttr.addAttribute("pageSize", paging.getPageSize());
	    rttr.addAttribute("searchType", paging.getSearchType());
	    rttr.addAttribute("keyword", paging.getKeyword());
	    logger.info(paging.toString());
	    rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/listAll";
	}
}
