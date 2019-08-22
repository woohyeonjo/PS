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
	
	static final int WALL = -1;
	static final int NON_ACTIVE = -2;
	static final int ACTIVE = -3;
	
	static int[][] lab;
	static int[][] lab_copied;
	static Cell[] selectedCell;
	static boolean[][] visited;
	static boolean[][] selected;
	static PriorityQueue<Cell> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, M, ans, current;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		lab = new int[N][N];
		visited = new boolean[N][N];
		selected = new boolean[N][N];
		
		q = new PriorityQueue<>();
		selectedCell = new Cell[M];
		ans = Integer.MAX_VALUE;
		current = Integer.MIN_VALUE;
		
		for(int r = 0; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c) {
				lab[r][c] = sc.nextInt();
			}
		}
		
		select(0, 0, 0);
		if(ans == Integer.MAX_VALUE) ans = -1;
		System.out.println(ans);
		
		
	}

	private static void select(int sr, int sc, int cnt) {
		if(cnt == M) {
			copy();
			for(int i = 0 ; i < M ; ++i){
				lab_copied[selectedCell[i].r][selectedCell[i].c] = ACTIVE;
				visited[selectedCell[i].r][selectedCell[i].c] = true;
				q.offer(new Cell(selectedCell[i].r, selectedCell[i].c, selectedCell[i].time));
			}
			bfs();
			check();
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
				if(lab[r][c] == 2 && !selected[r][c]){
					selectedCell[cnt] = new Cell(r, c, 0);
					select(r, c + 1, cnt + 1);
				}
			}
		}
	}
	
	private static void check() {
		int max = 0;
		
		for(int r = 0; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c) {
				if(lab_copied[r][c] < 0) continue;
				if(lab_copied[r][c] == 0) return;
				max = lab_copied[r][c] > max ? lab_copied[r][c] : max;
			}
		}
		current = max;
		ans = current < ans ? current : ans;
	}

	private static void bfs() {
		while(!q.isEmpty()){
			Cell cell = q.poll();
			
			int nr, nc;
			
			for(int i = 0 ; i < 4 ; ++i){
				nr = cell.r + dir[i][0];
				nc = cell.c + dir[i][1];
				
				if(nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc]) continue;
				if(lab_copied[nr][nc] == WALL) continue;
				if(lab_copied[nr][nc] == NON_ACTIVE){
					visited[nr][nc] = true;
					q.offer(new Cell(nr, nc, cell.time + 1));
					continue;
				}
				
				visited[nr][nc] = true;
				lab_copied[nr][nc] = cell.time + 1;
				q.offer(new Cell(nr, nc, cell.time + 1));
			}
		}
		
	}

	private static void copy(){
		lab_copied = new int[N][N];
		visited = new boolean[N][N];
		
		for(int r = 0; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c) {
				if(lab[r][c] == 1) lab_copied[r][c] = WALL;
				else if(lab[r][c] == 2) lab_copied[r][c] = NON_ACTIVE;
				else lab_copied[r][c] = 0;
			}
		}
	}
}
