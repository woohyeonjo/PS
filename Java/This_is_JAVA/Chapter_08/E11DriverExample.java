package Chapter_08;

public class E11DriverExample {
	public static void main(String args[]){
		E11Driver driver = new E11Driver();
		
		E11Bus bus = new E11Bus();
		E11Taxi taxi = new E11Taxi();
		
		driver.drive(bus);
		driver.drive(taxi);
	}
}
