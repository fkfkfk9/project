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
		//request������ �ѱ��� ������� ������ �����ϱ��� ���ڼ��� �ٲ۴�.
		request.setCharacterEncoding("utf-8");
		
		//ȸ�������� ã�ƿ� id�� ������ ���� ������ �޾ƿ´�.
		HttpSession session = request.getSession();
		
		//member���̺� DB �۾��� �ϱ����� MemberDAOBean������ ������ش�.
		MemberDAOBean dmb = MemberDAOBean.getInstance();//�̱����̱� ������ �����ڰ� �ƴ� �޼���� ����
		MemberDTOBean mdtb = dmb.updateinfo((String)session.getAttribute("id"));//ȸ����ü�� ������ �޴´�.
		String[] hp = mdtb.getHp().split("-");
		String[] email = mdtb.getEmail().split("@");
		if(mdtb.getM_level() == 1) request.setAttribute("m_level_val", "�л�");
		else if(mdtb.getM_level() == 2) request.setAttribute("m_level_val", "����");
		request.setAttribute("name", mdtb.getName());
		request.setAttribute("hp1", hp[0]);
		request.setAttribute("hp2", hp[1]);
		request.setAttribute("hp3", hp[2]);
		request.setAttribute("email1", email[0]);
		request.setAttribute("email2", email[1]);		
		
		return "/login/update_form.jsp";//ȸ�������������� �ҷ��´�.
	}

}
