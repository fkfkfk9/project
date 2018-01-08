package bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.member.bean.MemberDAODb;

/**
 * Servlet implementation class DeleteMember
 */
@WebServlet("/DeleteMember")
public class DeleteMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("���� ����");
		String id = request.getParameter("id");
		MemberDAODb ldb = MemberDAODb.getInstance();
		//DB�� �����͸� �ҷ����� ���� ��ü�� ����
		PrintWriter out = response.getWriter();
		//���ϰ��� �Է��ϱ� ���� ����
		
		int check = ldb.deleteMember(id);
		HttpSession session = request.getSession();//������ �����ϱ� ���� �޾ƿ´�.
		session.setAttribute("id", "");//�޾ƿ� ���ǿ� ���� �־��ش�.
		System.out.println(check + "����");
		out.print(check);
	}


}
