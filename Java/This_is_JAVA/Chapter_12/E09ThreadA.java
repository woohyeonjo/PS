package Chapter_12;

public class E09ThreadA extends Thread {
	private E09WorkObject workObject;
	
	public E09ThreadA(E09WorkObject workObject) {
		this.workObject = workObject;
	}
	
	@Override
	public void run() {
		for(int i = 0 ; i < 10 ; i ++){
			workObject.methodA();
		}
	}
	
}
