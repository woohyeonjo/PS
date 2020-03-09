package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class B2665_미로만들기 {
	
	static class Node{
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static Queue<Node> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] map, visited;
	static int N; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		q = new LinkedList<>();
		visited = new int[N][N];
		map = new int[N][N];
		
		for(int i = 0 ; i < N ; ++i) {
			char[] line = br.readLine().toCharArray();
			for(int j = 0 ; j < N ; ++j) {
				map[i][j] = line[j] - '0';
			}
		}

		for(int r = 0 ; r < N ; ++r) {
			Arrays.fill(visited[r], Integer.MAX_VALUE);
		}
		
		q.offer(new Node(0, 0));
		visited[0][0] = 0;
		
		bfs();
		
		System.out.println(visited[N - 1][N - 1]);
	}

	private static void bfs() {
		while(!q.isEmpty()) {
			Node now = q.poll();

			for(int d = 0 ; d < 4 ; ++d) {
				int nr = now.r + dir[d][0];
				int nc = now.c + dir[d][1];
				if(nr >= N || nr < 0 || nc >= N || nc < 0) continue;
				
				// 검은 방
				if(map[nr][nc] == 0) {
					if(visited[nr][nc] > visited[now.r][now.c] + 1) {
						visited[nr][nc] = visited[now.r][now.c] + 1;
						q.offer(new Node(nr, nc));
					}
				} else {
					if(visited[nr][nc] > visited[now.r][now.c]) {
						visited[nr][nc] = visited[now.r][now.c];
						q.offer(new Node(nr, nc));
					}
				}
			}
		}
	}
}
