package BOJ;

import java.util.Scanner;

public class B11005_진법변환2 {
	
	// A = 65
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		long N = sc.nextLong();
		int B = sc.nextInt();
		
		if(N == 0) {
			System.out.println(0);
			return;
		}
		
		while(N != 0) {
			long remainder = N % B;
			
			if(remainder > 9) {
				sb.insert(0, (char)(remainder + 55));
			} else {
				sb.insert(0, remainder);
			}
			
			N /= B;
		}
		
		System.out.println(sb.toString());
	}
}
