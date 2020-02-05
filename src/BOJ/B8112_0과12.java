package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class B8112_0과12 {
	
	static final int MAX = 1000000 + 1;
	static boolean[] visited;
	static int[] parent;
	static HashMap<Integer, Character> map;
	
	static int T, N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = stoi(br.readLine());
		for(int i = 0 ; i < T ; ++i) {
			N = stoi(br.readLine());
			
			if(N == 1) {
				System.out.println(1);
				continue;
			}
			
			visited = new boolean[MAX];
			parent = new int[MAX];
			map = new HashMap<>();
			
			bfs();
			print(0);
			System.out.println();
		}
	}
	
	private static void print(int idx) {
		if(idx == -1) {
			return;
		}
		print(parent[idx]);
		System.out.print(map.get(idx));
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(1);
		visited[1] = true;
		map.put(1, '1');
		parent[1] = -1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			int[] next = {(cur * 10) % N, (cur * 10 + 1) % N};
			
			for(int i = 0 ; i < 2 ; ++i) {
				if(visited[next[i]]) continue;
				
				map.put(next[i], (char)(i + '0'));
				parent[next[i]] = cur;
				
				if(next[i] == 0) return;
				
				visited[next[i]] = true;
				q.offer(next[i]);
			}
			
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}