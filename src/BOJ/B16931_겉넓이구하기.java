package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16931_겉넓이구하기 {
	
	static int[][] map;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, M, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = 0;
		
		map = new int[N][M];
		
		for(int r = 0 ; r < N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 위, 아래
		ans += N * M * 2;
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				int cnt = map[r][c] * 4;
				
				for(int d = 0 ; d < 4 ; ++d) {
					int nr = r + dir[d][0];
					int nc = c + dir[d][1];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

					if(map[nr][nc] > map[r][c]) {
						cnt -= map[r][c];
					} else {
						cnt -= map[nr][nc];
					}
				}
				
				ans += cnt;
			}
		}
		
		System.out.println(ans);
	}
}
