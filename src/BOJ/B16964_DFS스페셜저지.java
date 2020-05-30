package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B16964_DFS스페셜저지 {
	
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	static int[] answer;
	static boolean flag;
	static int N, idx;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		adj = new ArrayList<>();
		for(int i = 0 ; i <= N ; ++i) adj.add(new ArrayList<>());
		
		visited = new boolean[N + 1];
		answer = new int[N];
		flag = true;
		idx = 1;
		
		for(int i = 0 ; i < N - 1 ; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adj.get(from).add(to);
			adj.get(to).add(from);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; ++i) answer[i] = Integer.parseInt(st.nextToken());
		
		if(answer[0] != 1) {
			System.out.println(0);
			return;
		}
		
		dfs(1);
		
		if(flag) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
		
	}

	private static void dfs(int x) {
		if(visited[x]) return;
		
		// x정점 방문
		visited[x] = true;
		
		HashSet<Integer> set = new HashSet<>();
		for(int next : adj.get(x)) {
			if(visited[next]) continue;
			set.add(next);
		}
		
		if(set.size() == 0) return;
		
		if(set.contains(answer[idx])) {
			dfs(answer[idx++]);
		} else {
			flag = false;
		}
	}
}
