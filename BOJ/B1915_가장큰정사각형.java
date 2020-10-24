package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1915_가장큰정사각형 {

	static int map[][];
	static int dp[][];
	static int N, M, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		ans = 0;
		map = new int[N][M];
		dp = new int[N][M];
		
		for(int i = 0 ; i < N ; ++i) {
			char[] line = br.readLine().toCharArray();
			for(int j = 0 ; j < M ; ++j) {
				map[i][j] = line[j] - '0';
				dp[i][j] = line[j] - '0';
				if(dp[i][j] == 1) ans = 1;
			}
		}
		
		for(int i = 1 ; i < N ; ++i) {
			for(int j = 1 ; j < M ; ++j) {
				if(map[i - 1][j] == 1 && map[i - 1][j - 1] == 1 && map[i][j - 1] == 1) {
					dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1; 
					ans = dp[i][j] > ans ? dp[i][j] : ans;
				}
			}
		}
		
		System.out.println(ans * ans);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
