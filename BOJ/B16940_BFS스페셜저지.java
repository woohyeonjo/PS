package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16940_BFS스페셜저지 {

	static ArrayList<ArrayList<Integer>> adj;
	static Queue<Integer> q;
	static boolean[] visited;
	static int N;
	static int[] answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		q = new LinkedList<>();
		visited = new boolean[N + 1];
		answer = new int[N];
		
		adj = new ArrayList<>();
		for(int i = 0 ; i <= N ; ++i) adj.add(new ArrayList<>());
		
		// 인접리스트 초기화 
		for(int i = 0 ; i < N - 1 ; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
		
			adj.get(from).add(to);
			adj.get(to).add(from);
		}
		
		// 입력된 답안 
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; ++i) answer[i] = Integer.parseInt(st.nextToken());
		
		if(answer[0] != 1) {
			System.out.println(0);
			return;
		}
		
		q.offer(1);
		visited[1] = true;
		
		if(bfs()) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
		
	}

	private static boolean bfs() {
		int idx = 1;
		HashSet<Integer> set = new HashSet<>();
		
		while(!q.isEmpty()) {
			set.clear();
			
			int cur = q.poll();
			
			for(int next : adj.get(cur)) {
				if(visited[next]) continue;
				
				set.add(next);
				visited[next] = true;
			}
			
			int size = set.size();
			
			for(int i = idx ; i < idx + size ; ++i) {
				if(set.contains(answer[i])) q.offer(answer[i]);
				else return false;
			}
			
			idx += size;
		}
		
		return true;
	}
}
