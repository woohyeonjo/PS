package Chapter_12;

public class E09WaitNotifyExample {
	public static void main(String[] args){
		E09WorkObject sharedObject = new E09WorkObject();
		
		E09ThreadA threadA = new E09ThreadA(sharedObject);
		E09ThreadB threadB = new E09ThreadB(sharedObject);
		
		threadA.start();
		threadB.start();
	}
}
