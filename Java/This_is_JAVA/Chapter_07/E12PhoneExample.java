package Chapter_07;

public class E12PhoneExample {
	public static void main(String args[]){
		//E12Phone phone = new E12Phone();
		
		E12SmartPhone smartPhone = new E12SmartPhone("홍길동");
		
		smartPhone.turnOn();
		smartPhone.internetSearch();
		smartPhone.turnOff();
	}
}
