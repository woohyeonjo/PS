package Chapter_09;

public class E06ButtonExample {
	public static void main(String args[]){
		E06Button btn = new E06Button();
		
		btn.setOnClickListener(new E06CallListener());
		btn.touch();
		
		btn.setOnClickListener(new E06MessageListener());
		btn.touch();
	}
}
