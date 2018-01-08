package homepage.command.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.member.MemberDAOBean;
import homepage.command.CommandAction;

public class IdCheckAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//request파일중 한글이 있을경우 깨짐을 방지하귀해 문자셋을 바꾼다.
		request.setCharacterEncoding("utf-8");
				
		//request로 post로 전송된 id, pass를 String변수에 넣어준다.
		String id = request.getParameter("id");
		
		//member테이블에 DB 작업을 하기위한 MemberDAOBean변수를 만들어준다.
		MemberDAOBean dmb = MemberDAOBean.getInstance();//싱글톤이기 때문에 생성자가 아닌 메서드로 생성
		int check = dmb.idCheck(id);//리턴받은 int형 데이터를 입력한다.
		System.out.println("id체크로 리턴받은 값 : " + check);//리턴받은값을 콘솔에서 확인하기 위한 작업
		request.setAttribute("check", new Integer(check));
		return "/lib/returnIntCheck.jsp";
	}

}
