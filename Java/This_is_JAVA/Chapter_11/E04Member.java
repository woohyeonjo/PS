package Chapter_11;

public class E04Member implements Cloneable {
	public String id;
	public String name;
	public String password;
	public int age;
	public boolean adult;
	
	public E04Member(String id, String name, String password, int age, boolean adult) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.adult = adult;
	}
	
	public E04Member getMember(){
		E04Member cloned = null;
		try{
			cloned = (E04Member) clone();
		} catch (CloneNotSupportedException e){}
		return cloned;
	}
}
