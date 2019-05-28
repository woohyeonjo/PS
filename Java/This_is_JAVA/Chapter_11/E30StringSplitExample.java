package Chapter_11;

public class E30StringSplitExample {
	public static void main(String args[]){
		String text = "홍길동&이수홍,박연수,김자바-최명호";
		
		String[] names = text.split("&|,|-");
		
		for(String name : names){
			System.out.println(name);
		}
	}
}
