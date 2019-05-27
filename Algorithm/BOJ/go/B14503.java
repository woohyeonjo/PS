package Algorithm.BOJ.go;

import java.util.Scanner;

public class B14503 {
	
	static Cell[][] map;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int N, M, R, C, D, ans;
	
	static class Cell {
		int r, c, type;
		boolean isClean;
		
		public Cell(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
			this.isClean = false;
		}
		
		@Override
		public String toString() {
			if(this.isClean) return 3 + "";
			else return this.type + "";
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();
		C = sc.nextInt();
		D = sc.nextInt();
		
		map = new Cell[N][M];
	
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = new Cell(r, c, sc.nextInt());
			}
		}
		
		int turnCnt = 0;
		int nowDir = D;
		int r = R, c = C, nr, nc;
		while(true) {
			if(turnCnt == 4) {
				turnCnt = 0;
				r = r - dir[nowDir][0];
				c = c - dir[nowDir][1];
				if(map[r][c].type == 1) break;
			}
			if(!map[r][c].isClean) cleanUp(r, c);
			
			nowDir = turn(nowDir);
			turnCnt++;
			nr = r + dir[nowDir][0];
			nc = c + dir[nowDir][1];
			if(map[nr][nc].type == 1 || map[nr][nc].isClean) continue;
			turnCnt = 0;
			r = nr;
			c = nc;
		}
		System.out.println(ans);
	}
	
	
	
	private static int turn(int nowDir) {
		int temp = nowDir - 1;
		if(temp < 0) temp = 3;
		return temp;
	}

	private static void cleanUp(int r, int c) {
		map[r][c].isClean = true;
		ans++;
	}
	
	
}
