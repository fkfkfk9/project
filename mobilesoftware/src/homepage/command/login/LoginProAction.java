package homepage.command.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.member.MemberDAOBean;
import enumfile.ReturnInt;
import homepage.command.CommandAction;

public class LoginProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//request파일중 한글이 있을경우 깨짐을 방지하귀해 문자셋을 바꾼다.
		request.setCharacterEncoding("utf-8");
		
		//request로 post로 전송된 id, pass를 String변수에 넣어준다.
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		//member테이블에 DB 작업을 하기위한 MemberDAOBean변수를 만들어준다.
		MemberDAOBean dmb = MemberDAOBean.getInstance();//싱글톤이기 때문에 생성자가 아닌 메서드로 생성
		int[] iArr = dmb.getLoginCheck(id, pass);//id, pass를 보내 DB의 정보와 맞는지 확인 후 int로 리턴
		int check = iArr[0];//성공여부 확인을 위한 리턴값
		int m_level = iArr[1];//사용자 레벨 확인을 위한 리턴값
		//리턴값을 저장하여 콜백함수에서 확인할 수 있게한다. new Integer를 사용하는 이유는 setAttribute가 object를 매개변수로 사용하기 때문
		request.setAttribute("check", new Integer(check));
		System.out.println("로그인 리턴값 체크 : " + check);
		//리턴값이 성공일 경우  세션에 id, 사용자 레벨값을 넣어줌으로써 로그인 상태를 만든다
		if(check == ReturnInt.SUCCESS.getValue()) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("m_level", m_level);
		}
		return "/lib/returnIntCheck.jsp";//리턴값이 int인 모든 서블릿에서 표시해주는 페이지
	}

}
