package homepage.command.toplogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homepage.command.CommandAction;

public class JoinFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/login/join_form.jsp";//회원가입 폼만 불러온다.
	}

}
