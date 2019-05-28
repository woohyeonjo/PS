package Chapter_07;

public class E13Dog extends E13Animal{

	public E13Dog(){
		this.kind = "포유류";
	}
	
	@Override
	public void sound(){
		System.out.println("멍멍");
	}
}
