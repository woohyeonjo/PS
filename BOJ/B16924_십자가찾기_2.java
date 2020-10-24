package BOJ;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B16924_십자가찾기_2 {
	
	static class Star {
		int r, c, len;
		
		public Star(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public Star(int r, int c, int len) {
			this.r = r;
			this.c = c;
			this.len = len;
		}
		
	}
	
	static Queue<Star> q;
	static ArrayList<Star> stars;
	static int[][] map;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	static int N, M, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		sc.nextLine();
		
		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		stars = new ArrayList<>();
		q = new LinkedList<>();
		
		char[] line;
		for(int r = 1 ; r <= N ; ++r) {
			line = sc.nextLine().toCharArray();
			for(int c = 1; c <= M ; ++c) {
				if(line[c - 1] == '.') {
					map[r][c] = 0;
					visited[r][c] = true;
				}
				else {
					map[r][c] = 1;
				}
			}
		}
		
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1; c <= M ; ++c) {
				if(map[r][c] == 1) {
					q.offer(new Star(r, c, 1));
					bfs();
				}
			}
		}
		
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1; c <= M ; ++c) {
				if(!visited[r][c]) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(stars.size());
		for(Star s : stars) System.out.println(s.r + " " + s.c + " " + s.len);
	}

	private static void bfs() {
		int nr, nc;
		while(!q.isEmpty()) {
			Star star = q.poll();
			
			for(int i = 0 ; i < 4 ; ++i) {
				nr = star.r + dir[i][0] * star.len;
				nc = star.c + dir[i][1] * star.len;
				if(nr > N || nr < 1 || nc > M || nr < 1 || map[nr][nc] == 0) {
					if(star.len != 1) {
						stars.add(new Star(star.r, star.c, star.len - 1));
					}
					return;
				}
			}
			for(int i = 0 ; i < 4 ; ++i) {
				nr = star.r + dir[i][0] * star.len;
				nc = star.c + dir[i][1] * star.len;
				visited[nr][nc] = true;
			}
			visited[star.r][star.c] = true; 
			star.len++;
			q.offer(star);
		}
	}
}
