package BOJ;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B16978_직사각형탈출 {
	
	static class Cell {
		int r, c, cnt;

		public Cell(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	static int N, M, H, W, Sr, Sc, Fr, Fc, ans;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Cell> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		q = new LinkedList<>();
		ans = Integer.MAX_VALUE;
		
		for(int r = 1 ; r < N + 1 ; ++r){
			for(int c = 1 ; c < M + 1 ; ++c){
				map[r][c] = sc.nextInt();
			}
		}
		
		H = sc.nextInt();
		W = sc.nextInt();
		Sr = sc.nextInt();
		Sc = sc.nextInt();
		Fr = sc.nextInt();
		Fc = sc.nextInt();
		
		visited[Sr][Sc] = true;
		q.offer(new Cell(Sr, Sc, 0));
		bfs();
		
		System.out.println(ans);
		
	}

	private static void bfs() {
		while(!q.isEmpty()){
			Cell cell = q.poll();
			int nr, nc;
			
			if(cell.r == Fr && cell.c == Fc){
				ans = cell.cnt;
				return;
			}
			
			for(int i = 0 ; i < 4 ; ++i){
				nr = cell.r + dir[i][0];
				nc = cell.c + dir[i][1];
				
				if(!check(nr, nc)) continue;
				if(visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				q.offer(new Cell(nr, nc, cell.cnt + 1));
			}
		}
		ans = -1;
	}

	private static boolean check(int nr, int nc) {
		
		for(int c = nc ; c < nc + W ; ++c){
			if(c < 1 || c > M || nr + H - 1 > N) return false;
			if(map[nr][c] == 1 || map[nr + H - 1][c] == 1)return false;
		}
		
		for(int r = nr ; r < nr + H ; ++r){
			if(r < 1 || r > N || nc + W - 1 > M) return false;
			if(map[r][nc] == 1 || map[r][nc + W - 1] == 1)return false;
		}
		
		return true;
	}
}
