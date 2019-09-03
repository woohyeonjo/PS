package SWEA.go;

import java.util.Scanner;

public class S5650_모의핀볼게임 {
	
	static class Cell {
		int r, c;
		int type;
		
		public Cell(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}
		
		@Override
		public String toString() {
			return this.r + ", " + this.c;
		}
	}
	
	static class Ball {
		int r, c;
		int dir;

		public Ball(int r, int c, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
	
	static Cell[][] wormHoles;
	static int[][] map;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int N, T, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			N = sc.nextInt();
			
			wormHoles = new Cell[11][2];
			map = new int[N][N];
			ans = 0;
			
			
			int type;
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < N ; ++c) {
					type = sc.nextInt();
					if(type >= 6) {
						if(wormHoles[type][0] == null) wormHoles[type][0] = new Cell(r, c, type);
						else wormHoles[type][1] = new Cell(r, c, type);
					}
					map[r][c] = type;
				}
			}
			
			int current = 0;
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < N ; ++c) {
					if(map[r][c] == 0) {
						for(int i = 0 ; i < 4 ; ++i) {
							current = play(new Ball(r, c, i));
							ans = current > ans ? current : ans;
						}
					}
				}
			}
			System.out.println("#" + t + " " + ans);
		}
	}

	private static int play(Ball ball) {
		Cell wormHole;
		int points = 0;
		int nr = ball.r;
		int nc = ball.c;
		
		while(true) {
			nr += dir[ball.dir][0];
			nc += dir[ball.dir][1];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
				points++;
				ball.dir = (ball.dir + 2) % 4;
				continue;
			}
			
			if(nr == ball.r && nc == ball.c) return points;
			if(map[nr][nc] == -1) {
				return points;
			} else if(map[nr][nc] >= 6) {
				wormHole = wormHoles[map[nr][nc]][0];
				if(wormHole.r == nr && wormHole.c == nc) {
					wormHole = wormHoles[map[nr][nc]][1];
					nr = wormHole.r;
					nc = wormHole.c;
				} else {
					nr = wormHole.r;
					nc = wormHole.c;
				}
				continue;
			} else if(map[nr][nc] >= 1) {
				points++;
				switch(map[nr][nc]) {
					case 1:
						if(ball.dir == 2) ball.dir = 1;
						else if(ball.dir == 3) ball.dir = 0;
						else ball.dir = (ball.dir + 2) % 4;
						continue;
					case 2:
						if(ball.dir == 3) ball.dir = 2;
						else if(ball.dir == 0) ball.dir = 1;
						else ball.dir = (ball.dir + 2) % 4;
						continue;
					case 3:
						if(ball.dir == 1) ball.dir = 2;
						else if (ball.dir == 0) ball.dir = 3;
						else ball.dir = (ball.dir + 2) % 4;
						continue;
					case 4:
						if(ball.dir == 2) ball.dir = 3;
						else if(ball.dir == 1) ball.dir = 0;
						else ball.dir = (ball.dir + 2) % 4;
						continue;
					case 5:
						ball.dir = (ball.dir + 2) % 4;
						continue;
				}
			}
		}
		
	}
}
