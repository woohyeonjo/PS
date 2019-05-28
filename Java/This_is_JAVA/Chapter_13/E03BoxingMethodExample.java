package Chapter_13;

public class E03BoxingMethodExample {
	public static void main(String[] args){
		E01Box<Integer> box1 = E03Util.<Integer>boxing(100);
		int intValue = box1.get();
		
		E01Box<String> box2 = E03Util.boxing("홍길동");
		String strValue = box2.get();
	}
}
