package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2169_로봇조종하기 {
	
	static int[][] map;
	static int[][] dp;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		map = new int[N][M];
		dp = new int[N][M];
		
		for(int r = 0 ; r < N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < M ; ++c) {
				int num = stoi(st.nextToken());
				if(r == 0) dp[r][c] = num;
				map[r][c] = num;
			}
		}
		
		// DP 가장 윗 행 초기화
		for(int c = 1 ; c < M ; ++c) {
			dp[0][c] += dp[0][c - 1]; 
		}
		
		// DP 중간 행 갱신 
		for(int r = 1 ; r < N ; ++r) {
			int[][] temp = new int[2][M];
			
			// 왼쪽에서 오른쪽의 경우 
			// 가장 왼쪽 칸 초기화 (위에서 내려온) 
			temp[0][0] = map[r][0] + dp[r - 1][0];
			for(int c = 1 ; c < M ; ++c) {
				temp[0][c] = Math.max(temp[0][c - 1], dp[r - 1][c]) + map[r][c];
			}
			
			// 오른쪽에서 왼쪽의 경우
			// 가장 오른쪽 칸 초기화 (위에서 내려온)
			temp[1][M - 1] = map[r][M - 1] + dp[r - 1][M - 1];
			for(int c = M - 2 ; c >= 0 ; --c) {
				temp[1][c] = Math.max(temp[1][c + 1], dp[r - 1][c]) + map[r][c];
			}
			
			// 왼쪽에서 오른쪽과 오른쪽에서 왼쪽의 두 경우중 큰 것으로 DP 갱신 
			for(int c = 0 ; c < M ; ++c) {
				dp[r][c] = Math.max(temp[0][c], temp[1][c]);
			}
		}

		System.out.println(dp[N - 1][M - 1]);
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
