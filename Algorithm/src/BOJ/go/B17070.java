package BOJ.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B17070 {
	static Cell[][] map;
	static int[][] dir = {{0, 1}, {1, 1}, {1, 0}};
	static Queue<Cell> q;
	static int N, ans;
	
	static class Cell{
		int r, c, type, dir;

		public Cell(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}
		
		public Cell(int r, int c, int type, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
			this.dir = dir;
		}
		
		@Override
		public String toString() {
			return type + "";
		}
	}
	
public static void main(String[] args) throws NumberFormatException, IOException {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	String[] line;
	N = Integer.parseInt(in.readLine());
	
	map = new Cell[N + 1][N + 1];
	q = new LinkedList<Cell>();
	ans = 0;
	
	for(int r = 1 ; r <= N ; ++r) {
		line = in.readLine().split(" ");
		for(int c = 1 ; c <= N ; ++c) {
			map[r][c] = new Cell(r, c, Integer.parseInt(line[c - 1]));
		}
	}
	
	map[1][2].dir = 0;
	q.offer(map[1][2]);
	bfs();
	
	System.out.println(ans);
}

private static void bfs() {
	
	int nr, nc;
	while(!q.isEmpty()) {
		
		Cell tail = q.poll();
		Cell head = null;
		
		for(int i = 0 ; i < 3 ; ++i) {
			nr = tail.r + dir[i][0];
			nc = tail.c + dir[i][1];
			
			// 오른쪽
			if(i == 0) {
				if(nr > N || nr < 1 || nc > N || nc < 1 || tail.dir == 2) continue;
				if(map[nr][nc].type == 1) continue;
				
			// 대각선 아래	
			} else if(i == 1) {
				if(nr > N || nr < 1 || nc > N || nc < 1) continue;
				if(map[nr][nc].type == 1 || map[nr][nc - 1].type == 1 || map[nr - 1][nc].type == 1) continue;
				
			// 아래
			} else if(i == 2) {
				if(nr > N || nr < 1 || nc > N || nc < 1 || tail.dir == 0) continue;
				if(map[nr][nc].type == 1) continue;
			}
			if(nr == N && nc == N) {
				ans++;
				continue;
			}
			head = new Cell(nr, nc, 0, i);
			q.offer(head);
			
		}
	}
}
}
