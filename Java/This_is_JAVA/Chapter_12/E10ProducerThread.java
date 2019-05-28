package Chapter_12;

public class E10ProducerThread extends Thread {
	private E10DataBox dataBox;
	
	public E10ProducerThread(E10DataBox dataBox){
		this.dataBox = dataBox;
	}
	
	@Override
	public void run(){
		for(int i = 1 ; i <= 3 ; i  ++){
			String data = "Data-" + i;
			dataBox.setData(data);
		}
	}
}
