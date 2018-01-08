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
		//request파일중 한글이 있을경우 깨짐을 방지하귀해 문자셋을 바꾼다.
		request.setCharacterEncoding("utf-8");
						
		//request로 post로 전송된 회원가입 정보를 bean객체에 넣어준다.
		MemberDTOBean mdtb = new MemberDTOBean();
		mdtb.setId(request.getParameter("id"));
		mdtb.setPass(request.getParameter("pass"));
		mdtb.setName(request.getParameter("name"));
		mdtb.setHp(request.getParameter("hp"));
		mdtb.setEmail(request.getParameter("email"));
		mdtb.setRegist_day(new Timestamp(System.currentTimeMillis()));//시스템의 현재시간
		mdtb.setM_level(Integer.parseInt(request.getParameter("m_level")));//인터형으로 바꿔준다.
		System.out.println("회원가입폼 데이터 전송확인 hp: " + mdtb.getHp() //테스트용 출력문
				+ ", email : " + mdtb.getEmail() + ", m_level : " + mdtb.getM_level());
		
		//member테이블에 DB 작업을 하기위한 MemberDAOBean변수를 만들어준다.
		MemberDAOBean dmb = MemberDAOBean.getInstance();//싱글톤이기 때문에 생성자가 아닌 메서드로 생성
		int check = dmb.memberinsert(mdtb);//리턴받은 int형 데이터를 입력한다.
		System.out.println("memberinsert 리턴받은 값 : " + check);//리턴받은값을 콘솔에서 확인하기 위한 작업
		request.setAttribute("check", new Integer(check));
		
		return "/lib/returnIntCheck.jsp";
	}

}
