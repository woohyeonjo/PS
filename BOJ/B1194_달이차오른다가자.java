package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1194_달이차오른다가자 {
	
	static class Node {
		int r, c, k;
		
		Node(int r, int c, int k){
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}
	
	static Queue<Node> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char[][] map;
	static boolean[][][] visited;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		q = new LinkedList<>();
		map = new char[N][M];
		visited = new boolean[N][M][64];
		
		for(int r = 0 ; r < N ; ++r) {
			char[] line = br.readLine().toCharArray();
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = line[c];
				
				if(map[r][c] == '0') {
					q.offer(new Node(r, c, 0));
					visited[r][c][0] = true;
					map[r][c] = '.';
				}
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		int time = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			time++;
			
			for(int i = 0 ; i < size ; ++i) {
				Node cur = q.poll();
				int r = cur.r;
				int c = cur.c;
				int k = cur.k;
				
				for(int d= 0 ; d < 4 ; ++d) {
					int nr = r + dir[d][0];
					int nc = c + dir[d][1];
					int nk = k;
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if(visited[nr][nc][k] || map[nr][nc] == '#') continue;
					
					if(map[nr][nc] == '1') return time;
					
					else if(map[nr][nc] >= 'a' && map[nr][nc] <= 'z') {
						int ck = 1 << (map[nr][nc] - 'a');
						if((k & ck) != ck) nk |= ck;
					} else if(map[nr][nc] >= 'A' && map[nr][nc] <= 'Z') {
						int cd = 1 << (map[nr][nc] - 'A');
						if((k & cd) != cd) continue;
					}

					q.offer(new Node(nr, nc, nk));
					visited[nr][nc][k] = true;
				}
			}
		}
		return -1;
	}
}
