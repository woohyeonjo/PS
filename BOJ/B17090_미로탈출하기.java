package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17090_미로탈출하기 {

	static class Node{
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] U = { -1, 0 };
	static int[] D = { 1, 0 };
	static int[] R = { 0, 1 };
	static int[] L = { 0, -1 };

	static Queue<Node> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	static char[][] map;
	static int N, M, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		q = new LinkedList<>();
		ans = 0;
		visited = new boolean[N][M];
		map = new char[N][M];

		for (int r = 0; r < N; ++r) {
			char[] line = br.readLine().toCharArray();
			for (int c = 0; c < M; ++c) {
				map[r][c] = line[c];
			}
		}
		
		// 탈출구 찾기
		// 꼭지점
		if(map[0][0] == 'L' || map[0][0] == 'U') q.offer(new Node(0, 0));
		if(map[0][M - 1] == 'R' || map[0][M - 1] == 'U') q.offer(new Node(0, M - 1));
		if(map[N - 1][0] == 'L' || map[N - 1][0] == 'D') q.offer(new Node(N - 1, 0));
		if(map[N - 1][M - 1] == 'R' || map[N - 1][M - 1] == 'D') q.offer(new Node(N - 1, M - 1));
		
		// 모서리 
		for(int c = 1 ; c < M - 1 ; ++c) {
			if(map[0][c] == 'U') q.offer(new Node(0, c));
			if(map[N - 1][c] == 'D') q.offer(new Node(N - 1, c));
		}
		
		for(int r = 1 ; r < N - 1 ; ++r) {
			if(map[r][0] == 'L') q.offer(new Node(r, 0));
			if(map[r][M - 1] == 'R') q.offer(new Node(r, M - 1));
		}
		
		ans = q.size();

		// 탈출구가 있을 때만 BFS를 수행한다. 
		if(ans != 0) bfs();
		
		System.out.println(ans);
	}

	private static void bfs() {
		// 탈출구 모두 방문체크 
		for(Node n : q) {
			visited[n.r][n.c] = true;
		}
		
		while(!q.isEmpty()) {
			Node now = q.poll();

			// 탈출구부터 사방탐색 
			for(int i = 0 ; i < 4 ; ++i) {
				int nr = now.r + dir[i][0];
				int nc = now.c + dir[i][1];
				
				// 탈출구부터 역추적하기 때문에 다시 나가는 것을 고려하지 않는다. 
				if(nr >= N || nr < 0 || nc >= M || nc < 0 || visited[nr][nc]) continue;
				
				// 사방탐색한 곳에서 적혀있는 명령어 대로 다시 이동
				int nnr = nr, nnc = nc;
				switch(map[nr][nc]) {
					case 'U':
						nnr += U[0];
						nnc += U[1];
						break;
					case 'D':
						nnr += D[0];
						nnc += D[1];
						break;
					case 'R':
						nnr += R[0];
						nnc += R[1];
						break;
					case 'L':
						nnr += L[0];
						nnc += L[1];
						break;
				}
				
				// 이동한 곳이 이전 지점이라면 탈출경로 
				if(nnr == now.r && nnc == now.c) {
					ans++;
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc));
				}
			}
		}
	}
}
