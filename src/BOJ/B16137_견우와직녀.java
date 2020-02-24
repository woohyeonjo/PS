package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16137_견우와직녀 {

	static class Node {
		int r, c, t;
		
		Node(int r, int c, int t){
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
	
	static Queue<Node> q;
	static boolean[][] visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] map;
	static int maxSize, cycle, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		maxSize = stoi(st.nextToken());
		cycle = stoi(st.nextToken());
		
		ans = Integer.MAX_VALUE;
		q = new LinkedList<>();
		map = new int[maxSize][maxSize];

		for(int r = 0 ; r < maxSize ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < maxSize ; ++c) {
				map[r][c] = stoi(st.nextToken());
			}
		}
		
		for(int r = 0 ; r < maxSize ; ++r) {
			for(int c = 0 ; c < maxSize ; ++c) {
				// 절벽이며 
				if(map[r][c] == 0) {
					
					boolean[] isCliff = new boolean[4];
					
					for(int d = 0 ; d < 4 ; ++d) {
						int nr = r + dir[d][0];
						int nc = c + dir[d][1];
						
						if(nr >= maxSize || nr < 0 || nc >= maxSize || nc < 0) continue;

						// 해당 방향에 절벽이 있는지 
						if(map[nr][nc] == 0) {
							isCliff[d] = true;
						}
					}
					if((isCliff[0] && isCliff[3]) || (isCliff[1] && isCliff[2]) ||
					   (isCliff[0] && isCliff[2]) || (isCliff[1] && isCliff[3])) continue;
					
					map[r][c] = cycle;
					visited = new boolean[maxSize][maxSize];
					bfs();
					map[r][c] = 0;
					
				}
			}
		}
		System.out.println(ans);
	}
	
	private static void bfs() {
		visited[0][0] = true;
		q.offer(new Node(0, 0, 0));
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			if(now.r == maxSize - 1 && now.c == maxSize - 1) {
				ans = ans > now.t ? now.t : ans;
				return;
			}
			
			for(int i = 0 ; i < 4 ; ++i) {
				int nr = now.r + dir[i][0];
				int nc = now.c + dir[i][1];
				
				if(nr >= maxSize || nr < 0 || nc >= maxSize || nc < 0) continue;
				if(map[nr][nc] == 0 || visited[nr][nc]) continue;
				
				// 땅
				if(map[nr][nc] == 1) {
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc, now.t + 1));
				}
				
				// 다리
				if(map[nr][nc] >= 2 && map[now.r][now.c] == 1) {
					if((now.t + 1) % map[nr][nc] == 0) {
						visited[nr][nc] = true;
						q.offer(new Node(nr, nc, now.t + 1));
					} else {
						q.offer(new Node(now.r, now.c, now.t + 1));
					}
				}
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
