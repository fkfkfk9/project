package bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.board.bean.BoardBeanDb;
import bbs.board.bean.BoardDAODb;

/**
 * Servlet implementation class UpdateBoard
 */
@WebServlet("/DeleteBoard")
public class DeleteBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBoard() {
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
		System.out.println("Delete 서블릿 진입");
		int num = Integer.parseInt(request.getParameter("num"));
		int ref = Integer.parseInt(request.getParameter("ref"));
		String passwd = request.getParameter("passwd");
		
		BoardDAODb bdd = BoardDAODb.getInstance();
		//DB의 데이터를 불러오기 위해 객체를 생성
		PrintWriter out = response.getWriter();
		//리턴값을 입력하기 위한 변수
		
		int check = bdd.deleteBoard(num, ref, passwd);
		System.out.println(check + "델리트성공");
		out.print(check);
	}

}
