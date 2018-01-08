package homepage.command.majorboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homepage.command.CommandAction;

public class MajorBoardAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/majorboard/majorboardmain.jsp";
	}

}
