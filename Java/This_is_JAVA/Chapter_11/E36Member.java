package Chapter_11;

public class E36Member implements Comparable<E36Member> {
	String name;
	E36Member(String name){
		this.name = name;
	}
	
	@Override
	public int compareTo(E36Member o){
		return name.compareTo(o.name);
	}
}
