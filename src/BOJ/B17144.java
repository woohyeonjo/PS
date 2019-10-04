package BOJ;


import java.util.Scanner;

public class B17144 {
	
	static int R, C, T;
	static int ans;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[][] dir2 = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int[][] machine = new int[2][2];
	static int[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		
		map = new int[R][C];
		
		for(int r = 0 ; r < R ; ++r){
			for(int c = 0 ; c < C ; ++c){
				map[r][c] = sc.nextInt();
				if(map[r][c] == -1){
					if(machine[0][0] == 0){
						machine[0][0] = r;
						machine[0][1] = c;
					} else {
						machine[1][0] = r;
						machine[1][1] = c;
					}
				}
			}
		}
		
		for(int t = 0 ; t < T ; ++t){
			spread();
			run();
		}
		
		ans = check();
		System.out.println(ans);
	}

	private static int check() {
		int result = 0;
		
		for(int r = 0 ; r < R ; ++r){
			for(int c = 0 ; c < C ; ++c){
				if(map[r][c] == -1 || map[r][c] == 0) continue;
				result += map[r][c];
			}
		}
		return result;
	}

	private static void run() {
		int pr = machine[0][0], pc = machine[0][1];
		int nr, nc;
		
		// M1
		for(int i = 0 ; i < dir.length ; ++i){
			while(true) {
				nr = pr + dir[i][0];
				nc = pc + dir[i][1];
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || nr > machine[0][0]) break;
				if(map[pr][pc] != -1) {
					if(map[nr][nc] == -1) map[pr][pc] = 0;
					else map[pr][pc] = map[nr][nc];
				}
				pr = nr;
				pc = nc;
			}
		}
		
		pr = machine[1][0];
		pc = machine[1][1];
		// M2
		for(int i = 0 ; i < dir2.length ; ++i){
			while(true) {
				nr = pr + dir2[i][0];
				nc = pc + dir2[i][1];
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || nr < machine[1][0]) break;
				if(map[pr][pc] != -1) {
					if(map[nr][nc] == -1) map[pr][pc] = 0;
					else map[pr][pc] = map[nr][nc];
				}
				pr = nr;
				pc = nc;
			}
		}
	}

	private static void spread() {
		int[][] temp = new int[R][C];
		int nr, nc;
		int cellCnt;
		
		for(int r = 0 ; r < R ; ++r){
			for(int c = 0 ; c < C ; ++c){
				if(map[r][c] == -1 || map[r][c] == 0) continue;
				cellCnt = 0;
				nr = 0;
				nc = 0;
				
				for(int i = 0 ; i < dir.length ; ++i){
					nr = r + dir[i][0];
					nc = c + dir[i][1];
					
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
					if(map[nr][nc] == -1) continue;
					cellCnt++;
					temp[nr][nc] += (int) map[r][c] / 5;
				}
				map[r][c] = map[r][c] - ((int)(map[r][c] / 5) * cellCnt);
			}
		}
		
		for(int r = 0 ; r < R ; ++r){
			for(int c = 0 ; c < C ; ++c){
				if(map[r][c] == -1) continue;
				map[r][c] += temp[r][c];
			}
		}
	}
}
