package BOJ;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B14502 {
	static int[][] map;
	static boolean[][] visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static ArrayList<Cell> virus;
	static Queue<Cell> q;
	static int N, M, ans;
	static class Cell {
		int r, c;

		public Cell(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		q = new LinkedList<Cell>();
		virus = new ArrayList<Cell>();
		ans = 0;
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = sc.nextInt();
				if(map[r][c] == 2) {
					virus.add(new Cell(r, c));
				}
			}
		}
		dfs(0);
		System.out.println(ans);
	}
	
	private static void dfs(int cnt) {
		
		if(cnt == 3) {
			int[][] temp = deepCopy(map);
			visited = new boolean[N][M];
			
			for(Cell c : virus) {
				q.offer(c);
				visited[c.r][c.c] = true;
			}
			
			bfs(temp);
			ans = Math.max(ans, count(temp));
			return;
		}
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				if(map[r][c] == 0) {
					map[r][c] = 1;
					dfs(cnt + 1);
					map[r][c] = 0;
				}
			}
		}
	}

	private static int[][] deepCopy(int[][] map) {
		int[][] result = new int[N][M];
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				result[r][c] = map[r][c];
			}
		}
		return result;
	}

	private static int count(int[][] temp) {
		int cnt = 0;
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				if(temp[r][c] == 0) cnt++;
			}
		}
		return cnt;
	}

	private static void bfs(int[][] temp) {
		int nr, nc;
		
		while(!q.isEmpty()) {
			Cell cell = q.poll();
			for(int d = 0 ; d < 4 ; ++d) {
				nr = cell.r + dir[d][0];
				nc = cell.c + dir[d][1];
				if(nr >= N || nr < 0 || nc >= M || nc < 0 || visited[nr][nc]) continue;
				if(temp[nr][nc] == 0) {
					temp[nr][nc] = 2;
					visited[nr][nc] = true;
					q.offer(new Cell(nr, nc));
				}
			}
		}
	}
}
