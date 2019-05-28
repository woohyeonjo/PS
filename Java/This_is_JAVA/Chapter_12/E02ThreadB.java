package Chapter_12;

public class E02ThreadB extends Thread {
	public void run(){
		for(int i = 0 ; i < 2 ; i ++){
			System.out.println(getName() + "가 출력한 내용");
		}
	}
}
