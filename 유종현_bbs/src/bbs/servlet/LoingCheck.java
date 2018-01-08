package bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import bbs.member.bean.MemberDAODb;

/**
 * Servlet implementation class LoingCheck
 */
@WebServlet("/LoingCheck")
public class LoingCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoingCheck() {
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
		
		int check = ldb.userCheck(id, request.getParameter("passwd"));
		System.out.println(check + "����");
		HttpSession session = request.getSession();//������ �����ϱ� ���� �޾ƿ´�.
		session.setAttribute("id", id);//�޾ƿ� ���ǿ� ���� �־��ش�.
		out.print(check);
	}
}
