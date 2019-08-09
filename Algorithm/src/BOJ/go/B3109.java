package BOJ.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B3109 {
	static char[][] map;
	static boolean[][] DP;
	static int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
	static int R, C, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		cnt = 0;
		map = new char[R][C];
		DP = new boolean[R][C];
		
		for(int r = 0 ; r < R; ++r) Arrays.fill(DP[r], true);
		for (int r = 0; r < R; ++r) {
			char[] line = in.readLine().toCharArray();
			for (int c = 0; c < C; ++c) {
				map[r][c] = line[c];
			}
		}
		
		for(int r = 0 ; r < R ; ++r) {
			DP[r][0] = dfs(r, 0);
		}
		
		System.out.println(cnt);
	}

	private static boolean dfs(int r, int c) {
		int nr, nc;
		
		if(c == C - 1) {
			cnt++;
			return true;
		}
		
		for(int i = 0 ; i < 3 ; ++i	) {
			nr = r + dir[i][0];
			nc = c + dir[i][1];
			if(nr >= R || nr < 0 || nc >= C || nc < 0) continue;
			if(map[nr][nc] == '.') {
				map[nr][nc] = (char) (cnt + '0');
				if(DP[nr][nc]) DP[nr][nc] = dfs(nr, nc);
				if(DP[nr][nc]) return true;
				map[nr][nc] = '.'; 
			}
		}
		return false;
	}
}