package Chapter_07;

public class E08CarExample {
	public static void main (String args[]){
		E08Car car = new E08Car();
		
		for(int i = 1 ; i <= 5 ; i++){
			int problemLocation = car.run();
			if(problemLocation != 0){
				System.out.println(car.tires[problemLocation - 1].location + " HankookTire로 교체");
				car.tires[problemLocation - 1] =
						new E07HankookTire(car.tires[problemLocation - 1].location, 15);
			}
			System.out.println("-------------------------------");
		}
	}
}
