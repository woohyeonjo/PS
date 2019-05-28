package Chapter_13;

public class E02ProductExample {
	public static void main(String[] args){
		E02Product<TV, String> product1 = new E02Product<TV, String>();
		product1.setKind(new TV());
		product1.setModel("스마트 TV");
		TV tv = product1.getKind();
		String tvModel = product1.getModel();
		
		E02Product<Car, String> product2 = new E02Product<Car, String>();
		product2.setKind(new Car());
		product2.setModel("디젤");
		Car car = product2.getKind();
		String carModel = product2.getModel();
	}
}

class TV{}
