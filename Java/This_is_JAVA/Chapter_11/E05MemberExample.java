package Chapter_11;

public class E05MemberExample {
	public static void main(String args[]){
		E05Member original = new E05Member("홍길동", 25, new int[]{90,90}, new E05Car("소나타"));
		
		E05Member cloned = original.getMember();
		cloned.scores[0] = 100;
		cloned.car.model = "그랜져";
		
		System.out.println(cloned.scores.length +","+ original.scores.length);
		
		System.out.println("[복제 객체의 필드값]");
		System.out.println("name : " + cloned.name);
		System.out.println("age : " + cloned.age);
		System.out.print("scores : {");
		for(int score : cloned.scores){
			System.out.print(score);
			System.out.print((score == (cloned.scores[cloned.scores.length-1])?"":","));
		}
		System.out.println("}");
		System.out.println("car: " + cloned.car.model);
		
		System.out.println();
		
		System.out.println("[원본 객체의 필드값]");
		System.out.println("name : " + original.name);
		System.out.println("age : " + original.age);
		System.out.print("scores : {");
		for(int score : original.scores){
			System.out.print(score);
			System.out.print((score == (original.scores[original.scores.length-1])?"":","));
		}
		System.out.println("}");
		System.out.println("car: " + original.car.model);
	}
}
