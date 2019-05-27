package Algorithm.SWEA.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S4613 {
	static char[][] map;
	static int T, N, M, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line;
		
		T = Integer.parseInt(in.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			line = in.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			M = Integer.parseInt(line[1]);
			map = new char[N][M];
			ans = Integer.MAX_VALUE;
			
			char[] cLine;
			for(int r = 0 ; r < N ; ++r) {
				cLine = in.readLine().toCharArray();
				for(int c = 0; c < M; ++c) {
					map[r][c] = cLine[c];
				}
			}
			
			for(int r = 1 ; r < N - 1 ; ++r) {
				ans = Math.min(ans, find(r));
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static int find(int bLine) {
		int result = Integer.MAX_VALUE;
		
		for(int b = bLine ; b < N - 1  ; ++b) {
			// bLine ~ b 가 blue 생각하고 
			int cnt = 0;
			for(int r = 0 ; r < bLine ; ++r) {
				for(int c = 0 ; c < M ; ++c) {
					if(map[r][c] != 'W') cnt++;
				}
			}
			
			for(int r = bLine ; r <= b ; ++r) {
				for(int c = 0 ; c < M ; ++c) {
					if(map[r][c] != 'B') cnt++;
				}
			}
			
			for(int r = b + 1 ; r < N ; ++r) {
				for(int c = 0 ; c < M ; ++c) {
					if(map[r][c] != 'R') cnt++;
				}
			}
			result = Math.min(cnt, result);
		}
		return result;
	}
}
