package Chapter_07;

public class E13Cat extends E13Animal{

	public E13Cat(){
		this.kind = "포유류";
	}
	
	@Override
	public void sound(){
		System.out.println("야옹");
	}
}
