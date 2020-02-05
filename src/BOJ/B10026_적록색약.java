package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B10026_적록색약 {
	
	static class Node{
		int r, c;
		
		Node (int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static Queue<Node> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char[][] map;
	static boolean[][] visited;
	static int N;
	static int sector1, sector2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		q = new LinkedList<>();
		map = new char[N][N];
		visited = new boolean[N][N];
		sector1 = 0;
		sector2 = 0;
		
		for(int r = 0 ; r < N ; ++r) {
			char[] line = br.readLine().toCharArray();
			for(int c = 0 ; c < N ; ++c) {
				map[r][c] = line[c];
			}
		}
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				if(!visited[r][c]) {
					sector1++;
					visited[r][c] = true;
					q.offer(new Node(r, c));
					bfsForNormal();
				}
			}
		}
		
		visited = new boolean[N][N];
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				if(!visited[r][c]) {
					sector2++;
					visited[r][c] = true;
					q.offer(new Node(r, c));
					bfsForColorBlind();
				}
			}
		}
		System.out.println(sector1 + " " + sector2);
	}

	private static void bfsForNormal() {
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			for(int i = 0 ; i < 4 ; ++i) {
				int nr = node.r + dir[i][0];
				int nc = node.c + dir[i][1];
				if(nr < N && nr >= 0 && nc < N && nc >= 0 && !visited[nr][nc]) {
					if(map[node.r][node.c] == map[nr][nc]) {
						q.offer(new Node(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
		}
	}

	private static void bfsForColorBlind() {
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			for(int i = 0 ; i < 4 ; ++i) {
				int nr = node.r + dir[i][0];
				int nc = node.c + dir[i][1];
				if(nr < N && nr >= 0 && nc < N && nc >= 0 && !visited[nr][nc]) {
					if(map[node.r][node.c] == 'B' && map[nr][nc] == 'B') {
						q.offer(new Node(nr, nc));
						visited[nr][nc] = true;
					} else if(map[node.r][node.c]!= 'B' && map[nr][nc] != 'B') {
						q.offer(new Node(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
		}
	}
	
	
}
