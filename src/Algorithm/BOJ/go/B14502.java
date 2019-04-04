package Algorithm.BOJ.go;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B14502 {
	static Cell[][] map;
	static ArrayList<Cell> virus;
	static Queue<Cell> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	static int N, M, ans;
	static class Cell {
		int r, c, type;

		public Cell(int r, int c, int type) {
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
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new Cell[N][M];
		visited = new boolean[N][M];
		q = new LinkedList<Cell>();
		virus = new ArrayList<Cell>();
		ans = 0;

		int temp;
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = new Cell(r, c, sc.nextInt());
				if(map[r][c].type == 2) virus.add(map[r][c]);
			}
		}
		
		dfs(0, 0, 0);
		
		System.out.println(ans);
	}

	private static void dfs(int R, int C, int cnt) {
		
		if(cnt == 3) {
			Cell[][] backup = map.clone();
			for(Cell cell : virus) {
				q.offer(cell);
				visited[cell.r][cell.c] = true;
			}
			bfs();
			ans = Math.max(ans, count());
			map = backup;
			return;
		}
		
		for(int r = R ; r < N ; ++r) {
			for(int c = C; c < M ; ++c) {
				if(map[r][c].type == 0) {
					map[r][c].type = 1;
					dfs(r, c, cnt + 1);
					map[r][c].type = 0;
				}
			}
		}
	}

	private static void bfs() {
		
		int nr, nc;
		while(!q.isEmpty()) {
			Cell cell = q.poll();
			for(int i = 0 ; i < 4 ; ++i) {
				nr = cell.r + dir[i][0];
				nc = cell.c + dir[i][1];
				if(nr >= N || nr < 0 || nc >= M || nc < 0 || visited[nr][nc]) continue;
				if(map[nr][nc].type == 0) {
					map[nr][nc].type = 2;
					q.offer(map[nr][nc]);
					visited[nr][nc] = true;
				}
			}
		}
		
	}
	
	private static int count() {
		int cnt = 0;
		for(int r = 0; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				if(map[r][c].type == 0) cnt++;
			}
		}
		return cnt;
	}
	
}
