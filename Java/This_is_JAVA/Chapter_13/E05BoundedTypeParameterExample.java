package Chapter_13;

public class E05BoundedTypeParameterExample {
	public static void main(String[] args){
		//String str = E05util.compare("a", "b");
		
		int result1 = E05Util.compare(10, 20);
		System.out.println(result1);
		
		int result2 = E05Util.compare(4.5,  3);
		System.out.println(result2);
	}
}
