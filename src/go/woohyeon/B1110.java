package go.woohyeon;

import java.util.Scanner;

public class B1110 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tens, ones, sum, cnt = 0;
		int N = sc.nextInt();
		if(N < 10) N = N * 10;
		int M = N;
		do {
			tens = M / 10;
			ones = M % 10;
			sum = tens + ones;
			M = ones * 10 + sum % 10;
			cnt++;
		} while(N != M);
		System.out.println(cnt);
	}
}
