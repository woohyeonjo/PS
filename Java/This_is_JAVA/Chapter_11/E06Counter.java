package Chapter_11;

public class E06Counter {
	private int no;
	
	public E06Counter(int no){
		this.no = no;
	}
	
	@Override
	protected void finalize() throws Throwable{
		System.out.println(no + "번 객체의 finalize()가 실행됨");
	}
}
