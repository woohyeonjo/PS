package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1600_말이되고픈원숭이 {
	
	static class Monkey {
		int r, c, action, horse;
		
		Monkey(int r, int c, int action, int horse){
			this.r = r;
			this.c = c;
			this.action = action;
			this.horse = horse;
		}
		
	}
	
	static Queue<Monkey> q;
	// 12개의 이동 방법
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1},
						  {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2},
						  {1, 2}, {2, 1}, {2, -1}, {1, -2}
	};
	static boolean[][][] visited;
	static int[][] map;
	static int H, W, K, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		
		K = stoi(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = stoi(st.nextToken());
		H = stoi(st.nextToken());

		ans = Integer.MAX_VALUE;
		map = new int[H][W];
		visited = new boolean[H][W][K + 1];
		
		q = new LinkedList<>();
		
		for(int r = 0 ; r < H ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < W ; ++c) {
				map[r][c] = stoi(st.nextToken());
			}
		}
		
		visited[0][0][0] = true;
		q.offer(new Monkey(0, 0, 0, 0));
		
		go();
		
		if(ans == Integer.MAX_VALUE) ans = -1;
		
		System.out.println(ans);
	}
	
	private static void go() {
		while(!q.isEmpty()) {
			Monkey now = q.poll();
			
			if(now.r == H - 1 && now.c == W - 1) {
				ans = ans > now.action ? now.action : ans;
				return;
			}
			
			for(int d = 0 ; d < 12 ; ++d) {
				int nr = now.r + dir[d][0]; 
				int nc = now.c + dir[d][1]; 

				if(nr >= H || nr < 0 || nc >= W || nc < 0 || map[nr][nc] == 1) continue;
				
				// 원숭이 걸음 
				if(d <= 3) {
					if(!visited[nr][nc][now.horse]) {
						visited[nr][nc][now.horse] = true;
						q.offer(new Monkey(nr, nc, now.action + 1, now.horse));
					}
				// 말 걸음 
				} else {
					if(now.horse < K && !visited[nr][nc][now.horse + 1]) {
						visited[nr][nc][now.horse + 1] = true;
						q.offer(new Monkey(nr, nc, now.action + 1, now.horse + 1));
					}
				}
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
