package BOJ;

import java.util.Scanner;

public class B14225_부분수열의합 {
	static boolean[] visited;
	static boolean[] selected;
	static int[] S;
	static int N;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		S = new int[N];
		selected = new boolean[N];
		visited = new boolean[20 * 100000 + 1];
		
		for(int i = 0 ; i < N ; ++i) {
			S[i] = sc.nextInt();
		}
		
		for(int i = 1 ; i <= N ; ++i) {
			dfs(i, 0, 0, 0);
		}
		
		for(int i = 1 ; i < visited.length ; ++i) {
			if(!visited[i]) {
				System.out.println(i);
				return;
			}
		}
	}
	private static void dfs(int max, int cnt, int idx, int sum) {
		if(max == cnt) {
			visited[sum] = true;
			return;
		}
		
		for(int i = idx ; i < N ; ++i) {
			if(selected[i]) continue;
			selected[i] = true;
			dfs(max, cnt + 1, i + 1, sum + S[i]);
			selected[i] = false;
		}
	}
}
