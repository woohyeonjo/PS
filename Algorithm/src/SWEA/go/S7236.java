package SWEA.go;

import java.util.Scanner;

public class S7236 {
	
	static int[][] map;
	static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	static int T, N, ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		for(int t = 1 ; t <= T ; ++t) {
			N = sc.nextInt();
			
			map = new int[N][N];
			String input = "";
			ans = 0;
			
			for(int row = 0 ; row < N ; ++row) {
				for(int col = 0 ; col < N ; ++col) {
					input = sc.next();
					if(input.equals("G")) map[row][col] = 0;
					else map[row][col] = 1;
				}
			}
			
			int nextR, nextC;
			
			for(int row = 0 ; row < N ; ++row) {
				for(int col = 0 ; col < N ; ++col) {
					if(map[row][col] != 0) {
						int cnt = 0;
						for(int i = 0 ; i < 8 ; ++i) {
							nextR = row + dir[i][0];
							nextC = col + dir[i][1];
							if(nextR >= 0 && nextR < N && nextC >=0 && nextC < N) {
								if(map[nextR][nextC] != 0) {
									cnt++;
								}
							}
						}
						map[row][col] = cnt;
						if(cnt > ans) ans = cnt;
					}
				}
			}
			if(ans == 0) ans = 1;
			System.out.println("#" + t + " " + ans);
		}
	}
}
