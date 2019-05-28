package Chapter_12;

public class E10WaitNotifyExample {
	public static void main(String[] args){
		E10DataBox dataBox = new E10DataBox();
		
		E10ProducerThread producerThread = new E10ProducerThread(dataBox);
		E10ConsumerThread consumerThread = new E10ConsumerThread(dataBox);
		
		producerThread.start();
		consumerThread.start();
	}
}
