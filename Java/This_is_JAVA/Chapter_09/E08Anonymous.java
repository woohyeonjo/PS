package Chapter_09;

public class E08Anonymous {

	E08RemoteControl field = new E08RemoteControl(){
		@Override
		public void turnOn(){
			System.out.println("TV를 켭니다.");
		}
		@Override
		public void turnOff(){
			System.out.println("TV를 끕니다.");
		}
	};
	
	void method1(){
		E08RemoteControl localVar = new E08RemoteControl() {
			
			@Override
			public void turnOn() {
				// TODO Auto-generated method stub
				System.out.println("Audio를 켭니다.");
				
			}
			
			@Override
			public void turnOff() {
				// TODO Auto-generated method stub
				System.out.println("Audio를 끕니다.");
			}
		};
		
		localVar.turnOn();
	}
	
	void method2(E08RemoteControl rc){
		rc.turnOn();
	}
}
