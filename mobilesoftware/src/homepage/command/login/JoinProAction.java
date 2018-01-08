package homepage.command.login;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.member.MemberDAOBean;
import bean.member.MemberDTOBean;
import homepage.command.CommandAction;

public class JoinProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//request������ �ѱ��� ������� ������ �����ϱ��� ���ڼ��� �ٲ۴�.
		request.setCharacterEncoding("utf-8");
						
		//request�� post�� ���۵� ȸ������ ������ bean��ü�� �־��ش�.
		MemberDTOBean mdtb = new MemberDTOBean();
		mdtb.setId(request.getParameter("id"));
		mdtb.setPass(request.getParameter("pass"));
		mdtb.setName(request.getParameter("name"));
		mdtb.setHp(request.getParameter("hp"));
		mdtb.setEmail(request.getParameter("email"));
		mdtb.setRegist_day(new Timestamp(System.currentTimeMillis()));//�ý����� ����ð�
		mdtb.setM_level(Integer.parseInt(request.getParameter("m_level")));//���������� �ٲ��ش�.
		System.out.println("ȸ�������� ������ ����Ȯ�� hp: " + mdtb.getHp() //�׽�Ʈ�� ��¹�
				+ ", email : " + mdtb.getEmail() + ", m_level : " + mdtb.getM_level());
		
		//member���̺� DB �۾��� �ϱ����� MemberDAOBean������ ������ش�.
		MemberDAOBean dmb = MemberDAOBean.getInstance();//�̱����̱� ������ �����ڰ� �ƴ� �޼���� ����
		int check = dmb.memberinsert(mdtb);//���Ϲ��� int�� �����͸� �Է��Ѵ�.
		System.out.println("memberinsert ���Ϲ��� �� : " + check);//���Ϲ������� �ֿܼ��� Ȯ���ϱ� ���� �۾�
		request.setAttribute("check", new Integer(check));
		
		return "/lib/returnIntCheck.jsp";
	}

}
