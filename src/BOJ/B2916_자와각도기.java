package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2916_자와각도기 {
	
	static int N, K;
	static int[] angle;
	static boolean[][] dp;
	static boolean[] ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		K = stoi(st.nextToken());
	
		ans = new boolean[360];
		dp = new boolean[10][360];
		angle = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; ++i) {
			angle[i] = stoi(st.nextToken());
		}
		
		Arrays.sort(angle);
		solve(0, 0);
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < K ; ++i) {
			int problem = stoi(st.nextToken());
			
			if(ans[problem]) System.out.println("YES");
			else System.out.println("NO");
		}
		
	}
	
	private static void solve(int i, int sum) {
		if(i >= N || dp[i][sum]) return;
		
		dp[i][sum] = true;
		ans[sum] = true;
		
		solve(i, (360 + sum - angle[i]) % 360);
		solve(i + 1, sum);
		solve(i, (360 + sum + angle[i]) % 360);
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
