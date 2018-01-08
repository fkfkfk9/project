package homepage.command.majorboard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.menu02.MajorBoardDAOBean;
import bean.menu02.MajorBoardDTOBean;
import bean.page.PageBtn;
import bean.page.Paging;
import homepage.command.CommandAction;

public class MajorBoardListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//request������ �ѱ��� ������� ������ �����ϱ��� ���ڼ��� �ٲ۴�.
		request.setCharacterEncoding("utf-8");
		
		int grade = Integer.parseInt(request.getParameter("grade"));//�г��� ������ �޴´�.
		int sub = Integer.parseInt(request.getParameter("sub"));//�б⸦ ������ �޴´�.
		int articleCnt = 0;//�Խ����� �� �� ���� �޾ƿ� ������ �ʱ�ȭ
		String classes = "";//������� �����ϱ� ���� ����
		List<MajorBoardDTOBean> articleList = null;//�Խ��ǿ� ����� ������ �޾ƿ� ����Ʈ�� �ʱ�ȭ		
		
		//����¡�� ���� �����۾�
		PageBtn pb = new PageBtn();
		Paging paging = new Paging();//������ ������ ���� ��ü ����
		//�⺻���� pageNum = 1 page Size = 10
		String pageNum = request.getParameter("pageNum");//ȭ�鿡�� �޾ƿ� ����ڰ� ������ ������ ��ȣ
		//�Ѿ�� ���� ������ �� ���� �־��ش�.
		if(pageNum == null || pageNum.equals("")) paging.setPageNum(1);
		else paging.setPageNum(Integer.parseInt(pageNum));
		pb.setPaging(paging);//�������� �ѹ��� ���ڷ� �Ľ� ������Ʈ�� ���� ���� String�� ������ ����
		//END ����¡ ����
		
		//���� �����ϱ� ���� DAOŬ������ ����
		MajorBoardDAOBean mbdao = MajorBoardDAOBean.getInstance();		
		
		//�Է¹��� �г�� �б⿡ �ٶ� ����� �гⰪ�� �Է��� �ҷ����� �������� �������ش�.
		if(grade == 1) {			
			request.setAttribute("grade", "1�г�");
			if(sub == 1) classes="C���";
			else if(sub == 2) classes="��ǻ�� ����";
		}else if(grade == 2) {			
			request.setAttribute("grade", "2�г�");
			if(sub == 1) classes="JAVA";
			else if(sub == 2) classes="�˰���";
		}else if(grade == 3) {			
			request.setAttribute("grade", "3�г�");
			if(sub == 1) classes="�ü��";
			else if(sub == 2) classes="��Ʈ��ũ";
		}if(grade == 4) {			
			request.setAttribute("grade", "4�г�");
			classes="���� ������Ʈ";	
		}
		request.setAttribute("sub", classes);
		/*END OF Ÿ��Ʋ ����� ������ ���� ������ ����*/
		
		//�Խ��� ���� �Ѱ����� ���Ѵ�. ���񺰷� ������ �ٸ��� ����ؾ� �ϱ� ������ �Ű������� �����
		articleCnt = mbdao.getarticleCnt(classes);
		
		//����¡ ������ �۾�
		//������ ������ �κ��� ��ü ������ �������ش�.
		pb.setTotalcol(articleCnt);
		articleList = mbdao.getArticle(classes, pb.getPaging());
		
		//setAttribute�� ��ü �����͸� �䱸�ϱ� ������ ��Ƽ���� �����ؼ� ������.
		request.setAttribute("articleCnt", new Integer(articleCnt));//�ѱ� ����
		request.setAttribute("articleList", articleList);//����� �Խñ� ����
		request.setAttribute("pageBtn", pb);
		
		return "/majorboard/majorboardlist.jsp";
	}

}
