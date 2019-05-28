package Chapter_13;

public class E04CompareMethodExample {
	public static void main(String[] args){
		E04Pair<Integer, String> p1 = new E04Pair<>(1, "사과");
		E04Pair<Integer, String> p2 = new E04Pair<>(1, "사과");
		boolean result1 = E04Util.<Integer, String>compare(p1, p2);
		if(result1) {
			System.out.println("논리적으로 동등한 객체입니다.");
		} else {
			System.out.println("논리적으로 동등하지 않는 객체입니다.");
		}
		
		E04Pair<String, String> p3 = new E04Pair<String, String>("user1", "홍길동");
		E04Pair<String, String> p4 = new E04Pair<String, String>("user2", "홍길동");
		boolean result2 = E04Util.compare(p3, p4);
		if(result2){
			System.out.println("논리적으로 동등한 객체입니다.");
		} else {
			System.out.println("논리적으로 동등하지 않는 객체입니다.");
		}
	}
}
