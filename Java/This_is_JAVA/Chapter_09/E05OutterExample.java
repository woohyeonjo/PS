package Chapter_09;

public class E05OutterExample {
	public static void main(String args[]){
		E05Outter outter = new E05Outter();
		E05Outter.Nested nested = outter.new Nested();
		nested.print();
	}
}
