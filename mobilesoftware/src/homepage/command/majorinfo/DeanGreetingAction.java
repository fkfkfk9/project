package homepage.command.majorinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homepage.command.CommandAction;

public class DeanGreetingAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/majorInfo/deanGreeting.jsp";//학과장 인사말 페이지를 불러온다.
	}

}
