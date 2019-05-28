package Chapter_12;

public class E09ThreadB extends Thread {
	private E09WorkObject workObject;
	
	public E09ThreadB(E09WorkObject workObject) {
		this.workObject = workObject;
	}
	
	@Override
	public void run() {
		for(int i = 0 ; i < 10 ; i ++){
			workObject.methodB();
		}
	}
}
