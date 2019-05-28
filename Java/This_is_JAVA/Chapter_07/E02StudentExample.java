package Chapter_07;

public class E02StudentExample {
	public static void main(String[] args){
		E02Student student = new E02Student("홍길동", "123456-1234567", 1);
		System.out.println("name : " + student.name);
		System.out.println("ssn : " + student.ssn);
		System.out.println("studentNo : " + student.studentNo);
	}
}
