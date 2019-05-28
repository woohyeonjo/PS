package Chapter_06;

public class E18SingletonExample {
	public static void main(String args[]){
		/*
		 * E18Singleton obj1 = new Singleton();
		 * E18Singleton obj2 = new Singleton();
		 */
		
		E18Singleton obj1 = E18Singleton.getInstatnce();
		E18Singleton obj2 = E18Singleton.getInstatnce();
		
		if(obj1 == obj2){
			System.out.println("같은 Singleton 객체 입니다.");
		} else {
			System.out.println("다른 Singleton 객체 입니다.");
		}
	}
}
