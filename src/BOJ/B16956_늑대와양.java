package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16956_늑대와양 {
	
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
	static char[][] map;
	static boolean impossible;
	static int R, C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		q = new LinkedList<>();
		map = new char[R][C];
		visited = new boolean[R][C];
	
		for(int r = 0 ; r < R ; ++r) {
			char[] line = br.readLine().toCharArray();
			for(int c = 0 ; c < C ; ++c) {
				map[r][c] = line[c];
				if(map[r][c] == 'W') {
					visited[r][c] = true;
					q.offer(new Node(r, c));
				}
			}
		}
		
		if(q.size() > 0) bfs();
		
		if(!impossible) {
			System.out.println(1);
			print();
		}
	}
	
	private static void bfs() {
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int d = 0 ; d < 4 ; ++d) {
				int nr = cur.r + dir[d][0];
				int nc = cur.c + dir[d][1];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) continue;
				if(map[nr][nc] == '.') {
					q.offer(new Node(nr, nc));
					visited[nr][nc] = true;
				} else if(map[nr][nc] == 'S') {
					if(map[cur.r][cur.c] == 'W') {
						impossible = true;
						System.out.println(0);
						return;
					}
					map[cur.r][cur.c] = 'D';
					visited[cur.r][cur.c] = true; 
				}
			}
		}
	}

	private static void print() {
		for(int r = 0 ; r < R ; ++r) {
			for(int c = 0 ; c < C ; ++c) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
