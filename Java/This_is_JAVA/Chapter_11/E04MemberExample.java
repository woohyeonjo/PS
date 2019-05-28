package Chapter_11;

public class E04MemberExample {
	public static void main(String args[]){
		E04Member original = new E04Member("blue", "홍길동", "12345", 25, true);
		
		E04Member cloned = original.getMember();
		cloned.password = "67890";
		
		System.out.println("[복제 객체의 필드 값]");
		System.out.println("id: " + cloned.id);
		System.out.println("name: " + cloned.name);
		System.out.println("password: " + cloned.password);
		System.out.println("age: " + cloned.age);
		System.out.println("adult: " + cloned.adult);
		
		System.out.println();
		
		System.out.println("[원본 객체의 필드 값]");
		System.out.println("id: " + original.id);
		System.out.println("name: " + original.name);
		System.out.println("password: " + original.password);
		System.out.println("age: " + original.age);
		System.out.println("adult: " + original.adult);
	}
}
