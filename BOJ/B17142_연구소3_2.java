package BOJ;


import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class B17142_연구소3_2 {
	
	static class Cell implements Comparable<Cell> {
		int r, c, time;
		
		public Cell(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}

		@Override
		public int compareTo(Cell o) {
			return this.time - o.time;
		}
	}
	
	static ArrayList<Cell> virusSet;
	static Cell[] selected;
	static PriorityQueue<Cell> q;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[][] map;
	static int[][] copy;
	static boolean[][] visited;
	static int N, M, ans;
	
	static final int WALL = -3;
	static final int NON_ACTIVE = -2;
	static final int ACTIVE = -1;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		ans = Integer.MAX_VALUE;
		map = new int[N][N];
		visited = new boolean[N][N];
		selected = new Cell[M];
		q = new PriorityQueue<>();
		virusSet = new ArrayList<>();
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				int temp = sc.nextInt();
				if(temp == 2) virusSet.add(new Cell(r, c, 0));
				map[r][c] = temp;
			}
		}
		
		select(0, 0);
		if(ans == Integer.MAX_VALUE) ans = -1;
		System.out.println(ans);
	}

	private static void select(int cnt, int index) {
		
		if(cnt == M){
			copyMap();
			
			for(int i = 0 ; i < M ; ++i){
				q.offer(new Cell(selected[i].r, selected[i].c, 0));
				visited[selected[i].r][selected[i].c] = true;
				copy[selected[i].r][selected[i].c] = ACTIVE;
			}
			
			bfs();
			check();
			
			return;
		}
		
		for(int i = index ; i < virusSet.size() ; ++i){
			selected[cnt] = virusSet.get(i);
			select(cnt + 1, i + 1);
		}
	}

	private static void check() {
		int max = 0;
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				if(copy[r][c] == 0) return;
				if(copy[r][c] < 0) continue;
				max = copy[r][c] > max ? copy[r][c] : max;
			}
		}
		ans = ans > max ? max : ans;
	}

	private static void bfs() {
		while(!q.isEmpty()){
			Cell cell = q.poll();
			
			int nr, nc;
			for(int i = 0 ; i < 4; ++i){
				nr = cell.r + dir[i][0];
				nc = cell.c + dir[i][1];
				
				if(nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc]) continue;
				if(copy[nr][nc] == WALL) continue;
				if(copy[nr][nc] != NON_ACTIVE) copy[nr][nc] = cell.time + 1;
				
				q.offer(new Cell(nr, nc, cell.time + 1));
				visited[nr][nc] = true;
			}
		}
	}
	
	private static void copyMap() {
		
		copy = new int[N][N];
		visited = new boolean[N][N];
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				if(map[r][c] == 1) copy[r][c] = WALL;
				else if(map[r][c] == 2) copy[r][c] = NON_ACTIVE;
				else copy[r][c] = 0;
			}
		}
	}
}
