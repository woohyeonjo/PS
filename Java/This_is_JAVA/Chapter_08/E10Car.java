package Chapter_08;

public class E10Car {
	E09Tire[] tires = {
			new E09HankookTire(),
			new E09HankookTire(),
			new E09HankookTire(),
			new E09HankookTire()
	};
	
	void run(){
		for(E09Tire tire : tires){
			tire.roll();
		}
	}
}
