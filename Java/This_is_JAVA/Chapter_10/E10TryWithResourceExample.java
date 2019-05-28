package Chapter_10;

public class E10TryWithResourceExample {
	public static void main(String args[]){
		try(E10FileInputStream fis = new E10FileInputStream("file.txt")){
			fis.read();
			throw new Exception();
		} catch(Exception e){
			System.out.println("예외 처리 코드가 실행되었습니다.");
		}
	}
}
