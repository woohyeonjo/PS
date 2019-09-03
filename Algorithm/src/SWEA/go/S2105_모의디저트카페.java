package SWEA.go;

import java.util.HashSet;
import java.util.Scanner;

public class S2105_모의디저트카페 {
	
	static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = {{1, 1}, {1,-1}, {-1, -1}, {-1, 1}};
	static HashSet<Integer> set;
	static Node start;
	static int T, N, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			N = sc.nextInt();
			
			map = new int[N][N];
			visited = new boolean[N][N];
			set = new HashSet<>();
			ans = 0;
			
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < N ; ++c) {
					map[r][c] = sc.nextInt();
				}
			}
			
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < N ; ++c) {
					if(r == 0 && c == 0) continue;
					else if(r == N - 1 && c == N - 1) continue;
					else if(r == 0 && c == N - 1) continue;
					else if(r == N - 1 && c == 0) continue;
					
					start = new Node(r, c);
					dfs(start, 0);
				}
			}
			if(ans < 4) ans = -1;
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void dfs(Node node, int d) {
		
		if(d == 3 && node.r == start.r && node.c == start.c) {
			int current = set.size();
			ans = current > ans ? current : ans;
			return;
		}
		
		int nr, nc;
		
		for(int i = d ; i < 4 ; ++i) {
			nr = node.r + dir[i][0];
			nc = node.c + dir[i][1];
			if(nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc]) continue;
			if(set.contains(map[nr][nc])) continue;
			
			visited[nr][nc] = true;
			set.add(map[nr][nc]);
			dfs(new Node(nr, nc), i);
			set.remove(map[nr][nc]);
			visited[nr][nc] = false;
		}
		
	}
}
