package Chapter_12;

public class E02ThreadNameExample {
	public static void main(String[] args){
		Thread mainThread = Thread.currentThread();
		System.out.println("프로그램 시작 스레드 이름: " + mainThread.getName());
		
		E02ThreadA threadA = new E02ThreadA();
		System.out.println("작업 스레드 이름 : " + threadA.getName());
		threadA.start();
		
		E02ThreadB threadB = new E02ThreadB();
		System.out.println("작업 스레드 이름 : " + threadB.getName());
		threadB.start();
	}
}
