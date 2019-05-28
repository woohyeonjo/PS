package Chapter_08;

public class E12VehicleExample {
	public static void main(String args[]){
		E11Vehicle vehicle = new E12Bus();
		
		vehicle.run();
		//vehicle.checkFare(); (x)
		
		E12Bus bus = (E12Bus) vehicle;
		
		bus.run();
		bus.checkFare();
	}
}
