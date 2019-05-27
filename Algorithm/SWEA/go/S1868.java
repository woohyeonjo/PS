package Algorithm.SWEA.go;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class S1868 {
	static Cell[][] map;
	static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	static Queue<Cell> q = new LinkedList<Cell>();
	static int T, N, cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = Integer.parseInt(sc.nextLine());
		for(int t = 1; t <= T ; ++t) {
			N = Integer.parseInt(sc.nextLine());
			map = new Cell[N + 1][N + 1];
			cnt = 0;
			
			for(int row = 0 ; row < N ; ++row) {
				String[] temp = sc.nextLine().split("");
				for(int col = 0 ; col < N ; ++col) {
					map[row][col] = new Cell(row, col, temp[col]);
				}
			}
			
			int nextRow, nextCol;
			
			for(int row = 0 ; row < N ; ++row) {
	COL:		for(int col = 0 ; col < N ; ++col) {
					if(map[row][col].type.equals(".")) {
						for(int i = 0 ; i < 8 ; ++i) {
							nextRow = map[row][col].row + dir[i][0];
							nextCol = map[row][col].col + dir[i][1];
							if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
								if(map[nextRow][nextCol].type.equals("*")) continue COL;
							}
						}
						for(int i = 0 ; i < 8 ; ++i) {
							nextRow = map[row][col].row + dir[i][0];
							nextCol = map[row][col].col + dir[i][1];
							if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
								if(map[nextRow][nextCol].type.equals(".")) {
									q.offer(map[nextRow][nextCol]);
								}
							}
						}
						map[row][col].type = "" + 0;
						bfs();
						cnt++;
						print();
					}
				}
			}
			
			for(int row = 0 ; row < N ; ++row) {
				for(int col = 0 ; col < N ; ++col) {
					if(map[row][col].type.equals(".")) {
						q.offer(map[row][col]);
						bfs();
						cnt++;
						print();
					}
				}
			}
			System.out.println("#" + t + " " + cnt);
		}
		
	}
	
	private static void bfs() {
		int nextRow, nextCol;
		int mineCnt;
		while(!q.isEmpty()) {
			Cell cell = q.poll();
			mineCnt = 0;
			
			for(int i = 0 ; i < 8 ; ++i) {
				nextRow = cell.row + dir[i][0];
				nextCol = cell.col + dir[i][1];
				if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
					if(map[nextRow][nextCol].type.equals("*")) mineCnt++;
				}
			}
			if(mineCnt == 0) {
				for(int i = 0 ; i < 8 ; ++i) {
					nextRow = cell.row + dir[i][0];
					nextCol = cell.col + dir[i][1];
					if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
						if(map[nextRow][nextCol].type.equals(".")) {
							q.offer(map[nextRow][nextCol]);
						}
					}
				}
			}
			cell.type = "" + mineCnt;
		}
	}
	
	private static void print() {
		for(int row = 0 ; row < N ; ++row) {
			for(int col = 0 ; col < N ; ++col) {
				System.out.print(" " + map[row][col].type);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static class Cell {
		int row, col;
		String type;

		public Cell(int row, int col, String type) {
			super();
			this.row = row;
			this.col = col;
			this.type = type;
		}
		@Override
		public String toString() {
			return "" + type;
		}
	}
}
