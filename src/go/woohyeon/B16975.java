package go.woohyeon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B16975 {
	
	static Cell[][] board;
	static Queue<Cell> q = new LinkedList <Cell>();
	static int[][] dir = {{-1, 0 }, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {-1, 1}, {0, -1}, {-1, -1}};
	static int R, C;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		board = new Cell[R][C];
		
		for(int r = 0 ; r < R ; ++r) {
			for(int c = 0 ; c < C ; ++c) {
				board[r][c] = new Cell(r, c, sc.nextInt(), 1);
			}
		}
		
		for(int r = 0 ; r < R ; ++r) {
			for(int c = 0 ; c < C ; ++c) {
				if(board[r][c].value != 0) {
					q.offer(board[r][c]);
					bfs();
				}
			}
		}
		
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
		
		int nextR, nextC;
		int minVal;
		Cell minCell;
		
		while(!q.isEmpty()) {
			Cell c = q.poll();
			minVal = Integer.MAX_VALUE;
			minCell = null;
			
			for(int i = 0 ; i < 8 ; ++i) {
				nextR = c.row + dir[i][0];
				nextC = c.col + dir[i][1];
				if(nextR >= 0 && nextR < R && nextC >= 0 && nextC < C) {
					Cell temp = board[nextR][nextC];
					if(minVal > temp.value) {
						minVal = temp.value;
						minCell = temp;
					}
				}
			}
			if(c.value < minVal) return;
			else {
				c.ballCnt--;
				minCell.ballCnt++;
				q.offer(minCell);
			}
		}
	}

	static class Cell{
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
		
		
	}
}
