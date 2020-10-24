package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B9944_NxM보드완주하기 {

	static char[][] map;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	static int N, M, T;
	static int cell, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = 0;
		
		String input = null;
		while((input = br.readLine()) != null) {
			T++;
			st = new StringTokenizer(input);
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			ans = Integer.MAX_VALUE;
			cell = 0;
			map = new char[N][M];
			visited = new boolean[N][M];
			
			for(int r = 0 ; r < N ; ++r) {
				char[] line = br.readLine().toCharArray();
				for(int c = 0 ; c < M ; ++c) {
					map[r][c] = line[c];
					if(map[r][c] == '.') cell++;
				}
			}
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < M ; ++c) {
					if(map[r][c] == '.') {
						visited[r][c] = true;
						backtracking(r, c, 0, 1);
						visited[r][c] = false;
					}
				}
			}
			
			if(ans == Integer.MAX_VALUE) System.out.println("Case " + T + ": " + -1);
			else System.out.println("Case " + T + ": " + ans);
		}
		
	}

	private static void backtracking(int startR, int startC, int depth, int moves) {
		// 움직인 칸이 전체 빈 칸과 같을 때 종료
		if(moves == cell) {
			ans = ans > depth ? depth : ans;
			return;
		}
		
		for(int d = 0 ; d < 4 ; ++d) {
			int cnt = 0;
			int r = startR;
			int c = startC;
			int nr, nc;
			
			// 한 쪽 방향으로 계속 진행하기 
			while(true) {
				nr = r + dir[d][0];
				nc = c + dir[d][1];
				
				// 다음 칸에 경계를 벗어나거나 이미 지나온 길이거나 장애물이 있을경우 중지 
				if(nr >= N || nr < 0 || nc >= M || nc < 0 ||
				   map[nr][nc] == '*' || visited[nr][nc]) break;
				
				visited[nr][nc] = true;
				cnt++;
				r = nr;
				c = nc;
			}
			
			// 이동한 곳이 시작한 곳과 같으면 (움직이지 못했을 때) 다음 방향으로 넘어감 
			if(r == startR && c == startC) continue;
			// 다음 단계로 넘어가기 
			backtracking(r, c, depth + 1, moves + cnt);
			
			// 지나온 길 되돌아가기
			for(int i = 0 ; i < cnt ; ++i) {
				visited[r][c] = false;
				r = r - dir[d][0];
				c = c - dir[d][1];
			}
		}
	}
	
	private static void print() {
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				if(map[r][c] == '*') System.out.print("*");
				else if(visited[r][c]) System.out.print(".");
				else if(!visited[r][c]) System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
