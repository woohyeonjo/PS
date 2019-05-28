package Chapter_07;

public abstract class E12Phone {
	
	public String owner;
	
	public E12Phone(String owner){
		this.owner = owner;
	}
	
	public void turnOn(){
		System.out.println("폰 전원을 켭니다.");
	}
	
	public void turnOff(){
		System.out.println("폰 전원을 끕니다.");
	}
}
