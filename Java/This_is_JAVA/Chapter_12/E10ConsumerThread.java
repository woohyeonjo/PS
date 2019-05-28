package Chapter_12;

public class E10ConsumerThread extends Thread{
	private E10DataBox dataBox;
	
	public E10ConsumerThread(E10DataBox dataBox){
		this.dataBox = dataBox;
	}
	
	@Override
	public void run(){
		for(int i = 1; i <= 3 ; i ++){
			String data = dataBox.getData();
		}
	}
}
