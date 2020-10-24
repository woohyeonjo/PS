package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2638_치즈 {
	
	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static Queue<Node> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	static int[][] map;
	static int N, M, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		ans = 0;
		q = new LinkedList<>();
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int r = 0 ; r < N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = stoi(st.nextToken());
			}
		}
		
		do {
			ans++;
			melt();
		} while(reset());
		
		System.out.println(ans);
	}
	
	private static void melt() {
		q.offer(new Node(0, 0));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(int d = 0 ; d < 4 ; ++d) {
				int nr = now.r + dir[d][0];
				int nc = now.c + dir[d][1];
				if(nr >= N || nr < 0 || nc >= M || nc < 0 || visited[nr][nc]) continue;
				
				if(map[nr][nc] == 0) {
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc));
				} else if(map[nr][nc] > 0) {
					map[nr][nc]++;
				}
			}
		}
	}

	private static boolean reset() {
		boolean hasCheeze = false;
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				visited[r][c] = false;
				
				if(map[r][c] > 2) {
					map[r][c] = 0;
				} else if(map[r][c] > 0) {
					hasCheeze = true;
					map[r][c] = 1;
				}
			}
		}
		
		return hasCheeze;
	}
	
	private static void print() {
		for(int r = 0 ; r < N ; ++r) {
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
