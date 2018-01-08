package bean.page;

public class Paging {//����¡ �۾��� �ϴµ� �ʿ��� ������ ����
	//������ ������, �̵��� ������
	private int pageNum;
	//������������ ��µ� �������� ����
	private int pageSize;
	
	public Paging() {//�ƹ� ������ ���ٸ� ù������ �̹Ƿ� �⺻���� �־��ش�.
		super();
		this.pageNum = 1;
		this.pageSize = 15;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		if(pageNum<=0) {//�������� 0���� �������� �����Ƿ� ������ �Ǵ�.
			this.pageNum = 1;
			return;
		}
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize <=0 || pageSize >= 50) {//�� �������� ��µ� �����͸� 50������ �����ϰ� �ߴ�.
			pageSize = 10;
			return;
		}
		this.pageSize = pageSize;
	}
	public int getStartQuery() {//DB���� �����͸� �̾ƿö� ���۵Ǵ� ��ġ
		return (this.pageNum-1)*this.pageSize+1;
	}
	public int getEndQuery() {//DB���� �����͸� �̾ƿö� ������ ��ġ ����Ŭ�� limit�� ���� ������ �۾�
		return getStartQuery()+this.pageSize-1;
	}
	@Override
	public String toString() {
		return "Paging [pageNum=" + pageNum + ", pageSize=" + pageSize + ", StartQuery=" + getStartQuery()
				+ ", EndQuery=" + getEndQuery() + "]";
	}	

}
