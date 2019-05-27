package Algorithm.BOJ.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 2�? 
public class B2146 {
	
	static Point[][] map;
	static boolean[][] visited;
	static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<Point> queue;
	
	static int ans = Integer.MAX_VALUE;
	static int N, landNum, count;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		queue = new LinkedList<Point>();
		
		N = Integer.parseInt(st.nextToken());
		map = new Point[N][N];
		visited = new boolean[N][N];
		
		for(int row = 0 ; row < N ; ++row) {
			st = new StringTokenizer(in.readLine());
			for(int col = 0 ; col < N ; ++col) {
				map[row][col] = new Point(row, col, Integer.parseInt(st.nextToken()));
			}
		}
		
		landNum = 2;
		
		for(int row = 0 ; row < N ; ++row) {
			for(int col = 0 ; col < N ; ++col) {
				if(map[row][col].type == 1) {
					queue.offer(map[row][col]);
					map[row][col].type = landNum;
					bfs1();
				}
			}
		}
		
		System.out.println();
		for(int row = 0 ; row < N ; ++row) {
			for(int col = 0 ; col < N ; ++col) {
				System.out.print(map[row][col] + " ");
			}
			System.out.println();
		}
		
		for(int row = 0 ; row < N ; ++row) {
			for(int col = 0 ; col < N ; ++col) {
				if(map[row][col].type != 0) {
					int nextRow, nextCol;
					for(int i = 0 ; i < 4 ; ++i) {
						nextRow = row + direction[i][0];
						nextCol = col + direction[i][1];
						if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N ) {
							if(map[nextRow][nextCol].type == 0) {
								count = 0;
								queue.offer(map[row][col]);
								bfs2(map[row][col].type);
							}
						}
					}
				}
			}
		}
		System.out.println(ans);
	}
	
	private static void bfs1() {
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			int nextRow,nextCol;
			for(int i = 0 ; i < 4 ; ++i) {
				nextRow = p.row + direction[i][0];
				nextCol = p.col + direction[i][1];
				if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N ) {
					Point nextP = map[nextRow][nextCol];
					if(nextP.type == 1) {
						queue.offer(nextP);
						nextP.type = landNum;
					}
				}
			}
			
		}
		landNum++;
	}

	private static void bfs2(int myType) {
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			count++;
			if(p.type != 0 && p.type != myType) {
				ans = ans > count ? count : ans;
				return;
			}
			int nextRow,nextCol;
			for(int i = 0 ; i < 4 ; ++i) {
				nextRow = p.row + direction[i][0];
				nextCol = p.col + direction[i][1];
				if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N ) {
					Point nextP = map[nextRow][nextCol];
					if(nextP.type != myType) {
						queue.offer(nextP);
					}
				}
			}
			
		}
	}
	
	static class Point{
		int row, col, type;

		public Point(int row, int col, int type) {
			super();
			this.row = row;
			this.col = col;
			this.type = type;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append(type);
			return builder.toString();
		}
	}
}
