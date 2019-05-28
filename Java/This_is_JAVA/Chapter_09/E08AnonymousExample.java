package Chapter_09;

public class E08AnonymousExample {
	public static void main(String args[]){
		E08Anonymous anony = new E08Anonymous();
		
		anony.field.turnOn();
		anony.method1();
		anony.method2(
				new E08RemoteControl() {
					
					@Override
					public void turnOn() {
						// TODO Auto-generated method stub
						System.out.println("Smart TV를 켭니다.");
					}
					
					@Override
					public void turnOff() {
						// TODO Auto-generated method stub
						System.out.println("Smart TV를 끕니다.");
					}
				});
	}
}
