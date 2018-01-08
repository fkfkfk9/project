package bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.board.bean.BoardBeanDb;
import bbs.board.bean.BoardDAODb;

/**
 * Servlet implementation class WriteBoard
 */
@WebServlet("/WriteBoard")
public class WriteBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteBoard() {
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
		System.out.println("서블릿 진입");		
		BoardBeanDb bbd = new BoardBeanDb();
		bbd.setNum(Integer.parseInt(request.getParameter("num")));
		System.out.println(bbd.getNum());
		HttpSession session = request.getSession();
		bbd.setWriter((String)session.getAttribute("id"));
		bbd.setSubject(request.getParameter("subject"));
		bbd.setPasswd(request.getParameter("passwd"));
		bbd.setReg_date(new Timestamp(System.currentTimeMillis()));
		bbd.setRef(Integer.parseInt(request.getParameter("ref")));
		bbd.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		bbd.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		bbd.setContent(request.getParameter("content"));
		bbd.setIp(request.getRemoteAddr());
		BoardDAODb bdd = BoardDAODb.getInstance();
		//DB의 데이터를 불러오기 위해 객체를 생성
		PrintWriter out = response.getWriter();
		//리턴값을 입력하기 위한 변수
		
		int check = bdd.insertBoard(bbd);
		System.out.println(check + "성공");
		out.print(check);
	}


}
