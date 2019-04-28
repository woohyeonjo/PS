package Algorithm.BOJ.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1261_2 {
	
	static int[][] map;
	static int[][] dp;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");

		M = Integer.parseInt(line[0]);
		N = Integer.parseInt(line[1]);

		map = new int[N][M];
		dp = new int[N][M];

		for(int r = 0 ;r < N ; ++r) {
			line = in.readLine().split("");
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = Integer.parseInt(line[c]);
			}
		}

		for(int r = 1 ; r < N ; ++r) dp[r][0] = dp[r - 1][0] + map[r][0];
		for(int c = 1 ; c < N ; ++c) dp[0][c] = dp[0][c - 1] + map[0][c];

		for(int r = 1 ;r < N ; ++r) {
			for(int c = 1 ; c < M ; ++c) {
				dp[r][c] = Math.min(dp[r][c - 1], dp[r - 1][c]) + map[r][c];
			}
		}

		System.out.println(dp[N - 1][M - 1]);

	}
}
