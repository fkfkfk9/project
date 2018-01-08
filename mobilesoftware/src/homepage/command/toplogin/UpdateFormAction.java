package homepage.command.toplogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.member.MemberDAOBean;
import bean.member.MemberDTOBean;
import homepage.command.CommandAction;

public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//request파일중 한글이 있을경우 깨짐을 방지하귀해 문자셋을 바꾼다.
		request.setCharacterEncoding("utf-8");
		
		//회원정보를 찾아올 id를 얻어오기 위해 세션을 받아온다.
		HttpSession session = request.getSession();
		
		//member테이블에 DB 작업을 하기위한 MemberDAOBean변수를 만들어준다.
		MemberDAOBean dmb = MemberDAOBean.getInstance();//싱글톤이기 때문에 생성자가 아닌 메서드로 생성
		MemberDTOBean mdtb = dmb.updateinfo((String)session.getAttribute("id"));//회원객체에 정보를 받는다.
		String[] hp = mdtb.getHp().split("-");
		String[] email = mdtb.getEmail().split("@");
		if(mdtb.getM_level() == 1) request.setAttribute("m_level_val", "학생");
		else if(mdtb.getM_level() == 2) request.setAttribute("m_level_val", "교수");
		request.setAttribute("name", mdtb.getName());
		request.setAttribute("hp1", hp[0]);
		request.setAttribute("hp2", hp[1]);
		request.setAttribute("hp3", hp[2]);
		request.setAttribute("email1", email[0]);
		request.setAttribute("email2", email[1]);		
		
		return "/login/update_form.jsp";//회원정보수정폼을 불러온다.
	}

}
