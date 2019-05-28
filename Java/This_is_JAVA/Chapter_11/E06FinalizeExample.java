package Chapter_11;

public class E06FinalizeExample {
	public static void main(String args[]){
		E06Counter counter = null;
		for(int i = 1 ; i <=50 ; i++){
			counter = new E06Counter(i);
			
			counter = null;
			
			System.gc();
		}
	}
}
