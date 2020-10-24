package BOJ;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2636 {

	static Cell[][] map;
	static boolean[][] visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<Cell> q;
	static int N, M, hour, cheeze, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		q = new LinkedList<Cell>();
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new Cell[N][M];
		hour = 0;
		cheeze = 1;
		
		for(int row = 0 ; row < N ; ++row) {
			for(int col = 0 ; col < M ; ++col) {
				map[row][col] = new Cell(row, col, sc.nextInt());
			}
		}
		
		while(cheeze > 0) {
			if(cheeze > 0) ans = cheeze;
			q.offer(map[0][0]);
			bfs();
		}
		System.out.println(hour);
		System.out.println(ans);
	}

	private static void bfs() {
		visited = new boolean[N][M];
		visited[0][0] = true;
		int cnt = 0;
		while (!q.isEmpty()) {
			Cell cell = q.poll();

			int nr, nc;
			for (int i = 0; i < 4; ++i) {
				nr = cell.row + dir[i][0];
				nc = cell.col + dir[i][1];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					Cell nCell = map[nr][nc];
					if (!visited[nr][nc]) {
						if (nCell.type == 1) {
							nCell.type = 0;
							cnt++;
							visited[nr][nc] = true;
						} else if (nCell.type == 0) {
							visited[nr][nc] = true;
							q.offer(nCell);
						}
					}
				}
			}
		}
		if(cnt > 0) hour++;
		cheeze = cnt;
	}

	static class Cell {
		int row, col, type;

		public Cell(int row, int col, int type) {
			super();
			this.row = row;
			this.col = col;
			this.type = type;
		}
	}
}
