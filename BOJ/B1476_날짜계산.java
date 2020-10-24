package BOJ;

import java.util.Scanner;

public class B1476_날짜계산 {
	
	static final int E = 15;
	static final int S = 28;
	static final int M = 19;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int e = sc.nextInt();
		int s = sc.nextInt();
		int m = sc.nextInt();
		
		int[] year = new int[3];
		int cnt = 0;
		while(true) {
			if(year[0] == e && year[1] == s && year[2] == m) break;
			year[0] = year[0] % E + 1;
			year[1] = year[1] % S + 1;
			year[2] = year[2] % M + 1;
			cnt++;
		}
		
		System.out.println(cnt);
	}
}
