package Chapter_12;

public class E13AutoSaveThread extends Thread{
	public void save(){
		System.out.println("작업 내용을 저장함.");
	}
	
	@Override
	public void run(){
		while(true){
			try{
				Thread.sleep(1000);
			} catch(InterruptedException e){ break; }
		save();
		}
	}
}
