package homepage.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homepage.command.CommandAction;

/**
 * Servlet implementation class Controller
 */
@WebServlet(
	urlPatterns = { 
		"/Controller", 
		"*.do"//.do를 입력한 모든 경로들은 Controller로 오게된다.
	}, 
	initParams = { 
	    @WebInitParam(name = "propertyConfig", value = "commandMapping.properties")
	})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//commandMapping.properties에 저장된 키와 값들을 저장하기 위한 맵을 선언한다.
	private Map<String, Object> commandMap = new HashMap<String, Object>();      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    //init는 서버가 처음 시작되는 순간에만 사용되는 메서드 이다.
    //현재는 commandMapping.properties에 저장된 모든 키와 값들을 
    //서버 시작과 동시에 commandMap 저장해놓기 위해 사용
	public void init(ServletConfig config) throws ServletException {
		//propertyConfig에 저장해놓은 파일이름을 불러온다. 즉 commandMapping.properties를 가져옴
		String props = config.getInitParameter("propertyConfig");
		//우리는 commandMapping.properties파일을 property폴더에 넣어놨기때문에 폴더이름을 넣어줌
		String realFolder = "/property";
		//현재 context를 가져옴 즉 현재 가동중인 프로젝트의 경로를 가져온다.
		ServletContext context = config.getServletContext();
		//사용중인 프로젝트의 기본경로에 위에 정의해놓은 폴더를 넣어주고 파일명까지 더해준다.
		//context+realFolder+props = /mobilesoftware/property/commandMapping.properties
		String realPath = context.getRealPath(realFolder) +"\\"+props;
							    
		//Properties을 읽어올 변수와 경로의 파일을 가져올 FileInputStream 변수를 초기화 시킴
		Properties pr = new Properties();
		FileInputStream f = null;
		try{
			//경로를 넣어 파일을 불러온다.
			f = new FileInputStream(realPath); 
			//Properties타입의 변수에 Properties파일의 내용을 넣어준다.
			pr.load(f);
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (f != null) try { f.close(); } catch(IOException ex) {}
		}
		//Properties파일의 키값을 Iterator에 넣는다.
		Iterator<?> keyIter = pr.keySet().iterator();
		//Iterator의 내용이 끝날때까지 반복작업을 한다.
		while( keyIter.hasNext() ) {
			//키값을 가져와 스트링변수에 넣어준다.
			String command = (String)keyIter.next();
			//Properties변수에서 해당 키값을 넣어 키에 해당하는 값을 가져옴
			String className = pr.getProperty(command);
			try{
				//Properties파일에 들어가있는 값은 전부 클레스 이름이기 때문에
				//해당 클레스에 해당하는 객체를 생성한다.
				Class<?> commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance();
				//키와 생성한 객체를 맵에 넣어준다. 이 작업은 서버가 시작할때 1번 작동한다. 
				commandMap.put(command, commandInstance);
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (InstantiationException e) {
				e.printStackTrace();
			}catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(
		HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		requestPro(request, response);//get형식으로 데이터가 넘어오면 작동할 메서드
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(
		HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestPro(request, response);//post형식으로 데이터가 넘어오면 작동할 메서드
	}
	
	//즉 get이든 post이든 결국 모든 작업은 requestPro를 실행하게 된다.
	private void requestPro(
		HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		//index.jsp에 뿌려줄 jsp파일의 경로를 저장시킬 변수
		String view = null;
		//Properties파일에 저장된 모든 클래스들은 CommandAction인터페이스를 상속받기 때문에
		//다형성을 위해 CommandAction변수를 사용한다.
		CommandAction com=null;
		try {
			//사용자가 입력한 uri주소를 가져온다. mobilesoftware/???.do
			String command = request.getRequestURI();
			//command문자열이 mobilesoftware로 시작하는게 맞는지 확인한다.
	        if(command.indexOf(request.getContextPath()) == 0) 
	        	//command문자열에서 mobilesoftware부분만을 제거하고 남은 값을 저장한다.
	           command = command.substring(request.getContextPath().length());
	           System.out.println(command);
	        //그 남은 값이 맵의 키값이 되기 때문에 commandMap에 키로 넣어주면
	        //값인 CommandAction객체를 받아 올 수 있다.
	        com = (CommandAction)commandMap.get(command);
	        //받아온 객체의 requestPro 메소드를 실행하여 예정된 작업을 해주고 
	        //다음에 보여줄 화면주소를 리턴받는다
	        view = com.requestPro(request, response);
	        System.out.println(view);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		//request에 화면에 띄울 페이지를 저장시킨다.
		request.setAttribute("cont",view);
		//index.jsp로 이동한다.
	    RequestDispatcher dispatcher = 
	       request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
}