package BOJ.go;

import java.util.PriorityQueue;
import java.util.Scanner;

public class B17142_연구소3 {
	
	static class Cell implements Comparable<Cell> {
		int r, c;
		int time;
		
		public Cell(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}

		@Override
		public int compareTo(Cell o) {
			return this.time - o.time;
		}
	}
	
	static final String WALL = "-";
	static final String NON_ACTIVE = "*";
	static final String ACTIVE = "0";
	
	static int[][] lab;
	static String[][] lab_copied;
	static Cell[] selected;
	static boolean[][] visited;
	static PriorityQueue<Cell> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, M, ans, current;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		lab = new int[N][N];
		visited = new boolean[N][N];
		
		q = new PriorityQueue<>();
		selected = new Cell[M];
		ans = Integer.MAX_VALUE;
		current = Integer.MIN_VALUE;
		
		for(int r = 0; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c) {
				lab[r][c] = sc.nextInt();
			}
		}
		
		select(0, 0, 0);
		System.out.println(ans);
		
		
	}

	private static void select(int sr, int sc, int cnt) {
		if(cnt == M) {
			copy();
			for(int i = 0 ; i < M ; ++i) q.offer(new Cell(selected[i].r, selected[i].c, selected[i].time));
			bfs();
			return;
		}
		
		boolean flag = true;
		
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c){
				if(flag) {
					r = sr;
					c = sc;
					flag = false;
				}
				if(lab[r][c] == 2 && !visited[r][c]){
					visited[r][c] = true;
					selected[cnt] = new Cell(r, c, 0);
					select(r, c, cnt + 1);
					visited[r][c] = false;
				}
			}
		}
	}
	
	private static void bfs() {
		while(!q.isEmpty()){
			Cell cell = q.poll();
			
			int nr, nc;
			
			for(int i = 0 ; i < 4 ; ++i){
				nr = cell.r + dir[i][0];
				nc = cell.c + dir[i][1];
				
				if(nr > N || nr < 0 || nc > N || nc < 0 || visited[nr][nc]) continue;
				if(lab[nr][nc] == )) lab_copied
			}
		}
		
	}

	private static void copy(){
		visited = new boolean[N][N];
		
		for(int r = 0; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c) {
				lab_copied[r][c] = lab[r][c] + "";
			}
		}
	}
}
