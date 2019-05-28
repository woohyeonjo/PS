package Chapter_06;

public class E05KoreanExample {
	public static void main(String args[]){
		E05Korean k1 = new E05Korean("박자바 ", "011225-1234567");
		System.out.println("k1.name : " + k1.name);
		System.out.println("k1.ssn : " + k1.ssn);
		
		E05Korean k2 = new E05Korean("김자바", "930525-0654321");
		System.out.println("k2.name :" + k2.name);
		System.out.println("k2.ssn : " + k2.ssn);
	}
}
