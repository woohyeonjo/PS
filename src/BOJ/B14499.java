package BOJ;


import java.util.Arrays;
import java.util.Scanner;

public class B14499 {
	static class Dice {
		int r, c;
		int top, bottom;
		int[] horizen;
		int[] vertical;
		
		public Dice() {}
		public Dice(int r, int c) {
			super();
			this.r = r;
			this.c = c;
			this.horizen = new int[4];
			this.vertical = new int[4];
			Arrays.fill(horizen, 0);
			Arrays.fill(vertical, 0);
			
			top = vertical[1];
			bottom = vertical[3];
		}
		
		public void draw(int num) {
			bottom = num;
			horizen[3] = num;
			vertical[3] = num;
		}
		
		private void calc(char dir) {
			if(dir == 'v') {
				top = vertical[1];
				bottom = vertical[3];
				horizen[1] = top;
				horizen[3] = bottom;
			} else if (dir == 'h') {
				top = horizen[1];
				bottom = horizen[3];
				vertical[1] = top;
				vertical[3] = bottom;
			}
		}
		
		public void goN(int r, int c) {
			int temp = vertical[0];
			for(int i = 0 ; i < 3 ; ++i) {
				vertical[i] = vertical[i + 1]; 
			}
			vertical[3] = temp;
			calc('v');
			this.r = r;
			this.c = c;
		}
		
		public void goS(int r, int c) {
			int temp = vertical[3];
			for(int i = 3 ; i >= 1 ; --i) {
				vertical[i] = vertical[i - 1]; 
			}
			vertical[0] = temp;
			calc('v');
			this.r = r;
			this.c = c;
		}
		
		public void goE(int r, int c) {
			int temp = horizen[0];
			for(int i = 0 ; i < 3 ; ++i) {
				horizen[i] = horizen[i + 1]; 
			}
			horizen[3] = temp;
			calc('h');
			this.r = r;
			this.c = c;
		}
		
		public void goW(int r, int c) {
			int temp = horizen[3];
			for(int i = 3 ; i >= 1 ; --i) {
				horizen[i] = horizen[i - 1]; 
			}
			horizen[0] = temp;
			calc('h');
			this.r = r;
			this.c = c;
		}
	}
	
	static int[][] map;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Dice dice;
	static int N, M, R, C, O;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();
		C = sc.nextInt();
		O = sc.nextInt();
		
		map = new int[N][M];
		dice = new Dice(R, C);
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = sc.nextInt();
			}
		}
		
		for(int o = 0 ; o < O ; ++o) {
			int command = sc.nextInt();
			int nr, nc;
			switch(command) {
				// 동쪽
				case 1:
					nr = dice.r + dir[3][0];
					nc = dice.c + dir[3][1];
					if(nr >= N || nr < 0 || nc >= M || nc < 0) continue;
					dice.goE(nr, nc);
					if(map[nr][nc] == 0) {
						map[nr][nc] = dice.bottom;
					} else {
						dice.draw(map[nr][nc]);
						map[nr][nc] = 0;
					}
					break;
					
				// 서쪽
				case 2:
					nr = dice.r + dir[2][0];
					nc = dice.c + dir[2][1];
					if(nr >= N || nr < 0 || nc >= M || nc < 0) continue;
					dice.goW(nr, nc);
					if(map[nr][nc] == 0) {
						map[nr][nc] = dice.bottom;
					} else {
						dice.draw(map[nr][nc]);
						map[nr][nc] = 0;
					}
					break;
					
				// 북쪽
				case 3:
					nr = dice.r + dir[0][0];
					nc = dice.c + dir[0][1];
					if(nr >= N || nr < 0 || nc >= M || nc < 0) continue;
					dice.goN(nr, nc);
					if(map[nr][nc] == 0) {
						map[nr][nc] = dice.bottom;
					} else {
						dice.draw(map[nr][nc]);
						map[nr][nc] = 0;
					}
					break;
			
				// 남쪽
				case 4:
					nr = dice.r + dir[1][0];
					nc = dice.c + dir[1][1];
					if(nr >= N || nr < 0 || nc >= M || nc < 0) continue;
					dice.goS(nr, nc);
					if(map[nr][nc] == 0) {
						map[nr][nc] = dice.bottom;
					} else {
						dice.draw(map[nr][nc]);
						map[nr][nc] = 0;
					}
					break;
			}
			System.out.println(dice.top + "");
		}
	}
}
