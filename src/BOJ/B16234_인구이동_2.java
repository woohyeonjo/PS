package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16234_인구이동_2 {
	
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
	static int[][] population;
	static int[][] union;
	static HashMap<Integer, Integer> map;
	static int N, L, R;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		q = new LinkedList<>();
		population = new int[N][N];
		
		for(int r = 0 ; r < N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < N ; ++c) {
				population[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		while(true) {
			open();
			if(!move()) break;
			cnt++;
		}
		
		System.out.println(cnt);
	}

	private static boolean move() {
		boolean isMoved = false;
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				int u = union[r][c];
				int p = map.get(u);
				
				if(population[r][c] == p) continue;
				isMoved = true;
				population[r][c] = p;
			}
		}
	
		return isMoved;
	}

	private static void open() {
		map = new HashMap<>();
		visited = new boolean[N][N];
		union = new int[N][N];

		int num = 1;
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				if(visited[r][c]) continue;
				bfs(num++, r, c);
			}
		}
	}

	private static void bfs(int num, int r, int c) {
		int total = population[r][c];
		int cnt = 1;

		q.offer(new Node(r, c));
		visited[r][c] = true;
		union[r][c] = num;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int d = 0 ; d < 4 ; ++d) {
				int nr = cur.r + dir[d][0];
				int nc = cur.c + dir[d][1];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
				
				int gap = Math.abs(population[cur.r][cur.c] - population[nr][nc]);
				
				if(gap >= L && gap <= R) {
					union[nr][nc] = num;
					total += population[nr][nc];
					cnt++;
					
					q.offer(new Node(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
		map.put(num, total / cnt);
	}
}
