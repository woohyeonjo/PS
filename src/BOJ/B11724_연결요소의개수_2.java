package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B11724_연결요소의개수_2 {
	
	static ArrayList<ArrayList<Integer>> adj;
	static int[] visited;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		adj = new ArrayList<>();
		for(int i = 0; i < N + 1 ; ++i) {
			adj.add(new ArrayList<>());
		}
		visited = new int[N + 1];
		
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			adj.get(from).add(to);
			adj.get(to).add(from);
		}
		
		int cnt = 0;
		for(int i = 1 ; i < N + 1 ; ++i) {
			if(visited[i] == 0) {
				cnt++;
				visited[i] = cnt;
				dfs(i, cnt);
			}
		}
		System.out.println(cnt);
		
	}
	
	private static void dfs(int start, int cnt) {
		for(Integer i : adj.get(start)) {
			if(visited[i] == 0) {
				visited[i] = cnt;
				dfs(i, cnt);
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
