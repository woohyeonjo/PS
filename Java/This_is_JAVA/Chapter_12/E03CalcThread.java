package Chapter_12;

public class E03CalcThread extends Thread {
	public E03CalcThread(String name){
		setName(name);
	}
	
	public void run(){
		for(int i = 0 ; i < 2000000000 ; i ++){
			System.out.println(getName());
		}
	}
}
