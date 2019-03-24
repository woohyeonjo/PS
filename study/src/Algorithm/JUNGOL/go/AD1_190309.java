package Algorithm.JUNGOL.go;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class AD1_190309 {
	
	static Cell[][] map;
	static int[][] dir = {{0, 1}, {1, 0}, {1, 1}};
	static Queue<Cell> q;
	static int T, N, cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		for(int t = 1; t <= T; ++t) {
			N = sc.nextInt();
			
			map = new Cell[N][N];
			q = new LinkedList<Cell>();
			cnt = 0;
			
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < N ; ++c) {
					map[r][c] = new Cell(r, c, sc.nextInt());
				}
			}
			
			Cell temp;
			
			if(map[0][1].type == 0) {
				temp = new Cell(map[0][1].r, map[0][1].c, map[0][1].type);
				temp.busHead = 0;
				q.offer(temp);
			}
			if(map[1][0].type == 0) {
				temp = new Cell(map[1][0].r, map[1][0].c, map[1][0].type);
				temp.busHead = 1;
				q.offer(temp);
			}
			bfs();
			
			System.out.println("#" + t + " " + cnt);
		}
	}
	
	private static void bfs() {
		
		int nr, nc;
		Cell temp;
		
		while(!q.isEmpty()) {
			Cell cell = q.poll();
			if(cell.r == N - 1 && cell.c == N - 1 && cell.busHead != 2) cnt++;
			
			if(cell.busHead != 1) {
				nr = cell.r + dir[0][0];
				nc = cell.c + dir[0][1];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc].type == 0) {
					temp = new Cell(map[nr][nc].r, map[nr][nc].c, 0);
					temp.busHead = 0;
					
					q.offer(temp);
				}
			}
			
			if(cell.busHead != 0) {
				nr = cell.r + dir[1][0];
				nc = cell.c + dir[1][1];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc].type == 0) {
					temp = new Cell(map[nr][nc].r, map[nr][nc].c, 0);
					temp.busHead = 1;
					
					q.offer(temp);
				}
			}
			
			nr = cell.r + dir[2][0];
			nc = cell.c + dir[2][1];
			if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc].type == 0) {
				if (map[nr - 1][nc].type == 0 && map[nr][nc - 1].type == 0) {
					temp = new Cell(map[nr][nc].r, map[nr][nc].c, 0);
					temp.busHead = 2;
					
					q.offer(temp);
				}
			}
			
		}
	}

	static class Cell{
		int r, c, type, busHead;

		public Cell(int x, int y, int type) {
			super();
			this.r = x;
			this.c = y;
			this.type = type;
		}
	}
}
