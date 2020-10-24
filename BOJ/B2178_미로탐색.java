package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2178_미로탐색 {
	static class Node {
		int r, c;
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static Queue<Node> q;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; 
	static int[][] map;
	static boolean[][] visited;
	static int N, M;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		q = new LinkedList<>();
		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		
		for(int i = 1 ; i < N + 1 ; ++i) {
			char[] line = br.readLine().toCharArray();
			for(int j = 1 ; j < M + 1 ; ++j) {
				map[i][j] = line[j - 1] - '0';
			}
		}
		
		visited[1][1] = true;
		q.offer(new Node(1, 1));
		
		System.out.println(bfs());
	}

	private static int bfs() {
		int cnt = 0;
		
		while(!q.isEmpty()) {
			
			int size = q.size();
			cnt++;
			
			for(int i = 0 ; i < size ; ++i) {
				Node cur = q.poll();
				
				for(int j = 0 ; j < 4 ; ++j) {
					int nr = cur.r + dir[j][0];
					int nc = cur.c + dir[j][1];
					if(nr > 0 && nr <= N && nc > 0 && nc <= M &&
					   !visited[nr][nc] && map[nr][nc] == 1) {
						if(nr == N && nc == M) return cnt + 1;
						visited[nr][nc] = true;
						q.offer(new Node(nr, nc));
					}
				}
				
			}
		}
		return cnt;
	}
}
