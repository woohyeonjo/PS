package Chapter_11;

public class E01MemberExample {
	public static void main(String args[]){
		E01Member obj1 = new E01Member("blue");
		E01Member obj2 = new E01Member("blue");
		E01Member obj3 = new E01Member("red");
		
		System.out.println(obj1.hashCode());
		System.out.println(obj2.hashCode());
		System.out.println(obj3.hashCode());
		
		if(obj1.hashCode() == obj2.hashCode()){
			System.out.println("hashCode is same");
			
			if(obj1.equals(obj2)){
				System.out.println("obj1과 obj2는 동등합니다.");
			} else {
				System.out.println("obj1과 obj2는 동등하지 않습니다.");
			}
		} else {
			System.out.println("hashCode is different");
		}
		
		if(obj1.hashCode() == obj3.hashCode()){
			System.out.println("hashCode is same");

			if(obj1.equals(obj3)){
				System.out.println("obj1과 obj3은 동등합니다.");
			} else {
				System.out.println("obj1과 obj3은 동등하지 않습니다.");
			}
		} else {
			System.out.println("hashCode is different");
		}
	}
}
