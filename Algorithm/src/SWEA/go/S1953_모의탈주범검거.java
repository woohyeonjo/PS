package SWEA.go;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class S1953_모의탈주범검거 {
	
	static class Man {
		int r, c;
		int dir, time;
		
		public Man(int r, int c, int dir, int time) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.time = time;
		}
		
		@Override
		public String toString() {
			return r + ", " + c + " / " + dir + ", " + time;
		}
	}
	
	static int[][] map;
	static boolean[][] visited;
	static Queue<Man> q;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int T, N, M, R, C, L;
	static int ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			
			map = new int[N][M];
			visited = new boolean[N][M];
			q = new LinkedList<Man>();
			ans = 0;
			
			for(int r = 0 ; r < N ; ++r ) {
				for(int c = 0 ; c < M ; ++c) {
					map[r][c] = sc.nextInt();
				}
			}
			
			visited[R][C] = true;
			for(int i = 0 ; i < 4 ; ++i) {
				q.offer(new Man(R, C, i, 1));
			}
			
			bfs();
			
			for(int r = 0 ; r < N ; ++r ) {
				for(int c = 0 ; c < M ; ++c) {
					if(visited[r][c]) ans++;
				}
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void bfs() {
		int nr, nc;
		Man man;
		
		while(!q.isEmpty()) {
			man = q.poll();
			
			if(man.time == L) continue;
			
			nr = man.r + dir[man.dir][0];
			nc = man.c + dir[man.dir][1];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] == 0) continue;
			int pipe = map[nr][nc];
			switch(man.dir) {
				case 0:
					if(pipe == 3 || pipe == 4 || pipe == 7) continue;
					else if(pipe == 1) for(int i = 0 ; i < 4 ; ++i) q.offer(new Man(nr, nc, i, man.time + 1));
					else if(pipe == 2) q.offer(new Man(nr, nc, man.dir, man.time + 1));
					else if(pipe == 5) q.offer(new Man(nr, nc, 1, man.time + 1));
					else if(pipe == 6) q.offer(new Man(nr, nc, 3, man.time + 1));
					break;
				case 1:
					if(pipe == 2 || pipe == 4 || pipe == 5) continue;
					else if(pipe == 1) for(int i = 0 ; i < 4 ; ++i) q.offer(new Man(nr, nc, i, man.time + 1));
					else if(pipe == 3) q.offer(new Man(nr, nc, man.dir, man.time + 1));
					else if(pipe == 6) q.offer(new Man(nr, nc, 2, man.time + 1));
					else if(pipe == 7) q.offer(new Man(nr, nc, 0, man.time + 1));
					break;
				case 2:
					if(pipe == 3 || pipe == 5 || pipe == 6) continue;
					else if(pipe == 1) for(int i = 0 ; i < 4 ; ++i) q.offer(new Man(nr, nc, i, man.time + 1));
					else if(pipe == 2) q.offer(new Man(nr, nc, man.dir, man.time + 1));
					else if(pipe == 4) q.offer(new Man(nr, nc, 1, man.time + 1));
					else if(pipe == 7) q.offer(new Man(nr, nc, 3, man.time + 1));
					break;
				case 3:
					if(pipe == 2 || pipe == 6 || pipe == 7) continue;
					else if(pipe == 1) for(int i = 0 ; i < 4 ; ++i) q.offer(new Man(nr, nc, i, man.time + 1));
					else if(pipe == 3) q.offer(new Man(nr, nc, man.dir, man.time + 1));
					else if(pipe == 4) q.offer(new Man(nr, nc, 0, man.time + 1));
					else if(pipe == 5) q.offer(new Man(nr, nc, 2, man.time + 1));
					break;
			}
			visited[nr][nc] = true;
		}
	}
}
