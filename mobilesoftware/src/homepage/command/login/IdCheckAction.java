package homepage.command.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.member.MemberDAOBean;
import homepage.command.CommandAction;

public class IdCheckAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//request������ �ѱ��� ������� ������ �����ϱ��� ���ڼ��� �ٲ۴�.
		request.setCharacterEncoding("utf-8");
				
		//request�� post�� ���۵� id, pass�� String������ �־��ش�.
		String id = request.getParameter("id");
		
		//member���̺� DB �۾��� �ϱ����� MemberDAOBean������ ������ش�.
		MemberDAOBean dmb = MemberDAOBean.getInstance();//�̱����̱� ������ �����ڰ� �ƴ� �޼���� ����
		int check = dmb.idCheck(id);//���Ϲ��� int�� �����͸� �Է��Ѵ�.
		System.out.println("idüũ�� ���Ϲ��� �� : " + check);//���Ϲ������� �ֿܼ��� Ȯ���ϱ� ���� �۾�
		request.setAttribute("check", new Integer(check));
		return "/lib/returnIntCheck.jsp";
	}

}
