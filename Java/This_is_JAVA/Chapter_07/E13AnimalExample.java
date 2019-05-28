package Chapter_07;

public class E13AnimalExample {
	public static void main(String args[]){
		E13Dog dog = new E13Dog();
		E13Cat cat = new E13Cat();
		dog.sound();
		cat.sound();
		System.out.println("-----");
		
		E13Animal animal = null;
		animal = new E13Dog();
		animal.sound();
		animal = new E13Cat();
		animal.sound();
		System.out.println("-----");
		
		animalSound(new E13Dog());
		animalSound(new E13Cat());
	}
	
	public static void animalSound(E13Animal animal){
		animal.sound();
	}
}
