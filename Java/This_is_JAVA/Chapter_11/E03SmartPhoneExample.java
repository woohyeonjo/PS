package Chapter_11;

public class E03SmartPhoneExample {
	public static void main(String args[]){
		E03SmartPhone myPhone = new E03SmartPhone("구글", "안드로이드");
		
		String strObj = myPhone.toString();
		System.out.println(strObj);
		
		System.out.println(myPhone);
	}
}
