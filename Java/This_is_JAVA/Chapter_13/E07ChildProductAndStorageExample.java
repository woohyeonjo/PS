package Chapter_13;


public class E07ChildProductAndStorageExample {
	public static void main(String[] args){
		E07ChildProduct<television, String, String> product = new E07ChildProduct<>();
		product.setKind(new television());
		product.setModel("SmartTV");
		product.setCompany("Samsung");
		
		E07Storage<television> storage = new E07StorageImpl<television>(100);
		storage.add(new television(),  0);
		television tv = storage.get(0);
	}
}
