package BOJ;

import java.util.Scanner;

public class B1740_거듭제곱 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long N = sc.nextLong();
		long answer = 0;
        int count = 0;
        
        while (N > 0) {
            if ((N & 1) == 1) {
                answer += power(3, count);
            }
            count++;
            N = (N >> 1);
        }
        
        System.out.println(answer);
	}
	
	private static long power(long a, long b) {
		if(b == 0) return 1;
		if(b == 1) return a;
		
		long temp = power(a, b / 2);
		
		// b가 짝수일 때 
		if(b % 2 == 0) {
			return (temp * temp);
		// b가 홀수일 때 
		} else {
			return ((temp * temp) * a);
		}
	}
}
