package Chapter_12;

public class E15ThreadGroupExample {
	public static void main(String[] args){
		ThreadGroup myGroup = new ThreadGroup("myGroup");
		E15WorkThread workThreadA = new E15WorkThread(myGroup, "workThreadA");
		E15WorkThread workThreadB = new E15WorkThread(myGroup, "workThreadB");
		
		workThreadA.start();
		workThreadB.start();
		
		System.out.println("[ main 스레드 그룹의 list() 메소드 출력 내용 ]");
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		mainGroup.list();
		System.out.println();
		
		try{ Thread.sleep(3000); } catch (InterruptedException e){}
		System.out.println("[ myGroup 스레드 그룹의 interrupt() 메소드 호출 ]");
		myGroup.interrupt();
	}
}
