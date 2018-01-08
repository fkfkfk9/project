package homepage.command.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.member.MemberDAOBean;
import enumfile.ReturnInt;
import homepage.command.CommandAction;

public class LoginProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//request������ �ѱ��� ������� ������ �����ϱ��� ���ڼ��� �ٲ۴�.
		request.setCharacterEncoding("utf-8");
		
		//request�� post�� ���۵� id, pass�� String������ �־��ش�.
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		//member���̺� DB �۾��� �ϱ����� MemberDAOBean������ ������ش�.
		MemberDAOBean dmb = MemberDAOBean.getInstance();//�̱����̱� ������ �����ڰ� �ƴ� �޼���� ����
		int[] iArr = dmb.getLoginCheck(id, pass);//id, pass�� ���� DB�� ������ �´��� Ȯ�� �� int�� ����
		int check = iArr[0];//�������� Ȯ���� ���� ���ϰ�
		int m_level = iArr[1];//����� ���� Ȯ���� ���� ���ϰ�
		//���ϰ��� �����Ͽ� �ݹ��Լ����� Ȯ���� �� �ְ��Ѵ�. new Integer�� ����ϴ� ������ setAttribute�� object�� �Ű������� ����ϱ� ����
		request.setAttribute("check", new Integer(check));
		System.out.println("�α��� ���ϰ� üũ : " + check);
		//���ϰ��� ������ ���  ���ǿ� id, ����� �������� �־������ν� �α��� ���¸� �����
		if(check == ReturnInt.SUCCESS.getValue()) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("m_level", m_level);
		}
		return "/lib/returnIntCheck.jsp";//���ϰ��� int�� ��� �������� ǥ�����ִ� ������
	}

}
