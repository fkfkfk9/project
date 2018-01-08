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
		"*.do"//.do�� �Է��� ��� ��ε��� Controller�� ���Եȴ�.
	}, 
	initParams = { 
	    @WebInitParam(name = "propertyConfig", value = "commandMapping.properties")
	})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//commandMapping.properties�� ����� Ű�� ������ �����ϱ� ���� ���� �����Ѵ�.
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
    //init�� ������ ó�� ���۵Ǵ� �������� ���Ǵ� �޼��� �̴�.
    //����� commandMapping.properties�� ����� ��� Ű�� ������ 
    //���� ���۰� ���ÿ� commandMap �����س��� ���� ���
	public void init(ServletConfig config) throws ServletException {
		//propertyConfig�� �����س��� �����̸��� �ҷ��´�. �� commandMapping.properties�� ������
		String props = config.getInitParameter("propertyConfig");
		//�츮�� commandMapping.properties������ property������ �־���⶧���� �����̸��� �־���
		String realFolder = "/property";
		//���� context�� ������ �� ���� �������� ������Ʈ�� ��θ� �����´�.
		ServletContext context = config.getServletContext();
		//������� ������Ʈ�� �⺻��ο� ���� �����س��� ������ �־��ְ� ���ϸ���� �����ش�.
		//context+realFolder+props = /mobilesoftware/property/commandMapping.properties
		String realPath = context.getRealPath(realFolder) +"\\"+props;
							    
		//Properties�� �о�� ������ ����� ������ ������ FileInputStream ������ �ʱ�ȭ ��Ŵ
		Properties pr = new Properties();
		FileInputStream f = null;
		try{
			//��θ� �־� ������ �ҷ��´�.
			f = new FileInputStream(realPath); 
			//PropertiesŸ���� ������ Properties������ ������ �־��ش�.
			pr.load(f);
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (f != null) try { f.close(); } catch(IOException ex) {}
		}
		//Properties������ Ű���� Iterator�� �ִ´�.
		Iterator<?> keyIter = pr.keySet().iterator();
		//Iterator�� ������ ���������� �ݺ��۾��� �Ѵ�.
		while( keyIter.hasNext() ) {
			//Ű���� ������ ��Ʈ�������� �־��ش�.
			String command = (String)keyIter.next();
			//Properties�������� �ش� Ű���� �־� Ű�� �ش��ϴ� ���� ������
			String className = pr.getProperty(command);
			try{
				//Properties���Ͽ� ���ִ� ���� ���� Ŭ���� �̸��̱� ������
				//�ش� Ŭ������ �ش��ϴ� ��ü�� �����Ѵ�.
				Class<?> commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance();
				//Ű�� ������ ��ü�� �ʿ� �־��ش�. �� �۾��� ������ �����Ҷ� 1�� �۵��Ѵ�. 
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
		requestPro(request, response);//get�������� �����Ͱ� �Ѿ���� �۵��� �޼���
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(
		HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestPro(request, response);//post�������� �����Ͱ� �Ѿ���� �۵��� �޼���
	}
	
	//�� get�̵� post�̵� �ᱹ ��� �۾��� requestPro�� �����ϰ� �ȴ�.
	private void requestPro(
		HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		//index.jsp�� �ѷ��� jsp������ ��θ� �����ų ����
		String view = null;
		//Properties���Ͽ� ����� ��� Ŭ�������� CommandAction�������̽��� ��ӹޱ� ������
		//�������� ���� CommandAction������ ����Ѵ�.
		CommandAction com=null;
		try {
			//����ڰ� �Է��� uri�ּҸ� �����´�. mobilesoftware/???.do
			String command = request.getRequestURI();
			//command���ڿ��� mobilesoftware�� �����ϴ°� �´��� Ȯ���Ѵ�.
	        if(command.indexOf(request.getContextPath()) == 0) 
	        	//command���ڿ����� mobilesoftware�κи��� �����ϰ� ���� ���� �����Ѵ�.
	           command = command.substring(request.getContextPath().length());
	           System.out.println(command);
	        //�� ���� ���� ���� Ű���� �Ǳ� ������ commandMap�� Ű�� �־��ָ�
	        //���� CommandAction��ü�� �޾� �� �� �ִ�.
	        com = (CommandAction)commandMap.get(command);
	        //�޾ƿ� ��ü�� requestPro �޼ҵ带 �����Ͽ� ������ �۾��� ���ְ� 
	        //������ ������ ȭ���ּҸ� ���Ϲ޴´�
	        view = com.requestPro(request, response);
	        System.out.println(view);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		//request�� ȭ�鿡 ��� �������� �����Ų��.
		request.setAttribute("cont",view);
		//index.jsp�� �̵��Ѵ�.
	    RequestDispatcher dispatcher = 
	       request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
}