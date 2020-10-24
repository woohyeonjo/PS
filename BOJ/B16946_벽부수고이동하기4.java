package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16946_벽부수고이동하기4 {
	
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
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		q = new LinkedList<>();
		
		for(int i = 0 ; i < N ; ++i) {
			char[] line = br.readLine().toCharArray();
			for(int j = 0 ; j < M ; ++j) {
				map[i][j] = line[j] - '0';
			}
		}
		
		for(int i = 0 ; i < N ; ++i) {
			for(int j = 0 ; j < M ; ++j) {
				if(map[i][j] == 0 && !visited[i][j]) {
					visited[i][j] = true;
					q.offer(new Node(i, j));
					bfs();
				}
			}
		}
		
		for(int i = 0 ; i < N ; ++i) {
			for(int j = 0 ; j < M ; ++j) {
				map[i][j] = map[i][j] % 10;
			}
		}
		
		print();
	}
	
	private static void bfs() {
		ArrayList<Node> list = new ArrayList<>();
		int cnt = 1;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int d = 0 ; d < 4 ; ++d) {
				int nr = cur.r + dir[d][0];
				int nc = cur.c + dir[d][1];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;
				
				visited[nr][nc] = true;

				if(map[nr][nc] != 0) {
					list.add(new Node(nr, nc));
				} else {
					q.offer(new Node(nr, nc));
					cnt++;
				}
			}
		}
		
		for(Node node : list) {
			visited[node.r][node.c] = false;
			map[node.r][node.c] += cnt; 
		}
	}

	private static void print() {
		for(int i = 0 ; i < N ; ++i) {
			for(int j = 0 ; j < M ; ++j) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
