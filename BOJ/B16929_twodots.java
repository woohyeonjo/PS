package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16929_twodots {

	static char[][] map;
	static boolean[][] visited;
	static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		map = new char[N][M];
		
		for(int i = 0 ; i < N ; ++i) {
			char[] line = br.readLine().toCharArray();
			for(int j = 0 ; j < M ; ++j) {
				map[i][j] = line[j];
			}
		}
		
		// 모든 시작위치에서 DFS 실시 
		for(int i = 0 ; i < N ; ++i) {
			for(int j = 0 ; j < M ; ++j) {
				visited = new boolean[N][M];
				if(dfs(i, j, i, j, 1)) {
					System.out.println("Yes");
					return;
				};
			}
		}
		System.out.println("No");
	}

	private static boolean dfs(int startR, int startC, int r, int c, int depth) {
		
		visited[r][c] = true;
		
		for(int i = 0 ; i < 4 ; ++i) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			// Map을 벗어나는 경우 체크 
			if(nr >= 0 && nr < N && nc >= 0 && nc < M) {
				// 시작점으로 돌아온 경우 적어도 4개의 정점은 거쳐야한다. 
				if(visited[nr][nc] && nr == startR && nc == startC && depth >= 4) {
					return true;
				}
				// 방문하지 않았고 전 정점의 색과 같은 경우에만 진행 가능 
				if(!visited[nr][nc] && map[r][c] == map[nr][nc]) {
					if(dfs(startR, startC, nr, nc, depth + 1)) return true;
					visited[nr][nc] = false;
				}
			}
		}
		
		return false;
	}
}
