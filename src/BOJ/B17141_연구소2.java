package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17141_연구소2 {

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
	static int N, M, blank, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		blank = 0;
		ans = Integer.MAX_VALUE;
		q = new LinkedList<>();
		map = new int[N][N];
		
		for(int r = 0 ; r < N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < N ; ++c) {
				map[r][c] = stoi(st.nextToken());
				if(map[r][c] == 0 || map[r][c] == 2) blank++;
			}
		}
		
		placement(0, 0);
		
		if(ans == Integer.MAX_VALUE) ans = -1;
		System.out.println(ans);
	}
	
	private static void placement(int cnt, int pos) {
		
		if(cnt == M) {
			visited = new boolean[N][N];
			
			for(int i = 0 ; i < N ; ++i) {
				for(int j = 0 ; j < N ; ++j) {
					if(map[i][j] == 3) {
						q.offer(new Node(i, j));
						visited[i][j] = true;
					}
				}
			}
			
			spread();
			return;
		}
		if(pos == N * N) return;

		int r = pos / N;
		int c = pos % N;
		
		if(map[r][c] == 2) {
			map[r][c] = 3;
			placement(cnt + 1, pos + 1);
			map[r][c] = 2;
		}
		placement(cnt, pos + 1);
	}

	private static void spread() {
		int time = 0;
		int virus = M;
		
		while(!q.isEmpty()) {
			int size = q.size();
			time++;
			
			for(int i = 0 ; i < size ; ++i) {
				Node now = q.poll();
				
				for(int d = 0 ; d < 4 ; ++d) {
					int nr = now.r + dir[d][0];
					int nc = now.c + dir[d][1];
					if(nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc]) continue;
					
					if(map[nr][nc] == 0 || map[nr][nc] == 2) {
						visited[nr][nc] = true;
						virus++;
						q.offer(new Node(nr, nc));
					}
				}
			}
		}
		
		if(virus == blank) {
			ans = ans > time - 1 ? time - 1 : ans;
		}
	}
	
	private static void print() {
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
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
