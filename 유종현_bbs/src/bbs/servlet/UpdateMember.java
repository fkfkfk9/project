package bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.member.bean.MemberBeanDb;
import bbs.member.bean.MemberDAODb;

/**
 * Servlet implementation class UpdateMember
 */
@WebServlet("/UpdateMember")
public class UpdateMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doAction(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doAction(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		System.out.println("���� ����");		
		MemberBeanDb mbd = new MemberBeanDb();
		mbd.setId(request.getParameter("id"));
		System.out.println(mbd.getId());
		mbd.setPasswd(request.getParameter("passwd"));
		System.out.println(mbd.getPasswd());
		String newpasswd = request.getParameter("newpasswd");
		System.out.println(newpasswd);		
		mbd.setName(request.getParameter("name"));		
		mbd.setAddr(request.getParameter("addr"));
		mbd.setTel(request.getParameter("tel"));
		MemberDAODb ldb = MemberDAODb.getInstance();
		//DB�� �����͸� �ҷ����� ���� ��ü�� ����
		PrintWriter out = response.getWriter();
		//���ϰ��� �Է��ϱ� ���� ����
		
		int check = ldb.updatemember(mbd, newpasswd);
		System.out.println(check + "����");
		out.print(check);
	}

}
