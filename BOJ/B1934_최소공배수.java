package BOJ;

import java.util.Scanner;

public class B1934_최소공배수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		int T = sc.nextInt();
		
		for(int i = 0 ; i < T ; ++i) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			int LCM = a * b / gcd(a, b);
			
			System.out.println(LCM);
		}
	}
	
	private static int gcd(int a, int b) {
		if(b == 0) {
			return a;
		} else {
			return gcd(b, a%b);
		}
	}
}
