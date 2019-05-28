package Chapter_13;

import javax.xml.stream.events.StartDocument;

public class E06Course<T> {
	private String name;
	private T[] students;
	
	public E06Course(String name, int capacity){
		this.name = name;
		students = (T[]) (new Object[capacity]);
	}
	
	public String getName() {return name;}
	public T[] getStudents() {return students;}
	public void add(T t) {
		for(int i = 0 ; i < students.length ; i++){
			if(students[i] == null){
				students[i] = t;
				break;
			}
		}
	}
}
