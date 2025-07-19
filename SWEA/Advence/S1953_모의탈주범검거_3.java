package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1953_모의탈주범검거_3 {
	
	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}

	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<Node> q;
	static int[][] map;
	static boolean[][] visited;
	static int N, M, SR, SC, L, T, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			SR = Integer.parseInt(st.nextToken());
			SC = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			q = new LinkedList<>();
			map = new int[N][M];
			visited = new boolean[N][M];
			ans = 1;
			
			for(int r = 0 ; r < N ; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0 ; c < M ; ++c) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs();
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void bfs() {
		int time = 0;
		
		q.offer(new Node(SR, SC));
		visited[SR][SC] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			if(++time >= L) return;
			
			for(int i = 0 ; i < size ; ++i) {
				Node cur = q.poll();
				int type = map[cur.r][cur.c];
				
				for(int d = 0 ; d < 4 ; ++d) {
					int nr = cur.r + dir[d][0];
					int nc = cur.c + dir[d][1];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if(visited[nr][nc] || map[nr][nc] == 0) continue;
					
					int next = map[nr][nc];
					
					switch(d) {
					case 0:
						if(type == 1 || type == 2 || type == 4 || type == 7) {
							if(next == 1 || next == 2 || next == 5 || next == 6) {
								visited[nr][nc] = true;
								q.offer(new Node(nr, nc));
								ans++;
							}
						}
						break;
					case 1:
						if(type == 1 || type == 2 || type == 5 || type == 6) {
							if(next == 1 || next == 2 || next == 4 || next == 7) {
								visited[nr][nc] = true;
								q.offer(new Node(nr, nc));
								ans++;
							}
						}
						break;
					case 2:
						if(type == 1 || type == 3 || type == 6 || type == 7) {
							if(next == 1 || next == 3 || next == 4 || next == 5) {
								visited[nr][nc] = true;
								q.offer(new Node(nr, nc));
								ans++;
							}
						}
						break;
					case 3:
						if(type == 1 || type == 3 || type == 4 || type == 5) {
							if(next == 1 || next == 3 || next == 6 || next == 7) {
								visited[nr][nc] = true;
								q.offer(new Node(nr, nc));
								ans++;
							}
						}
						break;
					}
				}
			}
		}
		
	}
}
