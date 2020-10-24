package BOJ;

import java.util.Scanner;

public class B2745_진법변환 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String N = sc.next();
		int B = sc.nextInt();

		long ans = 0;
		int power = N.length() - 1;
		
		for(int i = 0 ; i < N.length() ; ++i) {
			char num = N.charAt(i);
			if(num >= 'A' && num <= 'Z') {
				ans += (num - 55) * Math.pow(B, power--);
			} else {
				ans += (num - '0') * Math.pow(B, power--);
			}
		}
		System.out.println(ans);
	}
}
