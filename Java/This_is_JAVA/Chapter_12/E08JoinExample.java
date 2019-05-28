package Chapter_12;

public class E08JoinExample {
	public static void main(String[] args){
		E08SumThread sumThread = new E08SumThread();
		sumThread.start();
		
		try {
			sumThread.join();
		} catch (InterruptedException e){}
		
		System.out.println("1~100  합 : " + sumThread.getSum());
	}
}
