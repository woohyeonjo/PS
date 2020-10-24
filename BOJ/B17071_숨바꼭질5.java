package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17071_숨바꼭질5 {
	
	static final int MAX = 500000;
	static final int ODD = 1;
	static final int EVEN = 0;
	
	static Queue<Integer> q;
	static boolean[][] visited;
	static int subin, brother;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		q = new LinkedList<>();
		visited = new boolean[2][MAX + 1];
		
		subin = Integer.parseInt(st.nextToken());
		brother = Integer.parseInt(st.nextToken());
		
		if(subin == brother) {
			System.out.println(0);
			return;
		}
		
		q.offer(subin);
		
		System.out.println(bfs());
	}

	private static int bfs() {
		int layer = 0;
		int time = 0;
		int young = brother;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			layer = time % 2 == 0 ? EVEN : ODD;
			
			for(int i = 0 ; i < size ; ++i) {
				int old = q.poll();
				
				if(old == young) return time;
				
				if(old * 2 <= MAX) {
					if(!visited[layer][old * 2]) {
						visited[layer][old * 2] = true;
						q.offer(old * 2);
					}
				}
				if(old + 1 <= MAX) {
					if(!visited[layer][old + 1]) {
						visited[layer][old + 1] = true;
						q.offer(old + 1);
					}
				}
				if(old - 1 >= 0) {
					if(!visited[layer][old - 1]) {
						visited[layer][old - 1] = true;
						q.offer(old - 1);
					}
				}
			}
			
			young += ++time;
			if(young > MAX) return -1;
			if(visited[layer][young]) return time;
		}

		return -1;
	}
}
