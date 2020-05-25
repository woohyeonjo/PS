package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1949_모의등산로조성_2 {
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	static int[][] map;
	static int T, N, K, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int i = 1 ; i <= T ; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
		
			map = new int[N][N];
			visited = new boolean[N][N];
			ans = 0;
			
			for(int r = 0 ; r < N ; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0 ; c < N ; ++c) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < N ; ++c) {
					for(int k = 1 ; k <= K ; ++k) {
						 map[r][c] -= k;
						 int height = findHighest();
						 findRoad(height);
						 map[r][c] += k;
					}
				}
			}
			
			System.out.println("#" + i + " " + ans);
		}
	
	}

	private static int findHighest() {
		int highest = 0;
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				highest = map[r][c] > highest ? map[r][c] : highest;
			}
		}
		
		return highest;
	}

	private static void findRoad(int height) {
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				if(map[r][c] == height) {
					init();
					dfs(r, c, 1);
				}
			}
		}
	}

	private static void dfs(int r, int c, int length) {
		ans = length > ans ? length : ans;
		
		for(int d = 0 ; d < 4 ; ++d) {
			int nr = r + dir[d][0];
			int nc = c + dir[d][1];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
			if(map[nr][nc] < map[r][c]) {
				visited[nr][nc] = true;
				dfs(nr, nc, length + 1);
				visited[nr][nc] = false;
			}
		}
	}
	
	private static void init() {
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				visited[r][c] = false;
			}
		}
	}
}
