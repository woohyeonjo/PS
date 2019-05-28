package Chapter_02;

public class E07LongExample {
	public static void main(String args[]){
		long var1 = 10;
		long var2 = 20L;
		//long var3 = 1000000000000; // int타입의 저장범위를 넘어서는 정수 리터럴에 'L'을 붙이지 않았기 때문이다.
		long var4 = 1000000000000L;
		
		System.out.println(var1);
		System.out.println(var2);
		System.out.println(var4);
	}
}
