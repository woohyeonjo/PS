package BOJ;

import java.util.Scanner;

public class B2089_음의2진수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		long n = sc.nextLong();

		// 0일때는 바로 0 출력 
		if(n == 0) {
			System.out.println(0);
			return;
		}
		
		// 10진수 -> 2진수와 같이 10진수를 계속해서 2로 나누고 나머지를 거꾸로 출력한다.
		// 하지만, -2진수에서는 나눈 몫을 올림한다. 
		while(n != 0) {
			sb.insert(0, Math.abs(n%-2));
			n = (long) Math.ceil((double)n / -2);
		}
		
		System.out.println(sb.toString());
	}
}
