package BOJ;

import java.util.Scanner;

public class B11653_소인수분해 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		long N = sc.nextLong();
		int i = 2;
		
		while(N != 1) {
			if(N % i == 0) {
				sb.append(i + "\n");
				N /= i;
			} else {
				i++;
			}
		}
		
		System.out.println(sb.toString());
	}
}
