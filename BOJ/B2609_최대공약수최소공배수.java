package BOJ;

import java.util.Scanner;

public class B2609_최대공약수최소공배수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int GCD = 0;
		int LCM = 0;
		
		GCD = gcd(a, b);
		LCM = a * b / GCD;
		
		System.out.println(GCD);
		System.out.println(LCM);
	}

	private static int gcd(int a, int b) {
		if(b == 0) {
			return a;
		} else {
			return gcd(b, a%b);
		}
	}
}
