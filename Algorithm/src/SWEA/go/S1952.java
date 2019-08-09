package SWEA.go;

import java.util.Arrays;
import java.util.Scanner;

public class S1952 {
	
	static int[] dp = new int[13];
	static int[] plan = new int[13];
	static int[] tiket = new int[4];
	static int T;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		for(int t = 1 ; t <= T ; ++t) {
			for(int i = 0 ; i < 4 ; ++i) tiket[i] = sc.nextInt();
			for(int i = 1 ; i < 13 ; ++i) plan[i] = sc.nextInt();
			
			for(int m = 1 ; m < 13 ; ++m) {
				dp[m] = dp[m - 1] + plan[m] * tiket[0];
				dp[m] = Math.min(dp[m], dp[m - 1] + tiket[1]);
				if(m - 3 >= 0) dp[m] = Math.min(dp[m], dp[m - 3] + tiket[2]);
			}
			
			System.out.println(Arrays.toString(dp));
			
			if(dp[12] > tiket[3]) System.out.println("#" + t + " " + tiket[3]);
			else System.out.println("#" + t + " " + dp[12]);
		}
	}
}
