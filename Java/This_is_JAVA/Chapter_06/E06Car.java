package Chapter_06;

public class E06Car {

	String company = "현대자동차";
	String model;
	String color;
	int maxSpeed;
	
	E06Car(){
		
	}
	
	E06Car(String model){
		this.model = model;
	}
	
	E06Car(String model, String color){
		this.model = model;
		this.color = color;
	}
	
	E06Car(String model, String color, int maxSpeed){
		this.model = model;
		this.color = color;
		this.maxSpeed = maxSpeed;
	}
}
