package enumfile;

public enum ReturnInt {
	//DB���� SELECT���� �����Ҷ� ���ϰ� �뵵�� ����� ������
	SUCCESS(1), FAIL(-1), ERROR(0);
	private final int value;
	private ReturnInt(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
