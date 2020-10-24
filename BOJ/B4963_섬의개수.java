package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B4963_섬의개수 {
	static class Node {
		int r, c;
		
		Node (int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static Queue<Node> q;
	static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1},
						  {0, -1},           {0, 1},
						  {1, -1}, {1, 0}, {1, 1}};
	static int[][] map;
	static boolean[][] visited;
	static int W, H, label;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = stoi(st.nextToken());
		H = stoi(st.nextToken());
		
		while(W != 0 && H != 0) {
			label = 1;
			map = new int[H][W];
			visited = new boolean[H][W];
			q = new LinkedList<>();
			
			for(int i = 0 ; i < H ; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < W ; ++j) {
					map[i][j] = stoi(st.nextToken());
				}
			}
			
			for(int i = 0 ; i < H ; ++i) {
				for(int j = 0 ; j < W ; ++j) {
					if(visited[i][j] || map[i][j] == 0) continue;
					label++;
					q.offer(new Node(i, j));
					bfs();
				}
			}
			System.out.println(label - 1);
			
			st = new StringTokenizer(br.readLine());
			W = stoi(st.nextToken());
			H = stoi(st.nextToken());
		}
	}
	
	private static void bfs() {
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			map[cur.r][cur.c] = label;
			
			for(int i = 0 ; i < 8 ; ++i) {
				int nr = cur.r + dir[i][0];
				int nc = cur.c + dir[i][1];
				if(nr < H && nr >= 0 && nc < W && nc >= 0 &&
				   !visited[nr][nc] && map[nr][nc] == 1) {
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
