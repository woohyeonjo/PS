package Chapter_13;

public class E01BoxExample {
	public static void main(String[] args){
		E01Box<String> box1 = new E01Box<String>();
		box1.set("hello");
		String str = box1.get();
		
		E01Box<Integer> box2 = new E01Box<Integer>();
		box2.set(6);;
		int value = box2.get();
	}
}
