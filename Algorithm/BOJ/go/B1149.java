package Algorithm.BOJ.go;

import java.util.Scanner;

public class B1149 {
	
	static int[][] dp = new int[1001][3];
	static int[][] rgb = new int[1001][3];
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		for(int i = 0 ; i < N ; ++i) {
			for(int j = 0 ; j < 3 ; ++j) {
				rgb[i][j] = sc.nextInt();
			}
		}
		
		dp[0][0] = rgb[0][0];
		dp[0][1] = rgb[0][1];
		dp[0][2] = rgb[0][2];
		
		for(int i = 1 ; i < N ; ++i) {
			dp[i][0] = Math.min(rgb[i][0] + dp[i - 1][1], rgb[i][0] + dp[i - 1][2]);
			dp[i][1] = Math.min(rgb[i][1] + dp[i - 1][0], rgb[i][1] + dp[i - 1][2]);
			dp[i][2] = Math.min(rgb[i][2] + dp[i - 1][0], rgb[i][2] + dp[i - 1][1]);
		}
		
		if(dp[N - 1][0] > dp[N - 1][1]) {
			System.out.println(Math.min(dp[N - 1][1], dp[N - 1][2]));
		} else System.out.println(Math.min(dp[N - 1][0], dp[N - 1][2]));
	}
}
