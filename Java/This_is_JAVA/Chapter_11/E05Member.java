package Chapter_11;

import java.util.Arrays;

public class E05Member implements Cloneable {
	public String name;
	public int age;
	public int[] scores;
	public E05Car car;
	
	public E05Member(String name, int age, int[] scores, E05Car car){
		this.name = name;
		this.age = age;
		this.scores = scores;
		this.car = car;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		E05Member cloned = (E05Member)super.clone();
		cloned.scores = Arrays.copyOf(this.scores, this.scores.length);
		cloned.car = new E05Car(this.car.model);
		return cloned;
	}
	
	public E05Member getMember(){
		E05Member cloned = null;
		try{
			cloned = (E05Member)clone();
		} catch (CloneNotSupportedException e){
			e.printStackTrace();
		}
		return cloned;
	}
}
