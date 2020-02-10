package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1953_모의탈주범검거_2 {
	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		@Override
		public String toString() {
			return "(" + r + ", " + c + ")";
		}
	}
	
	static Queue<Node> q;
	static boolean[][] visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] map;
	static int T, N, M, R, C, L, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = stoi(br.readLine());
		for(int t = 1 ; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			R = stoi(st.nextToken());
			C = stoi(st.nextToken());
			L = stoi(st.nextToken());
					
			ans = 0;
			map = new int[N][M];
			visited = new boolean[N][M];
			q = new LinkedList<>();

			for(int r = 0 ; r < N ; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0 ; c < M ; ++c) {
					map[r][c] = stoi(st.nextToken());
				}
			}
			
			visited[R][C] = true;
			q.offer(new Node(R, C));
			
			bfs();
			check();
			
			System.out.println("#" + t + " " + ans);
		}
	
	}
	
	private static void check() {
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				if(visited[r][c]) ans++;
			}
		}
	}

	private static void bfs() {
		int time = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			if(++time == L) return; 

			
			for(int i = 0 ; i < size ; ++i) {
				Node now = q.poll();
				int type = map[now.r][now.c];
				
				for(int d = 0 ; d < 4 ; ++d) {
					int nr = now.r + dir[d][0];
					int nc = now.c + dir[d][1];
					
					if(nr >= N || nr < 0 || nc >= M || nc < 0 || visited[nr][nc] || map[nr][nc] == 0) continue;
					
					int next = map[nr][nc];
					switch(d) {
						// 위 
						case 0:
							if(type == 1 || type == 2 || type == 4 || type == 7) {
								if(next == 1 || next == 2 || next == 5 || next == 6) {
									visited[nr][nc] = true;
									q.offer(new Node(nr, nc));
								}
							}
							break;
						// 아래 
						case 1:
							if(type == 1 || type == 2 || type == 5 || type == 6) {
								if(next == 1 || next == 2 || next == 4 || next == 7) {
									visited[nr][nc] = true;
									q.offer(new Node(nr, nc));
								}
							}
							break;
						// 왼
						case 2:
							if(type == 1 || type == 3 || type == 6 || type == 7) {
								if(next == 1 || next == 3 || next == 4 || next == 5) {
									visited[nr][nc] = true;
									q.offer(new Node(nr, nc));
								}
							}
							break;
						// 오
						case 3:
							if(type == 1 || type == 3 || type == 4 || type == 5) {
								if(next == 1 || next == 3 || next == 6 || next == 7) {
									visited[nr][nc] = true;
									q.offer(new Node(nr, nc));
								}
							}
							break;
					}
//					print();
//					System.out.println();
				}
			}
			
		}
	}
	
	private static void print() {
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				if(visited[r][c]) System.out.print("◼︎︎");
				else System.out.print("◻");
			}
			System.out.println();
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
