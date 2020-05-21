package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16928_뱀과사다리게임 {
	
	static HashMap<Integer, Integer> map;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		map = new HashMap<>();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i < N ; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			map.put(from, to);
		}
		
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			map.put(from, to);
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		int cnt = 0;
		boolean[] visited = new boolean[101];
		Queue<Integer> q = new LinkedList<>();
		
		visited[1] = true;
		q.offer(1);
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i = 0 ; i < size ; ++i) {
				
				int cur = q.poll();
				
				if(cur == 100) return cnt;
				
				for(int add = 1 ; add <= 6 ; ++add) {
					int next = cur + add;
				
					if(next > 100) break;
					if(visited[next]) continue;
					
					if(map.containsKey(next)) {
						next = map.get(next);
						if(visited[next]) continue;
					}
					
					q.offer(next);
					visited[next] = true;
				}
			}
			cnt++;
		}
		
		return cnt;
	}
}
