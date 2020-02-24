package BOJ;

import java.util.Scanner;

public class B1629_곱셈 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long A = sc.nextLong();
		long B = sc.nextLong();
		long C = sc.nextLong();
		
		System.out.println(power(A % C, B, C));
	}

	private static long power(long a, long b, long c) {
		// b가 1일 때
		if(b == 1) return a;
		
		long temp = power(a, b / 2, c) % c;
		
		// b가 짝수일 때 
		if(b % 2 == 0) {
			return (temp * temp) % c;
		// b가 홀수일 때 
		} else {
			return (((temp * temp) % c) * a) % c;
		}
	}
}
