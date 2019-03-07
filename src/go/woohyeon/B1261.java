package go.woohyeon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1261 {
	
	static int[][] map;
	static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	
	static int N, M, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp;
		temp = in.readLine().split(" ");
		M = Integer.parseInt(temp[0]);
		N = Integer.parseInt(temp[1]);
		
		map = new int[N][M];
		visited = new boolean[N][M];
		ans = Integer.MAX_VALUE;
		
		for(int n = 0 ; n < N ; ++n) {
			temp = in.readLine().split("");
			for(int m = 0 ; m < M ; ++m) {
				map[n][m] = Integer.parseInt(temp[m]);
			}
		}
		visited[0][0] = true;
		dfs(0, 0, 0);
		System.out.println(ans);
	}

	private static void dfs(int n, int m, int cnt) {
		if(visited[N - 1][M - 1]) {
			ans = cnt;
			return;
		}
		
		int nextN, nextM;
		for(int i = 0 ; i < 4 ; ++i) {
			nextN = n + direction[i][0];
			nextM = m + direction[i][1];
			
			if(nextN >= 0 && nextN < N && nextM >= 0 && nextM < M) {
				if(!visited[nextN][nextM] && cnt + map[nextN][nextM] < ans) {
					visited[nextN][nextM] = true;
					if(map[nextN][nextM] == 1) dfs(nextN, nextM, cnt + 1);
					else dfs(nextN, nextM, cnt);
					visited[nextN][nextM] = false;
				}
			}
		}
	}
		
		
}
