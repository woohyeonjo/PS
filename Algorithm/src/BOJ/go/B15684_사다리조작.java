package BOJ.go;

import java.util.Scanner;

public class B15684_사다리조작 {
	
	static int[][] map;
	static int N, M, H, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		
		ans = 4;
		map = new int[H + 1][N * 2 + 1];
		
		for(int r = 1 ; r <= H ; ++r) {
			for(int c = 1 ; c < N * 2 ; c += 2) {
				map[r][c] = 1;
			}
		}
		
		int a, b;
		for(int m = 0 ; m < M ; ++m) {
			a = sc.nextInt();
			b = sc.nextInt();
			
			map[a][b * 2] = 1;
		}
		
		for(int i = 0 ; i <= 3 ; ++i) {
			go(i, 0, 1, 1);
		}
		
		if( ans == 4) ans = -1;
		System.out.println(ans);
	}
	
	private static void go(int count, int depth, int R, int C) {
		
		if(depth == count || C == N * 2) {
			if(ride()) {
				ans = ans > count ? count : ans;
			}
			return;
		}
		
		boolean flag = true;
		for(int r = 1 ; r <= H ; ++r) {
			for(int c = 1 ; c < N * 2 ; ++c) {
				if(flag) {
					r = R;
					c = C;
					flag = false;
				}
				if(map[r][c] == 0) {
					if(c == 1) {
						if(map[r][c + 2] == 1) continue;
					} else if (c == N * 2 - 1) {
						if(map[r][c - 2] == 1) continue;
					} else {
						if(map[r][c + 2] == 1 || map[r][c - 2] == 1) continue;
					}
					map[r][c] = 1;
					go(count, depth + 1, r, c + 1);
					map[r][c] = 0;
				}
			}
		}
	}

	private static boolean ride() {
		for(int c = 1 ; c < N * 2 ; c += 2) {
			int r = 1;
			int cc = c;
			while(true) {
				if(r > H) break;
				if(map[r][cc - 1] == 1) cc -= 2;
				else if(map[r][cc + 1] == 1) cc += 2;
				r++;
			}
			if(c != cc) return false;
		}
		return true;
	}
}
