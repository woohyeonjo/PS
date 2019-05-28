package Chapter_12;

public class E09WorkObject {
	public synchronized void methodA() {
		System.out.println("ThreadA의 methodA(); 작업실행");
		notify();
		try{
			wait();
		} catch (InterruptedException e){}
	}
	
	public synchronized void methodB(){
		System.out.println("ThreadB의 methodB(); 작업 실행");
		notify();
		try{
			wait();
		} catch (InterruptedException e){}
	}
}
