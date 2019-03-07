package go.woohyeon;

import java.util.Scanner;

public class B2636 {
//  1. 공기를 bfs 하여서 라벨링을 한다
//  2. 라벨링된 공기와 접촉한 부분의 치즈를 라벨링한다.
//  3. 라벨링된 치즈를 제거한다.
//	4. 1 - 3 반복
//	5. 계속해서 시간과 남은 라벨링 된 치즈를 저장한다.

	static Cell[][] map;
	static boolean[][] visited;
	static int N, M;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new Cell[N][M];
		visited = new boolean[N][M];
		
		for(int row = 0 ; row < N ; ++row) {
			for(int col = 0 ; col < M ; ++col) {
				map[row][col] = new Cell(row, col, sc.nextInt());
			}
		}
		
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
