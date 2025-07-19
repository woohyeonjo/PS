package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class S2105_모의디저트카페_1 {
	
	static HashSet<Integer> set;
	static int[][] dir = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
	static boolean[][] visited;
	static int[][] map;
	static int N, T, ans, cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T ; ++t) {
			N = Integer.parseInt(br.readLine());
			
			set = new HashSet<>();
			map = new int[N][N];
			visited = new boolean[N][N];
			ans = -1;
			
			for(int r = 0 ; r < N ; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0 ; c < N ; ++c) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int r = 0 ; r < N - 2 ; ++r) {
				for(int c = 1 ; c < N - 1 ; ++c) {
					init();
					set.add(map[r][c]);
					visited[r][c] = true;
					tour(r, c, r, c, 0);
					visited[r][c] = false;
					set.remove(map[r][c]);
				}
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void tour(int cr, int cc, int sr, int sc, int curve) {
		for(int d = curve ; d < 4 ; ++d) {
			int nr = cr + dir[d][0];
			int nc = cc + dir[d][1];
			
			if(set.size() >= 3 && nr == sr && nc == sc) {
				cnt = set.size();
				ans = cnt > ans ? cnt : ans;

				return;
			}

			if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
			if(set.contains(map[nr][nc])) continue;
			
			visited[nr][nc] = true;
			set.add(map[nr][nc]);
			tour(nr, nc, sr, sc, d);
			set.remove(map[nr][nc]);
			visited[nr][nc] = false;
		}
		
	}

	private static void init() {
		set.clear();
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				visited[r][c] = false;
			}
		}
	}
}
