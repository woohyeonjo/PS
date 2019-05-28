package Chapter_07;

public class E02Student extends E02People{
	public int studentNo;
	
	public E02Student(String name, String ssn, int studentNo){
		super(name, ssn);
		this.studentNo = studentNo;
	}
}