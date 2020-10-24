package BOJ;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B16948_데스나이트 {
	static class Death {
		int r, c, cnt;
		
		public Death(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	static Queue<Death> q;
	static boolean[][] visited;
	static int[][] map;
	// 6방
	static int[][] dir = {{-2, -1}, {-2, 1}, {0, -2}, {0, 2}, {2, -1}, {2, 1}};
	static int N, R, C, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		map = new int[N][N];
		visited = new boolean[N][N];
		q = new LinkedList<>();
		ans = -1;
		
		q.offer(new Death(sc.nextInt(), sc.nextInt(), 0));
		R = sc.nextInt();
		C = sc.nextInt();
		
		bfs();
		System.out.println(ans);
	}

	private static void bfs() {
		int nr, nc;
		
		while(!q.isEmpty()) {
			Death d = q.poll();
			
			if(d.r == R && d.c == C) {
				ans = d.cnt;
				return;
			}
			
			for(int i = 0 ; i < 6 ; ++i) {
				nr = d.r + dir[i][0];
				nc = d.c + dir[i][1];
				
				if(nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				q.offer(new Death(nr, nc, d.cnt + 1));
			}
		}
	}
}
