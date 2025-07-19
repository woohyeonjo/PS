package JUNGOL;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class J1733 {
	
	static Stone[][] map = new Stone[20][20];
	static boolean[][] visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
	static Queue<Stone> q = new LinkedList<Stone>();
	static int left, top;
	static class Stone {
		int r, c, type;

		public Stone(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}
		
		@Override
		public String toString() {
			return this.type + "";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for(int r = 1 ; r <= 19 ; ++r) {
			String[] line = in.readLine().split(" ");
			for(int c = 1 ; c <= 19 ; ++c) {
				map[r][c] = new Stone(r, c, Integer.parseInt(line[c - 1]));
			}
		}
		
		OUTER: for(int r = 1 ; r <= 19 ; ++r) {
			for(int c = 1 ; c <= 19 ; ++c) {
				if(map[r][c].type == 0) continue;
				if(check(r, c)) break OUTER;
			}
		}
		if(left < 20 && top < 20) {
			if(map[top][left] == null) System.out.println(0);
			else {
				System.out.println(map[top][left].type);
				System.out.println(top + " " + left);
			}
		}
	}

	private static boolean check(int r, int c) {
		visited = new boolean[20][20];
		visited[r][c] = true;
		q.offer(map[r][c]);
		if(vCheck() == 5) return true;
		q.offer(map[r][c]);
		if(hCheck() == 5) return true;
		q.offer(map[r][c]);
		if(rdCheck() == 5) return true;
		q.offer(map[r][c]);
		if(ldCheck() == 5) return true;
		return false;
	}

	// 상하
	private static int vCheck() {
		int nr, nc, cnt = 1;
		int l = 20, t = 20;
		while(!q.isEmpty()) {
			Stone stone = q.poll();
			
			if(stone.c < l) {
				l = stone.c;
				t = stone.r;
			}
			
			nr = 0; nc = 0;
			for(int i = 0 ; i < 2 ; ++i) {
				nr = stone.r + dir[i][0];
				nc = stone.c + dir[i][1];
				
				if(nr > 19 || nr < 1 || nc > 19 || nc < 1 || visited[nr][nc] || map[nr][nc].type == 0) continue;
				if(map[nr][nc].type == stone.type) {
					visited[nr][nc] = true;
					q.offer(map[nr][nc]);
					cnt++;
				}
			}
		}
		if(cnt == 5) {
			left = l;
			top = t;
		}
		return cnt;
	}
	
	// 좌우
	private static int hCheck() {
		int nr, nc, cnt = 1;
		int l = 20, t = 20;
		while(!q.isEmpty()) {
			Stone stone = q.poll();
			
			if(stone.c < l) {
				l = stone.c;
				t = stone.r;
			}
			
			nr = 0; nc = 0;
			for(int i = 2 ; i < 4 ; ++i) {
				nr = stone.r + dir[i][0];
				nc = stone.c + dir[i][1];
				
				if(nr > 19 || nr < 1 || nc > 19 || nc < 1 || visited[nr][nc] || map[nr][nc].type == 0) continue;
				if(map[nr][nc].type == stone.type) {
					visited[nr][nc] = true;
					q.offer(map[nr][nc]);
					cnt++;
				}
			}
		}
		if(cnt == 5) {
			left = l;
			top = t;
		}
		return cnt;
	}
	
	// 우상향 대각선
	private static int rdCheck() {
		int nr, nc, cnt = 1;
		int l = 20, t = 20;
		while(!q.isEmpty()) {
			Stone stone = q.poll();
			
			if(stone.c < l) {
				l = stone.c;
				t = stone.r;
			}
			
			nr = 0; nc = 0;
			for(int i = 6 ; i < 8 ; ++i) {
				nr = stone.r + dir[i][0];
				nc = stone.c + dir[i][1];
				
				if(nr > 19 || nr < 1 || nc > 19 || nc < 1 || visited[nr][nc] || map[nr][nc].type == 0) continue;
				if(map[nr][nc].type == stone.type) {
					visited[nr][nc] = true;
					q.offer(map[nr][nc]);
					cnt++;
				}
			}
		}
		if(cnt == 5) {
			left = l;
			top = t;
		}
		return cnt;
	}
	
	// 좌상향 대각선
	private static int ldCheck() {
		int nr, nc, cnt = 1;
		int l = 20, t = 20;
		while(!q.isEmpty()) {
			Stone stone = q.poll();
			
			if(stone.c < l) {
				l = stone.c;
				t = stone.r;
			}
			
			nr = 0; nc = 0;
			for(int i = 4 ; i < 6 ; ++i) {
				nr = stone.r + dir[i][0];
				nc = stone.c + dir[i][1];
				
				if(nr > 19 || nr < 1 || nc > 19 || nc < 1 || visited[nr][nc] || map[nr][nc].type == 0) continue;
				if(map[nr][nc].type == stone.type) {
					visited[nr][nc] = true;
					q.offer(map[nr][nc]);
					cnt++;
				}
			}
		}
		if(cnt == 5) {
			left = l;
			top = t;
		}
		return cnt;
	}
}
