package homepage.command.toplogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homepage.command.CommandAction;

public class LoginFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/login/login_form.jsp";//�α��� ���� �ҷ��´�.
	}
}
