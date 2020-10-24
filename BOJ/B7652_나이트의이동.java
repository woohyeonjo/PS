package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7652_나이트의이동 {
	
	static class Node{
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static Queue<Node> q;
	static int[][] dir = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2},
						  {1, 2}, {2, 1}, {2, -1}, {1, -2}};
	static boolean[][] visited;
	static int T, I;
	static Node start, target;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = stoi(br.readLine());
		
		for(int t = 0 ; t < T ; ++t) {
			I = stoi(br.readLine());
			
			visited = new boolean[I][I];
			q = new LinkedList<>();
			
			st = new StringTokenizer(br.readLine());
			start = new Node(stoi(st.nextToken()), stoi(st.nextToken()));
			q.offer(start);
			
			st = new StringTokenizer(br.readLine());
			target = new Node(stoi(st.nextToken()), stoi(st.nextToken()));
			
			if(start.r == target.r && start.c == target.c) {
				System.out.println(0);
			} else System.out.println(bfs());
		}
	}
	
	private static int bfs() {
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			cnt++;
			
			for(int i = 0 ; i < size ; ++i) {
				
				Node cur = q.poll();
				
				for(int j = 0 ; j < 8 ; ++j) {
					int nr = cur.r + dir[j][0];
					int nc = cur.c + dir[j][1];
					
					if(nr >= I || nr < 0 || nc >= I || nc < 0 || visited[nr][nc]) continue;
					if(nr == target.r && nc == target.c) return cnt;
					
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc));
				}
			}
		}
		return cnt;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}