package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B11559_PuyoPuyo {
	
	static class Node {
		int r, c;
		
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static char[][] map;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ans = 0;
		map = new char[12][6];
		
		char[] line = null;
		for(int r = 0 ; r < 12 ; ++r) {
			line = br.readLine().toCharArray();
			for(int c = 0 ; c < 6 ; ++c) { 
				map[r][c] = line[c];
			}
		}
		
		while(pang()) {
			fallDown();
			ans++;
		}
		
		System.out.println(ans);
	}
	
	private static void fallDown() {
		for(int c = 0 ; c < 6 ; ++c) {
			for(int sr = 11 ; sr >= 0 ; --sr) {
				if(map[sr][c] == '.') {
					for(int nr = sr - 1 ; nr >= 0 ; --nr) {
						if(map[nr][c] != '.') {
							map[sr][c] = map[nr][c];
							map[nr][c] = '.';
							break;
						}
					}
				}
			}
		}
	}

	private static boolean pang() {
		boolean flag = false;
		
		for(int r = 0 ; r < 12 ; ++r) {
			for(int c = 0 ; c < 6 ; ++c) {
				if(map[r][c] == '.') continue;
				
				if(check(r, c) >= 4) {
					boom(r, c, map[r][c]);
					flag = true;
				}
			}
		}
		
		return flag;
	}

	private static void boom(int sr, int sc, char color) {
		Queue<Node> q = new LinkedList<>();
		
		map[sr][sc] = '.';
		q.offer(new Node(sr, sc));
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(int d = 0 ; d < 4 ; ++d) {
				int nr = now.r + dir[d][0];
				int nc = now.c + dir[d][1];
				if(nr >= 12 || nr < 0 || nc >= 6 || nc < 0) continue;
				
				if(map[nr][nc] == color) {
					map[nr][nc] = '.';
					q.offer(new Node(nr, nc));
				}
			}
		}
	}

	private static int check(int sr, int sc) {
		int cnt = 1;
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[12][6];
		
		q.offer(new Node(sr, sc));
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(int d = 0 ; d < 4 ; ++d) {
				int nr = now.r + dir[d][0];
				int nc = now.c + dir[d][1];
				if(nr >= 12 || nr < 0 || nc >= 6 || nc < 0 || visited[nr][nc]) continue;
				
				if(map[nr][nc] == map[sr][sc]) {
					cnt++;
					q.offer(new Node(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
		
		return cnt;
	}

	private static void print() {
		for(int r = 0 ; r < 12 ; ++r) {
			for(int c = 0 ; c < 6 ; ++c) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
