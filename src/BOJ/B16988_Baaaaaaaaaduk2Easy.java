package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16988_Baaaaaaaaaduk2Easy {

	static class Node {
		int r, c;
		
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[][] map;
	static boolean[][] visited;
	static int N, M, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int r = 0 ; r < N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = stoi(st.nextToken());
			}
		}
		
		placement(0, 0);
		
		System.out.println(ans);
	}
	
	private static void placement(int cnt, int limit) {
		if(cnt == 2) {
			// 죽은 돌 세어보기
			initVisited();
			int dead = 0;
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < M ; ++c) {
					if(map[r][c] == 2 && !visited[r][c]) {
						// 돌을 놓았을 때 여러 그룹이 동시에 죽을 수 있다. 
						dead += countDeadBlackStone(r, c);
					}
				}
			}
			
			ans = dead > ans ? dead : ans;
			
			return;
		}
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				// 이전 셀에 대한 값 보다 큰 셀 부터 시작한다.
				if(r * 100 + c < limit) continue;
				
				if(map[r][c] == 0) {
					map[r][c] = 1;
					placement(cnt + 1, r * 100 +c);
					map[r][c] = 0;
				}
			}
		}
		
		
	}
	
	private static int countDeadBlackStone(int r, int c) {
		Queue<Node >q = new LinkedList<>();
		int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		int cnt = 1;
		boolean isDead = true;

		q.offer(new Node(r, c));
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(int i = 0 ; i < 4 ; ++i) {
				int nr = now.r + dir[i][0];
				int nc = now.c + dir[i][1];
				if(nr >= N || nr < 0 || nc >= M || nc < 0 || visited[nr][nc]) continue;
				
				if(map[nr][nc] == 0) isDead = false;
				else if(map[nr][nc] == 2) {
					q.offer(new Node(nr, nc));
					visited[nr][nc] = true;
					cnt++;
				}
			}
		}
		
		if(isDead) {
			return cnt;
		}
		return 0;
	}
	
	private static void initVisited() {
		for(int r = 0; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				visited[r][c] = false;
			}
		}
	}

	private static void print() {
		for(int r = 0; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
