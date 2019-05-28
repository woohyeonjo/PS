package Chapter_07;

public class E07CarExample {
	public static void main(String args[]){
		E07Car car = new E07Car();
		
		for(int i = 0 ; i < 50 ; i++){
			int problemLocation = car.run();
			
			switch(problemLocation){
			case 1:
				System.out.println("앞 왼쪽 HankookTire로 교체");
				car.frontLeftTire = new E07HankookTire("앞 왼쪽", 15);
				break;
			case 2:
				System.out.println("앞 오른쪽 KumhoTire로 교체");
				car.frontRightTire = new E07KumhoTire("앞 오른쪽", 13);
				break;
			case 3:
				System.out.println("뒷 왼쪽 HankookTire로 교체");
				car.backLeftTire = new E07HankookTire("뒷 왼쪽", 14);
				break;
			case 4:
				System.out.println("뒷 오른쪽 KumhoTire로 교체");
				car.backRightTire = new E07KumhoTire("뒷 오른쪽", 17);
				break;
			}
			System.out.println("-------------------------------------------------");
		}
	}
}
