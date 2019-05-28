package Chapter_07;

public class E09DriverExample {
	public static void main(String args[]){
		E09Driver driver = new E09Driver();
		
		E09Bus bus = new E09Bus();
		E09Taxi taxi = new E09Taxi();
		
		driver.drive(bus);
		driver.drive(taxi);
	}
}
