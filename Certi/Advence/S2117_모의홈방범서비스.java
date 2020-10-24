package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S2117_모의홈방범서비스 {

	static class Node {
		int r, c;
		
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static Queue<Node> q;
	static boolean[][] visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] map;
	static int N, M, T, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ans = 0;
			
			q = new LinkedList<>();
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for(int r = 0 ; r < N ; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0 ; c < N ; ++c) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < N ; ++c) {
					init();
					bfs(r, c);
				}
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}
	
	private static void bfs(int r, int c) {
		q.offer(new Node(r, c));
		visited[r][c] = true;
		
		int K = 1;
		int house = map[r][c] == 1 ? 1 : 0;
		
		if(getOperationCost(K) <= house * M) {
			ans = K > ans ? K : ans;
		}
		
		while(!q.isEmpty()) {
			int size = q.size();
			K++;
			
			for(int i = 0 ; i < size ; ++i) {
				Node cur = q.poll();
				
				for(int d = 0 ; d < 4 ; ++d) {
					int nr = cur.r + dir[d][0];
					int nc = cur.c + dir[d][1];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
					
					if(map[nr][nc] == 1) house++;
					
					q.offer(new Node(nr, nc));
					visited[nr][nc] = true;
				}
			}
			if(getOperationCost(K) <= house * M) {
				ans = house > ans ? house : ans;
			}
		}
	}
	
	private static int getOperationCost(int k) {
		return k * k + (k - 1) * (k - 1);
	}

	private static void init() {
		q.clear();
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				visited[r][c] = false;
			}
		}
	}
}
