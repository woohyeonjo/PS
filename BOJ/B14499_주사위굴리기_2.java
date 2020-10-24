package BOJ;

import java.util.Scanner;

public class B14499_주사위굴리기_2 {
	
	static class Dice {
		int[] v = new int[4];
		int[] h = new int[4];
		
		public int west(int[][] map, int r, int c) {
			int temp = h[3];
			
			for(int i = 3 ; i > 0 ; --i) {
				h[i] = h[i - 1];
			}
			h[0] = temp;
			
			if(map[r][c] == 0) {
				map[r][c] = h[3];
			} else {
				h[3] = map[r][c];
				map[r][c] = 0;
			}
			
			v[3] = h[3];
			v[1] = h[1];
			
			return h[1];
		}
		public int east(int[][] map, int r, int c) {
			int temp = h[0];
			
			for(int i = 0 ; i < 3 ; ++i) {
				h[i] = h[i + 1];
			}
			h[3] = temp;
			
			if(map[r][c] == 0) {
				map[r][c] = h[3];
			} else {
				h[3] = map[r][c];
				map[r][c] = 0;
			}
			v[3] = h[3];
			v[1] = h[1];
			
			return h[1];
		}
		public int south(int[][] map, int r, int c) {
			int temp = v[3];
			
			for(int i = 3 ; i > 0 ; --i) {
				v[i] = v[i - 1];
			}
			v[0] = temp;
			
			if(map[r][c] == 0) {
				map[r][c] = v[3];
			} else {
				v[3] = map[r][c];
				map[r][c] = 0;
			}
			
			h[3] = v[3];
			h[1] = v[1];
			
			return v[1];
		}
		public int north(int[][] map, int r, int c) {
			int temp = v[0];
			
			for(int i = 0 ; i < 3 ; ++i) {
				v[i] = v[i + 1];
			}
			v[3] = temp;
			
			if(map[r][c] == 0) {
				map[r][c] = v[3];
			} else {
				v[3] = map[r][c];
				map[r][c] = 0;
			}
			
			h[3] = v[3];
			h[1] = v[1];
			
			return v[1];
		}
	}
	
	static int N, M, R, C, COMMAND_CNT;
	static int[][] map;
	static int[][] dir = {{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	static Dice dice;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();
		C = sc.nextInt();
		COMMAND_CNT = sc.nextInt();
		
		map = new int[N][M];
		dice = new Dice();
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = sc.nextInt();
			}
		}
		
		int command = 0;
		int r = R;
		int c = C;
		int nr, nc;
		for(int i = 0 ; i < COMMAND_CNT ; ++i) {
			command = sc.nextInt();
			
			nr = r + dir[command][0];
			nc = c + dir[command][1];
			
			
			if(nr >= N || 0 > nr || nc >= M || 0 > nc) continue;
			
			
			switch(command) {
			case 1:
				// 동 
				System.out.println(dice.east(map, nr, nc));
				break;
			case 2:
				// 서 
				System.out.println(dice.west(map, nr, nc));
				break;
			case 3:
				// 북 
				System.out.println(dice.north(map, nr, nc));
				break;
			case 4:
				// 남 
				System.out.println(dice.south(map, nr, nc));
				break;
			}
			
			
			r = nr;
			c = nc;
			
		}
		
	}
}
