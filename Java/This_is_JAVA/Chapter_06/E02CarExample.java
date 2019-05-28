package Chapter_06;

public class E02CarExample {
	public static void main(String args[]){
		E02Car myCar = new E02Car();
		
		System.out.println("제작회사 : " + myCar.compny);
		System.out.println("모델명 : " + myCar.model);
		System.out.println("색깔 : " + myCar.color);
		System.out.println("최고속도 : " + myCar.maxSpeed);
		System.out.println("현재속도 : " + myCar.speed);
		
		myCar.speed = 60;
		System.out.println("수정된 속도 : " + myCar.speed);
	}
}
