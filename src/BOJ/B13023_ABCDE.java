package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B13023_ABCDE {

	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	static int N, M, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		ans = 0;
		adj = new ArrayList<>();
		for(int i = 0 ; i < N ; ++i) {
			adj.add(new ArrayList<>());
		}
		
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			int r = stoi(st.nextToken());
			int c = stoi(st.nextToken());
			
			adj.get(r).add(c);
			adj.get(c).add(r);
		}
		
		for(int i = 0 ; i < N ; ++i) {
			visited = new boolean[N];
			visited[i] = true;
			if(dfs(i, 0)) {
				break;
			};
		}
		
		System.out.println(ans);
	}
	
	private static boolean dfs(int start, int depth) {
		
		if(depth == 4) {
			ans = 1;
			return true;
		}
		
		for(Integer i : adj.get(start)) {
			if(!visited[i]) {
				visited[i] = true;
//				System.out.println(start + " 의 친구 " + i);
				if(dfs(i, depth + 1)) {
					return true;
				}
				visited[i] = false;
			}
		}
		return false;
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
