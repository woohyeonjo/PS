package BOJ.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B14889 {
	static int[][] map;
	static boolean[] visited;
	static int[] start;
	static int[] link;
	static int gap;
	static int N, ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line;
		N = Integer.parseInt(in.readLine());
		visited = new boolean[N];
		map = new int[N][N];
		start = new int[N / 2];
		link = new int[N / 2];
		gap = 0; ans = Integer.MAX_VALUE;
		
		for(int r = 0; r < N ; ++r) {
			line = in.readLine().split(" ");
			for(int c = 0 ; c < N ; ++c) {
				map[r][c] = Integer.parseInt(line[c]);
			}
		}
		
		dfs(0, 0);
		
		System.out.println(ans);
	}
	
	private static void dfs(int player, int select) {
		
		if(player == N / 2) {
			int idx = 0;
			for(int i = 0 ; i < N ; ++i) {
				if(visited[i]) continue;
				link[idx] = i;
				idx++;
			}
			gap = calc();
			ans = ans > gap ? gap : ans;
			return;
		}
		
		for(int i = select; i < N ; ++i) {
			if(!visited[i]) {
				visited[i] = true;
				start[player] = i;
				dfs(player + 1, i);
				visited[i] = false;
			}
		}
	}

	private static int calc() {
		int startSum = 0;
		int linkSum = 0;
		
		for(int i = 0 ; i < N / 2 ; ++i) {
			for(int j = i + 1 ; j < N / 2 ; ++j) {
				startSum += map[start[i]][start[j]] + map[start[j]][start[i]];
				linkSum += map[link[i]][link[j]] + map[link[j]][link[i]];
			}
		}
		return Math.abs(startSum - linkSum);
	}
}
