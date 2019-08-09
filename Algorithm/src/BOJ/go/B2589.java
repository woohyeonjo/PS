package BOJ.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B2589 {
	
	static Tile[][] map;
	static boolean[][] visited;
	static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<Tile> q;
	static int ROW, COL, ans;
	static String[] temp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		q = new LinkedList<Tile>();
		
		temp = in.readLine().split(" ");
		
		ROW = Integer.parseInt(temp[0]);
		COL = Integer.parseInt(temp[1]);
		ans = Integer.MIN_VALUE;
		
		map = new Tile[ROW][COL];
		
		for(int r = 0 ; r < ROW ; ++r) {
			temp = in.readLine().split("");
			for(int c = 0 ; c < COL ; ++c) {
				map[r][c] = new Tile(r, c, 0, temp[c]);
			}
		}
		
		for(int r = 0 ; r < ROW ; ++r) {
			for(int c = 0 ; c < COL ; ++c) {
				if(map[r][c].type.equals("L")) {
					visited = new boolean[ROW][COL];
					visited[r][c] = true;
					q.offer(map[r][c]);
					init();
					bfs();
				}
			}
		}
		
		bfs();
		System.out.println(ans);
	}
	
	private static void init() {
		for(int r = 0 ; r < ROW ; ++r) {
			for(int c = 0 ; c < COL ; ++c) {
				map[r][c].distance = 0;
			}
		}
	}
	
	private static void bfs() {
		int nextRow = 0, nextCol = 0;
		Tile nextTile = null;
		
		while(!q.isEmpty()) {
			Tile t = q.poll();
			
			if(q.isEmpty()) {
				ans = t.distance > ans ? t.distance : ans;
			}
			
//			System.out.print("[" + t.row + ", " + t.col + "]" + "방문");
//			System.out.println("?��?�� �??��까�??�� 거리" + t.distance);
			
			for(int i = 0 ; i < 4 ; ++i) {
				nextRow = t.row + direction[i][0];
				nextCol = t.col + direction[i][1];
				
				if(nextRow >= 0 && nextRow < ROW && nextCol >= 0 && nextCol < COL) {
					nextTile = map[nextRow][nextCol];
					if(!visited[nextTile.row][nextTile.col] && nextTile.type.equals("L")) {
						visited[nextTile.row][nextTile.col] = true;
						nextTile.distance = t.distance + 1;
						q.offer(nextTile);
					}
				}
			}
		}
	}

	static class Tile{
		int row, col, distance;
		String type;

		public Tile(int row, int col, int distance, String type) {
			super();
			this.row = row;
			this.col = col;
			this.distance = distance;
			this.type = type;
		}
	}
}
