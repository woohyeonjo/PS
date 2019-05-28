package Chapter_06;

public class E07Car {

	String company = "현대자동차";
	String model;
	String color;
	int maxSpeed;
	
	E07Car(){
		
	}
	
	E07Car(String model){
		this(model, "은색", 250);
	}
	
	E07Car(String model, String color){
		this(model, color, 250);
	}
	
	E07Car(String model, String color, int maxSpeed){
		this.model = model;
		this.color = color;
		this.maxSpeed = maxSpeed;
	}
}
