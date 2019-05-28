package Chapter_08;

public class E13Driver {
	public void drive(E11Vehicle vehicle){
		if(vehicle instanceof E12Bus){
			E12Bus bus = (E12Bus) vehicle;
			bus.checkFare();
		}
		vehicle.run();
	}
}
