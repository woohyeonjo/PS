package BOJ.go;

import java.util.LinkedList;
import java.util.Queue;
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
	
	static Queue<Bomb> q;
	static Bomb[][] map;
	static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static int R, C, N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		N = sc.nextInt();
		
		map = new Bomb[R][C];
		q = new LinkedList<>();
		
		char[] line;
		for(int r = 0 ; r < R ; ++r){
			line = sc.next().toCharArray();
			for(int c = 0 ; c < C ; ++c){
				if(line[c] == '.') map[r][c] = new Bomb(r, c, -1, false);
				else {
					map[r][c] = new Bomb(r, c, 3, true);
					q.offer(map[r][c]);
				}
			}
		}
		go();
		print();
	}

	private static void go() {
		int TIME = 0;
		while(!q.isEmpty()){
			if(TIME == N) return;
			
			Bomb bomb = q.poll();
			
			if(bomb.time == 0){
				bomb.isLive = false;
				bomb.time = -1;
				for(int i = 0 ; i < 4 ; ++i){
					map[bomb.r + dir[i][0]][bomb.c + dir[i][1]].isLive = false;
				}
			} else if(bomb.isLive && bomb.time > 0){
				bomb.time--;
				q.offer(bomb);
			}
			
			setBomb();
			TIME++;
		}
	}

	private static void setBomb() {
		
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
