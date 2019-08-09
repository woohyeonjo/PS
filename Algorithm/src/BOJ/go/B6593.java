package BOJ.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class B6593 {
	
	static Cell[][][] building;
	static boolean[][][] visited;
	static int[][] dir = {{0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}, {1, 0, 0}, {-1, 0, 0}}; 
	static Queue<Cell> q;
	static int L, R, C, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String[] line = in.readLine().split(" ");
			L = Integer.parseInt(line[0]);
			R = Integer.parseInt(line[1]);
			C = Integer.parseInt(line[2]);
	
			if(L == 0 && R == 0 && C == 0) break;
			building = new Cell[L][R][C];
			visited = new boolean[L][R][C];
			q = new LinkedList<Cell>();
			ans = 0;
			Cell start = null;
			
			for(int l = 0 ; l < L ; ++l) {
				for(int r = 0 ; r < R ; ++r) {
					line = in.readLine().split("");
					System.out.println(Arrays.toString(line));
					for(int c = 0 ; c < C ; ++c) {
						String status = line[c];
						building[l][r][c] = new Cell(status, l, r, c);
						if(status.equals("S")) start = building[l][r][c];
					}
				}
				in.readLine();
				System.out.println();
			}
			
			if(start == null) break;
			
			q.offer(start);
			visited[start.l][start.r][start.c] = true;
			bfs();
			
			if(ans == 0) System.out.println("Trapped!");
			else System.out.println("Escaped in " + ans + " minute(s).");
		}
	}
	
	private static void bfs() {
		while(!q.isEmpty()) {
			Cell cell = q.poll();
			
			if(cell.status.equals("E")) {
				ans = cell.t;
				return;
			}
			
			int nl = 0, nr = 0, nc = 0;
			
			for(int d = 0 ; d < dir.length ; ++d) {
				nl = cell.l + dir[d][0];
				nr = cell.r + dir[d][1];
				nc = cell.c + dir[d][2];
				
				if(nl >= L || nl < 0 || nr >= R || nr < 0 || nc >= C || nc < 0|| visited[nl][nr][nc]) continue;
				if(building[nl][nr][nc].status.equals(".") || building[nl][nr][nc].status.equals("E")) {
					q.offer(building[nl][nr][nc]);
					building[nl][nr][nc].t = cell.t + 1;
					visited[nl][nr][nc] = true;
				}
			}
		}
	}

	static class Cell{
		String status;
		int l, r, c;
		int t;
		
		public Cell(String status, int l, int r, int c) {
			super();
			this.status = status;
			this.l = l;
			this.r = r;
			this.c = c;
			this.t= 0;
		}
		
		@Override
		public String toString() {
			return status + " ";
		}
		
	}
}
