package BOJ;


import java.util.Scanner;

public class B17144_미세먼지안녕 {
	
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
		int r = machine[0][0], c = machine[0][1];
		int nr, nc, d = 0;
		
		while(d < 4){
			nr = r + dir[d][0];
			nc = c + dir[d][1];
			if(nr >= 0 && nr <= machine[0][0] && nc >= 0 && nc < C){
				map[r][c] = map[nr][nc];
				r = nr;
				c = nc;
			} else d++;
		}
		map[machine[0][0]][machine[0][1]] = -1;
		map[machine[0][0]][machine[0][1] + 1] = 0;
		
		d = 0;
		r = machine[1][0];
		c = machine[1][1];
		while(d < 4){
			nr = r + dir2[d][0];
			nc = c + dir2[d][1];
			if(nr >= machine[1][0] && nr < R && nc >= 0 && nc < C){
				map[r][c] = map[nr][nc];
				r = nr;
				c = nc;
			} else d++;
		}
		map[machine[1][0]][machine[1][1]] = -1;
		map[machine[1][0]][machine[1][1] + 1] = 0;
		
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
