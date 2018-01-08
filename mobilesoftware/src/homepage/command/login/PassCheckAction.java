package homepage.command.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.member.MemberDAOBean;
import homepage.command.CommandAction;

public class PassCheckAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//request������ �ѱ��� ������� ������ �����ϱ��� ���ڼ��� �ٲ۴�.
		request.setCharacterEncoding("utf-8");
				
		//request�� post�� ���۵� id, pass�� String������ �־��ش�.
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		//member���̺� DB �۾��� �ϱ����� MemberDAOBean������ ������ش�.
		MemberDAOBean dmb = MemberDAOBean.getInstance();//�̱����̱� ������ �����ڰ� �ƴ� �޼���� ����
		int[] result = dmb.getLoginCheck(id, pass);//���̵�� ��ȣ�� �����ϹǷ� �α��ΰ� �ٸ��� ���� �״�� ����Ѵ�.
		request.setAttribute("check", new Integer(result[0]));
		return "/lib/returnIntCheck.jsp";
	}

}
