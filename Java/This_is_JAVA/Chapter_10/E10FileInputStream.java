package Chapter_10;

public class E10FileInputStream implements AutoCloseable {
	private String file;
	
	public E10FileInputStream(String file){
		this.file = file;
	}
	
	public void read(){
		System.out.println(file + "을 읽습니다.");
	}
	
	@Override
	public void close() throws Exception {
		System.out.println(file + "을 닫습니다.");
	}
}
