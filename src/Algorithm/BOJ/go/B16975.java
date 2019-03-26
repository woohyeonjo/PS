package Algorithm.BOJ.go;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class B16975 {
	
	static Cell[][] board;
	static Queue<Cell> q = new PriorityQueue<Cell>();
	static int[][] dir = {{-1, 0 }, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	static int R, C;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		board = new Cell[R][C];
		
		for(int r = 0 ; r < R ; ++r) {
			for(int c = 0 ; c < C ; ++c) {
				board[r][c] = new Cell(r, c, sc.nextInt(), 1);
				q.offer(board[r][c]);
			}
		}
		
		bfs();
		
		for(int r = 0 ; r < R ; ++r) {
			for(int c = 0 ; c < C ; ++c) {
				if(c == 0) {
					System.out.print(board[r][c].toString());
				} else {
					System.out.print(" " + board[r][c].toString());
				}
			}
			System.out.println();
		}
	}
	
	private static void bfs() {
		
		while(!q.isEmpty()) {
			Cell c = q.poll();
			
			int min = c.value;
			int tr = 0, tc = 0;
			int nr, nc;
			for(int i = 0 ; i < 8 ; ++i) {
				nr = c.row + dir[i][0];
				nc = c.col + dir[i][1];
				
				if(nr >= R || nr < 0 || nc >= C || nc < 0) continue;
				if(min > board[nr][nc].value) {
					min = board[nr][nc].value;
					tr = nr;
					tc = nc;
				}
			}
			if(min != c.value) {
				board[tr][tc].ballCnt += c.ballCnt;
				c.ballCnt = 0;
			}
		} 
	}

	static class Cell implements Comparable<Cell>{
		int row, col, value, ballCnt;

		public Cell(int row, int col, int value, int ballCnt) {
			super();
			this.row = row;
			this.col = col;
			this.value = value;
			this.ballCnt = ballCnt;
		}

		@Override
		public String toString() {
			return ballCnt + "";
		}

		@Override
		public int compareTo(Cell o) {
			return -(this.value - o.value);
		}
		
		
	}
}
