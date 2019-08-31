package BOJ.go;

import java.util.Scanner;

public class B16918_봄버맨 {
	static class Bomb {
		int r, c, time;
		boolean isLive;
		
		public Bomb(int r, int c, int time, boolean isLive) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
			this.isLive = isLive;
		}
		
		@Override
		public String toString() {
			if(isLive) return "O";
			else return ".";
		}
	}
	
	static Bomb[][] map;
	static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static int R, C, N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		N = sc.nextInt();
		
		map = new Bomb[R][C];
		
		char[] line;
		for(int r = 0 ; r < R ; ++r){
			line = sc.next().toCharArray();
			for(int c = 0 ; c < C ; ++c){
				if(line[c] == '.') map[r][c] = new Bomb(r, c, -1, false);
				else {
					map[r][c] = new Bomb(r, c, 2, true);
				}
			}
		}
		
		while(true) {
			if(timePass()) break;
			boom();
			
			setBomb();
			
			if(timePass()) break;
			boom();
		}
		print();
	}

	private static void boom() {
		int nr, nc;
		
		for(int r = 0 ; r < R ; ++r) {
			for(int c = 0 ; c < C ; ++c) {
				if(map[r][c].time == 0) {
					map[r][c].time = -1;
					map[r][c].isLive = false;
					for(int i = 0 ; i < 4 ; ++i) {
						nr = r + dir[i][0];
						nc = c + dir[i][1];
						if(nr >= R || nr < 0 || nc >= C || nc < 0) continue;
						
						if(map[nr][nc].isLive) {
							if(map[nr][nc].time > 0) {
								map[nr][nc].isLive = false;
								map[nr][nc].time = -1;
							}
							else map[nr][nc].isLive = false;
						}
					}
					
				}
			}
		}
	}

	private static boolean timePass() {
		N--;
		if(N == 0) return true;
		
		for(int r = 0 ; r < R ; ++r) {
			for(int c = 0 ; c < C ; ++c) {
				if(map[r][c].isLive) {
					map[r][c].time--;
				}
			}
		}
		return false;
	}

	private static void setBomb() {
		for(int r = 0 ; r < R ; ++r) {
			for(int c = 0 ; c < C ; ++c) {
				if(!map[r][c].isLive) {
					map[r][c].isLive = true;
					map[r][c].time = 3;
				}
			}
		}
	}

	private static void print() {
		for(int r = 0 ; r < R ; ++r){
			for(int c = 0 ; c < C ; ++c){
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
	}
}
