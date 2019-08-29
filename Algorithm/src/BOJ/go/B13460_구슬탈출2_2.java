package BOJ.go;

import java.util.Scanner;

public class B13460_구슬탈출2_2 {
	static class Ball {
		int r, c;
		char color;

		public Ball(int r, int c, char color) {
			super();
			this.r = r;
			this.c = c;
			this.color = color;
		}
	}
	
	static char[][] map;
	static char[][] map_copy;
	static int[] selected;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, M, ans;
	static Ball RED, BLUE;
	static boolean gameover;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sc.nextLine();
		
		map = new char[N][M];
		ans = -1;
		
		char[] line;
		for(int r = 0 ; r < N ; ++r) {
			line = sc.nextLine().toCharArray();
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = line[c];
				if(line[c] == 'R') RED = new Ball(r, c, 'R');
				else if(line[c] == 'B') BLUE = new Ball(r, c, 'B');
			}
		}
		
		for(int i = 1 ; i <= 10; ++i) {
			selected = new int[i];
			go(i, 0, -1);
		}
		
		System.out.println(ans);
		
	}

	private static void go(int limit, int index, int before) {
		if(gameover) return;
		
		if(index >= limit) {
			copy();
			if(play(limit)) {
				ans = limit;
				gameover = true;
			}
			return;
		}
		
		for(int i = 0 ; i < 4 ; ++i) {
			if(i == before) continue;
			selected[index] = i;
			go(limit, index + 1, i);
		}
	}

	private static boolean play(int limit) {
		Ball blue = new Ball(BLUE.r, BLUE.c, BLUE.color);
		Ball red = new Ball(RED.r, RED.c, RED.color);
		int nr, nc;
		boolean afterRed, afterBlue;
		
		for(int i = 0 ; i < limit ; ++i) {
			afterRed = false;
			nr = blue.r;
			nc = blue.c;
			while(true) {
				nr += dir[selected[i]][0];
				nc += dir[selected[i]][1];
				
				if(map_copy[nr][nc] == 'O') return false;
				else if(map_copy[nr][nc] == '#') {
					if(afterRed) {
						map_copy[blue.r][blue.c] = '.';
						blue.r = nr - dir[selected[i]][0] * 2;
						blue.c = nc - dir[selected[i]][1] * 2;
						map_copy[blue.r][blue.c] = 'B';
					} else {
						map_copy[blue.r][blue.c] = '.';
						blue.r = nr - dir[selected[i]][0];
						blue.c = nc - dir[selected[i]][1];
						map_copy[blue.r][blue.c] = 'B';
					}
					break;
				}
				else if(map_copy[nr][nc] == 'R') afterRed = true;
			}
			
			afterBlue = false;
			nr = red.r;
			nc = red.c;
			while(true) {
				nr += dir[selected[i]][0];
				nc += dir[selected[i]][1];
				
				if(map_copy[nr][nc] == '#') {
					if(afterBlue) {
						map_copy[red.r][red.c] = '.';
						red.r = nr - dir[selected[i]][0] * 2;
						red.c = nc - dir[selected[i]][1] * 2;
						map_copy[red.r][red.c] = 'R';
					} else {
						map_copy[red.r][red.c] = '.';
						red.r = nr - dir[selected[i]][0];
						red.c = nc - dir[selected[i]][1];
						map_copy[red.r][red.c] = 'R';
					}
					break;
				}
				else if(map_copy[nr][nc] == 'O') return true;
				else if(map_copy[nr][nc] == 'B' && !afterRed) afterBlue = true;
			}
		}
		return false;
	}

	private static void copy() {
		map_copy = new char[N][M];
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				map_copy[r][c] = map[r][c];
			}
		}
	}
}
