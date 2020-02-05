package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1182_부분수열의합 {

	static int[] numbers;
	static int N, S, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		S = stoi(st.nextToken());

		ans = 0;
		numbers = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; ++i) {
			numbers[i] = stoi(st.nextToken());
		}
		
		dfs(0, 0);
		
		System.out.println(S == 0 ? --ans : ans);
	}
	
	private static void dfs(int depth, int sum) {
		if(depth == N) {
			if(sum == S) ans++;
			return;
		}
		
		dfs(depth + 1, sum + numbers[depth]);
		dfs(depth + 1, sum);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
