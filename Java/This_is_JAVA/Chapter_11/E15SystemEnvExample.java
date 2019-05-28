package Chapter_11;

public class E15SystemEnvExample {
	public static void main(String args[]){
		String javaHome = System.getenv("JAVA_HOME");
		System.out.println("JAVA_HOME : " + javaHome);
	}
}
