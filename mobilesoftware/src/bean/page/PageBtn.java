package bean.page;

public class PageBtn {//������ ��ư�� ���� ������ ����
	//DB�� �������� ���� �� select count(*) from tbl_board�� ����.
	private int totalcol;
	//ù��° ��ư�� �� ����
	private int startPage;
	//������ ��ư�� �� ����
	private int endPage;
	//���� ��ư�� �����Ұ��ΰ� �Ǵ�
	private boolean prev;
	//���� ������ �����Ұ��ΰ� �Ǵ�.
	private boolean next;
	//������ ������ ��ȣ�� �������� ������ ũ�⸦ �־��ش�.
	private Paging paging;
	//������ ����
	private int btnSize = 10;
	
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	public void setTotalcol(int totalcol) {
		this.totalcol = totalcol;
		//���ݱ��� ���� �����͸� ���� ���������� ����� �����Ѵ�.
		pageMake();
	}
	
	private void pageMake() {
		//������ ��ư���� ���Ѵ�. �� �������� ������ ������ �������� ������ �ű⿡ �ٽ� �������� ������ ���ϸ� ������ ��ư�� ���� ���´�.
		endPage = (int)(Math.ceil(paging.getPageNum()/(double)btnSize)*btnSize);
		//������ ���������� ������ ������ ���� 1�� ���ϸ� ���� ������
		startPage = endPage-btnSize+1;
		//�� �������� ������ �� ���������� ǥ���ϴ� ������ ������ ������ �ø��ϸ� ���� ������
		int tempEndPage = (int)(Math.ceil(totalcol/(double)paging.getPageSize()));
		//������ ��ư�� �� ���������� ũ�ٸ� �������� ũ�⸦ ������ ��ư�� ������ ���� ��ȣ�� �ο����� �ʰ� �Ѵ�.
		if(endPage>tempEndPage) endPage = tempEndPage;
		//ó�������� ��ư�� 1�̶�� ������ư�� �ʿ������ �ƴ϶�� �־�� �Ѵ�.
		prev = startPage == 1 ? false : true;
		//���� ��������ư�� ���ڿ� ���������� ������ ������ ���Ѱ��� �� �������� �������� ũ�ٸ� ������ư�� �ʿ䰡 ����.
	    next = endPage * paging.getPageSize() >= totalcol ? false : true;
	}
	
	public int getTotalcol() {
		return totalcol;
	}
	
	public Paging getPaging() {
		return paging;
	}	
	
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public boolean isNext() {
		return next;
	}
	@Override
	public String toString() {
		return "PageBtn [totalcol=" + totalcol + ", startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev
				+ ", next=" + next + ", paging=" + paging + ", btnSize=" + btnSize + "]";
	}

}
