package Algorithm.BOJ.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1987 {
	static char[][] map;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int R, C, ans;
	static String alphabet = "";
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		
		map = new char[R][C];
		ans = 0;
		
		for(int r = 0 ; r < R ; ++r) {
			char[] temp = in.readLine().toCharArray();
			for(int c = 0 ; c < C ; ++c) {
				map[r][c] = temp[c];
			}
		}
		alphabet = map[0][0] + "";
		dfs(0, 0, 1);
		
		System.out.println(ans);
		
	}

	private static void dfs(int r, int c, int cnt) {
		
		if(cnt > ans) {
			ans = cnt;
		}
		
		for(int i = 0 ; i < 4 ; ++i) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			
			if(nr >= R || nr < 0 || nc >= C || nc < 0) continue;
			if(!alphabet.contains(map[nr][nc] + "")) {
				alphabet += map[nr][nc];
				dfs(nr, nc, alphabet.length());
				alphabet = alphabet.substring(0, alphabet.length() - 1);
			}
		}
	}

}
