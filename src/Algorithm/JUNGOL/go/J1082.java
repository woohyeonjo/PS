package Algorithm.JUNGOL.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class J1082 {
	
	static Cell[][] map;
	static boolean[][] visited;
	static Queue<Cell> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int R, C;
	static String ans;
	
	static class Cell{
		int r, c, time;
		char type;
		
		public Cell(int r, int c, char type, int time) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
			this.time = time;
		}
		
		@Override
		public String toString() {
			return this.type + "";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		
		q = new LinkedList<Cell>();
		map = new Cell[R][C];
		visited = new boolean[R][C];
		ans = "";
		Cell start = null;
		
		for(int r = 0 ; r < R ; ++r) {
			char[] line = in.readLine().toCharArray();
			for(int c = 0 ; c < C ; ++c) {
				map[r][c] = new Cell(r, c, line[c], 0);
				if(line[c] == 'S') start = map[r][c];
				if(line[c] == '*') q.offer(map[r][c]);
			}
		}
		
		visited[start.r][start.c] = true;
		q.offer(start);
		
		bfs();
		System.out.println(ans);
	}
	
	private static void bfs() {
		
		int nr, nc;
		while(!q.isEmpty()) {
			Cell cell = q.poll();
			
			if(cell.type == 'D') {
				ans = cell.time + "";
				return;
			}
			
			for(int i = 0 ; i < 4 ; ++i) {
				nr = cell.r + dir[i][0];
				nc = cell.c + dir[i][1];
				if(nr >= R || nr < 0 || nc >= C || nc < 0 || map[nr][nc].type == 'X') continue;
				if(cell.type == '*' && map[nr][nc].type == '.') {
					map[nr][nc].type = '*';
					q.offer(new Cell(nr, nc, '*', cell.time + 1));
				} else if (cell.type == '.' || cell.type == 'S') {
					if(map[nr][nc].type == '*' || visited[nr][nc]) continue;
					visited[nr][nc] = true;
					q.offer(new Cell(nr, nc, map[nr][nc].type, cell.time + 1));
//					System.out.println(nr + "," + nc);
				}
			}
		}
		ans = "impossible";
	}
}
