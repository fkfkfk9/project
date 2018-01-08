package homepage.command.majorboard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.menu02.MajorBoardDAOBean;
import bean.menu02.MajorBoardDTOBean;
import bean.page.PageBtn;
import bean.page.Paging;
import homepage.command.CommandAction;

public class MajorBoardListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//request파일중 한글이 있을경우 깨짐을 방지하귀해 문자셋을 바꾼다.
		request.setCharacterEncoding("utf-8");
		
		int grade = Integer.parseInt(request.getParameter("grade"));//학년을 변수로 받는다.
		int sub = Integer.parseInt(request.getParameter("sub"));//학기를 변수로 받는다.
		int articleCnt = 0;//게시판의 총 글 수를 받아올 변수를 초기화
		String classes = "";//과목명을 전달하기 위한 변수
		List<MajorBoardDTOBean> articleList = null;//게시판에 출력할 내용을 받아올 리스트를 초기화		
		
		//페이징을 위한 선언작업
		PageBtn pb = new PageBtn();
		Paging paging = new Paging();//페이지 저장을 위한 객체 생성
		//기본값은 pageNum = 1 page Size = 10
		String pageNum = request.getParameter("pageNum");//화면에서 받아온 사용자가 선택한 페이지 번호
		//넘어온 값이 있으면 그 값을 넣어준다.
		if(pageNum == null || pageNum.equals("")) paging.setPageNum(1);
		else paging.setPageNum(Integer.parseInt(pageNum));
		pb.setPaging(paging);//페이지의 넘버를 숫자로 파싱 리퀘스트로 받은 값은 String기 때문에 변경
		//END 페이징 선언
		
		//디비와 연동하기 위한 DAO클래스를 생성
		MajorBoardDAOBean mbdao = MajorBoardDAOBean.getInstance();		
		
		//입력받은 학년과 학기에 다라 과목과 학년값을 입력해 불러오는 페이지로 전송해준다.
		if(grade == 1) {			
			request.setAttribute("grade", "1학년");
			if(sub == 1) classes="C언어";
			else if(sub == 2) classes="컴퓨터 구조";
		}else if(grade == 2) {			
			request.setAttribute("grade", "2학년");
			if(sub == 1) classes="JAVA";
			else if(sub == 2) classes="알고리즘";
		}else if(grade == 3) {			
			request.setAttribute("grade", "3학년");
			if(sub == 1) classes="운영체제";
			else if(sub == 2) classes="네트워크";
		}if(grade == 4) {			
			request.setAttribute("grade", "4학년");
			classes="졸업 프로잭트";	
		}
		request.setAttribute("sub", classes);
		/*END OF 타이틀 과목명 선택을 위한 데이터 전송*/
		
		//게시판 글의 총갯수를 구한다. 과목별로 갯수가 다르게 계산해야 하기 때문에 매개변수는 과목명
		articleCnt = mbdao.getarticleCnt(classes);
		
		//페이징 데이터 작업
		//가져올 데이터 부분의 전체 갯수를 셋팅해준다.
		pb.setTotalcol(articleCnt);
		articleList = mbdao.getArticle(classes, pb.getPaging());
		
		//setAttribute는 객체 데이터를 요구하기 때문에 인티저로 생성해서 보낸다.
		request.setAttribute("articleCnt", new Integer(articleCnt));//총글 갯수
		request.setAttribute("articleList", articleList);//출력할 게시글 내용
		request.setAttribute("pageBtn", pb);
		
		return "/majorboard/majorboardlist.jsp";
	}

}
