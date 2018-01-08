package enumfile;

public enum ReturnInt {
	//DB에서 SELECT문을 실행할때 리턴값 용도로 상수를 정의함
	SUCCESS(1), FAIL(-1), ERROR(0);
	private final int value;
	private ReturnInt(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
