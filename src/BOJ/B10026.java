package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B10026 {
	static Cell[][] map;
	static boolean[][] visited;
	static Queue<Cell> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	static int N, cnt, dCnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		String[] line;
		
		q = new LinkedList<Cell>();
		map = new Cell[N][N];
		visited = new boolean[N][N];
		
		
		for(int r = 0 ; r < N ; ++r) {
			line = in.readLine().split("");
			for(int c = 0 ; c < N ; ++c) {
				map[r][c] = new Cell(r, c, line[c]);
			}
		}
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				if(!visited[r][c]) {
					q.offer(map[r][c]);
					visited[r][c] = true;
					cnt++;
					bfs();
				}
				if(map[r][c].color.equals("G")) map[r][c].color = "R";
			}
		}
		
		visited = new boolean[N][N];
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				if(!visited[r][c]) {
					q.offer(map[r][c]);
					visited[r][c] = true;
					dCnt++;
					bfs();
				}
			}
		}
		
		
		System.out.println(cnt + " " + dCnt);
		
	}
	
	private static void bfs() {
		
		int nr,nc;
		while(!q.isEmpty()) {
			Cell cell = q.poll();
			
			for(int i = 0 ; i < 4 ; ++i) {
				nr = cell.r + dir[i][0];
				nc = cell.c + dir[i][1];
				
				if(nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc]) continue;
				if(!map[nr][nc].color.equals(cell.color)) continue;
				
				visited[nr][nc] = true;
				q.offer(map[nr][nc]);
			}
			
		}
		
	}

	static class Cell{
		int r, c;
		String color;
		
		public Cell(int r, int c, String color) {
			this.r = r;
			this.c = c;
			this.color = color;
		}
		
		@Override
		public String toString() {
			return color;
		}
	}
}
