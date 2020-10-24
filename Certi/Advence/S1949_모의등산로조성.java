package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1949_모의등산로조성 {
	
	static class Node{
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static Queue<Node> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] map;
	static int max;
	static int T, N, K, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = stoi(br.readLine());
		for(int t = 1 ; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			K = stoi(st.nextToken());
			
			max = Integer.MIN_VALUE;
			ans = Integer.MIN_VALUE;
			map = new int[N][N];
			q = new LinkedList<>();
			
			for(int r = 0 ; r < N ; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0 ; c < N ; ++c) {
					map[r][c] = stoi(st.nextToken());
					max = map[r][c] > max ? map[r][c] : max;
				}
			}
			
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < N ; ++c) {
					for(int k = 1 ; k <= K ; ++k) {
						map[r][c] -= k;
						findRoad();
						map[r][c] += k;
					}
				}
			}
			System.out.println("#" + t + " " + ans);
		}
		
	}
	
	private static void findRoad() {
		for(int r = 0; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				if(map[r][c] == max) {
					q.offer(new Node(r, c));
					int length = bfs();
					
					ans = length > ans ? length : ans;
				}
			}
		}
		
	}

	private static int bfs() {
		int length = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			length++;
			
			for(int i = 0 ; i < size ; ++i) {
				Node now = q.poll();

				for(int d = 0 ; d < 4 ; ++d) {
					int nr = now.r + dir[d][0];
					int nc = now.c + dir[d][1];
					
					if(nr >= N || nr < 0 || nc >= N || nc < 0) continue;
					if(map[nr][nc] < map[now.r][now.c]) {
						q.offer(new Node(nr, nc));
					}
				}
			}
		}
		
		return length;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
