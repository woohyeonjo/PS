package Chapter_07;

public class E07Tire {

	public int maxRotation;
	public int accumulatedRotation;
	public String location;
	
	public E07Tire(String location, int maxRotation){
		this.location = location;
		this.maxRotation = maxRotation;
	}
	
	public boolean roll(){
		++accumulatedRotation;
		if(accumulatedRotation < maxRotation){
			System.out.println(location + " Tire 수명 : " + (maxRotation - accumulatedRotation) + "회");
			return true;
		} else {
			System.out.println("*** " + location + " Tire 펑크 ***");
			return false;
		}
	}
	
}
