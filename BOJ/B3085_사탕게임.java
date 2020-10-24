package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3085_사탕게임 {

	static int[][] dir = {{1, 0}, {0, 1}};
	static char[][] map;
	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		ans = Integer.MIN_VALUE;
		
		char[] line = null;
		for(int i = 0 ; i < N ; ++i) {
			line = br.readLine().toCharArray();
			for(int j = 0 ; j < N ; ++j) {
				map[i][j] = line[j];
			}
		}
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				for(int d = 0 ; d < 2 ; ++d) {
					int nr = r + dir[d][0];
					int nc = c + dir[d][1];
					if(nr >= N || nr < 0 || nc >= N || nc < 0) continue;
					swap(r, c, nr, nc);
					eat();
					swap(r, c, nr, nc);
				}
			}
		}
		
		System.out.println(ans);
	}

	private static void eat() {
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				for(int d = 0 ; d < 2 ; ++d) {
					int nr = r;
					int nc = c;
					int cnt = 0;
					while(map[nr][nc] == map[r][c]) {
						cnt++;
						nr += dir[d][0];
						nc += dir[d][1];
						if(nr >= N || nr < 0 || nc >= N || nc < 0) break;
					}
					ans = cnt > ans ? cnt : ans;
				}
			}
		}
		
	}
	
	private static void swap(int r1, int c1, int r2, int c2) {
		char temp = map[r1][c1];
		
		map[r1][c1] = map[r2][c2];
		map[r2][c2] = temp;
	}
}
