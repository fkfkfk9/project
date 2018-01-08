package homepage.command.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.member.MemberDAOBean;
import homepage.command.CommandAction;

public class PassCheckAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//request파일중 한글이 있을경우 깨짐을 방지하귀해 문자셋을 바꾼다.
		request.setCharacterEncoding("utf-8");
				
		//request로 post로 전송된 id, pass를 String변수에 넣어준다.
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		//member테이블에 DB 작업을 하기위한 MemberDAOBean변수를 만들어준다.
		MemberDAOBean dmb = MemberDAOBean.getInstance();//싱글톤이기 때문에 생성자가 아닌 메서드로 생성
		int[] result = dmb.getLoginCheck(id, pass);//아이디로 암호를 검증하므로 로그인과 다를게 없어 그대로 사용한다.
		request.setAttribute("check", new Integer(result[0]));
		return "/lib/returnIntCheck.jsp";
	}

}
