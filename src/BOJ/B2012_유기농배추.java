package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2012_유기농배추 {
	
	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static Queue<Node> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	static int[][] map;
	static int T, N, M, K, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = stoi(br.readLine());
		
		for(int i = 0 ; i < T ; ++i) {
			st = new StringTokenizer(br.readLine());
			
			M = stoi(st.nextToken());
			N = stoi(st.nextToken());
			K = stoi(st.nextToken());

			ans = 0;
			q = new LinkedList<>();
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for(int j = 0 ; j < K ; ++j) {
				st = new StringTokenizer(br.readLine());
				int c = stoi(st.nextToken());
				int r = stoi(st.nextToken());
				map[r][c] = 1;
			}
			
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < M ; ++c) {
					if(map[r][c] == 1 && !visited[r][c]) {
						ans++;
						visited[r][c] = true;
						q.offer(new Node(r, c));
						bfs();
					}
				}
			}
			System.out.println(ans);
		}
		
	}
	
	private static void bfs() {
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(int d = 0 ; d < 4 ; ++d) {
				int nr = now.r + dir[d][0];
				int nc = now.c + dir[d][1];
				if(nr >= N || nr < 0 || nc >= M || nc < 0 || visited[nr][nc]) continue;
				if(map[nr][nc] == 1) {
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc));
				}
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
