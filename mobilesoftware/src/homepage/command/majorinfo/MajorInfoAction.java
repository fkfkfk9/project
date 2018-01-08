package homepage.command.majorinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homepage.command.CommandAction;

public class MajorInfoAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/majorInfo/majorinfo.jsp";//학과소개 페이지를 불러온다.
	}

}
