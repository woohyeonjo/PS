package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17144_미세먼지안녕_2 {
	
	static int[][] antiClockwise = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[][] clockwise = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

	static int[] cleaner;
	static int[][] map;
	
	static int R, C, T, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R + 1][C + 1];
		cleaner = new int[4];
		
		for(int r = 1 ; r <= R ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1 ; c <= C ; ++c) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if(map[r][c] == -1) {
					if(cleaner[0] == 0) {
						cleaner[0] = r;
						cleaner[1] = c;
					} else {
						cleaner[2] = r;
						cleaner[3] = c;
					}
				}
			}
		}
		
		for(int t = 0 ; t < T ; ++t) {
			spread();
			activate();
		}
		
		for(int r = 1 ; r <= R ; ++r) {
			for(int c = 1 ; c <= C ; ++c) {
				if(map[r][c] == -1) continue;
				ans += map[r][c];
			}
		}
		
		System.out.println(ans);
	}

	private static void activate() {
		// top : antiClockwise
		int r = cleaner[0] - 1;
		int c = cleaner[1];
		int nr = 0, nc = 0;
		int d = 0;
		
		while(d < 4) {
			nr = r + antiClockwise[d][0];
			nc = c + antiClockwise[d][1];
			if(nr >= 1 && nr <= cleaner[0] && nc >= 1 && nc <= C) {
				if(map[nr][nc] == -1) {
					map[r][c] = 0;
					break;
				}
				
				map[r][c] = map[nr][nc];
				r = nr;
				c = nc;
			} else d++;
		}
		
		// bottom : clockwise
		r = cleaner[2] + 1;
		c = cleaner[3];
		nr = 0; nc = 0;
		d = 0;
		
		while(d < 4) {
			nr = r + clockwise[d][0];
			nc = c + clockwise[d][1];
			if(nr >= cleaner[2] && nr <= R && nc >= 1 && nc <= C) {
				if(map[nr][nc] == -1) {
					map[r][c] = 0;
					break;
				}
				
				map[r][c] = map[nr][nc];
				r = nr;
				c = nc;
			} else d++;
		}
	}

	private static void spread() {
		int[][] temp = new int[R + 1][C + 1];
		
		for(int r = 1 ; r <= R ; ++r) {
			for(int c = 1 ; c <= C ; ++c) {
				if(map[r][c] == -1 || map[r][c] == 0) continue;
				
				int dust = map[r][c];
				int cnt = 0;
				
				for(int d = 0 ; d < 4 ; ++d) {
					int nr = r + clockwise[d][0];
					int nc = c + clockwise[d][1];
					
					if(nr < 1 || nr > R || nc < 1 || nc > C || map[nr][nc] == -1) continue;
					
					temp[nr][nc] += dust / 5;
					cnt++;
				}
				
				map[r][c] -= (dust / 5) * cnt;
			}
		}
		
		for(int r = 1 ; r <= R ; ++r) {
			for(int c = 1 ; c <= C ; ++c) {
				if(map[r][c] == -1) continue;
				map[r][c] += temp[r][c];
			}
		}
	}
	
	private static void print() {
		for(int r = 1 ; r <= R ; ++r) {
			for(int c = 1 ; c <= C ; ++c) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
