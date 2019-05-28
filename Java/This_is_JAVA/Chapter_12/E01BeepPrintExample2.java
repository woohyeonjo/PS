package Chapter_12;

public class E01BeepPrintExample2 {
	public static void main(String[] args){
		Runnable beepTask = new E01BeepTask();
		Thread thread = new Thread(beepTask);
		thread.start();
		
		for(int i = 0 ; i < 5 ; i ++){
			System.out.println("띵");
			try{ Thread.sleep(500);;}
			catch(Exception e){}
		}
	}
}
