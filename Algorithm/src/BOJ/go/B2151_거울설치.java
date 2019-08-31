package BOJ.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class B2151_거울설치 {
	
	static class Beam{
		int r, c, cnt;

		public Beam(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	static Queue<Beam> q;
	static ArrayList<Beam> mirrors = new ArrayList<>();
	static char[][] room;
	static boolean[][] visited;
	static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0, 1}};
	static Beam start;
	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		q = new LinkedList<>();
		room = new char[N][N];
		visited = new boolean[N][N];
		
		char[] line;
		for(int r = 0 ; r < N ; ++r) {
			line = br.readLine().toCharArray();
			for(int c = 0 ; c < N ; ++c) {
				room[r][c] = line[c];
				if(line[c] == '#') start = new Beam(r, c, 0);
			}
		}
		visited[start.r][start.c] = true;
		q.offer(start);
		bfs();
		System.out.println(ans);
	}

	private static void bfs() {
		int nr, nc;
		Beam beam;
		while(!q.isEmpty()) {
			init();
			beam = q.poll();
			for(int i = 0 ; i < 4 ; ++i) {
				nr = beam.r;
				nc = beam.c;
				while(true) {
					nr += dir[i][0];
					nc += dir[i][1];
					
					if(nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc] ||room[nr][nc] == '*') break;
					visited[nr][nc] = true;
					if(room[nr][nc] == '#') {
						ans = beam.cnt;
						return;
					} else if (room[nr][nc] == '!') {
						room[nr][nc] = '%';
						q.offer(new Beam(nr, nc, beam.cnt + 1));
						continue;
					} 
				}
			}
			
		}
	}
	
	private static void init() {
		mirrors.add(q.peek());
		visited = new boolean[N][N];
		for(Beam beam : mirrors) {
			visited[beam.r][beam.c] = true;
		}
	}
}
