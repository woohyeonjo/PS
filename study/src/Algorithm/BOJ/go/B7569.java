package Algorithm.BOJ.go;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B7569 {
	
	static int[][] dir = {{-1, 0, 0}, {1, 0, 0},
							{0, -1, 0}, {0, 1, 0},
							{0, 0, 1}, {0, 0, -1}}; // 6방향
	
	static boolean[][][] visited;
	static Tomato[][][] container;
	static Queue<Tomato> q;
	static int N, M, H, day;
	static boolean isPossible = true;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();
		
		visited = new boolean[N][M][H];
		container = new Tomato[N][M][H];
		day = 0;
		q = new LinkedList<Tomato>();
		
		for(int height = 0 ; height < H ; ++height) {
			for(int row = 0 ; row < N ; ++row) {
				for(int col = 0 ; col < M ; ++col) {
					container[row][col][height] = new Tomato(row, col, height, sc.nextInt(), 0);
				}
			}
		}
		
		for(int height = 0 ; height < H ; ++height) {
			for(int row = 0 ; row < N ; ++row) {
				for(int col = 0 ; col < M ; ++col) {
					if(container[row][col][height].status == 1) {
						q.offer(container[row][col][height]);
					}
				}
			}
		}
		
		bfs();
		
		for(int height = 0 ; height < H ; ++height) {
			for(int row = 0 ; row < N ; ++row) {
				for(int col = 0 ; col < M ; ++col) {
					if(container[row][col][height].status == 0) {
						isPossible = false;
					}
				}
			}
		}
		
		System.out.println((isPossible ? day : -1));
		
		
		
	}
	
	private static void bfs() {
		while(!q.isEmpty()) {
			Tomato t = q.poll();
			day = t.day > day ? t.day : day;
			
			int nextR, nextC, nextH;
			for(int i = 0 ; i < 6 ; ++i) {
				nextR = t.row + dir[i][0];
				nextC = t.col + dir[i][1];
				nextH = t.height + dir[i][2];
				
				if(nextR >= 0 && nextR < N &&
					nextC >= 0 && nextC < M &&
					nextH >= 0 && nextH < H) {
					Tomato nextT = container[nextR][nextC][nextH];
					
					if(nextT.status == 0) {
						nextT.status = 1;
						nextT.day = t.day + 1;
						q.offer(nextT);
					}
				}
			}
		}
		
	}

	static class Tomato{
		int row, col, height;
		int status, day;
		
		public Tomato(int row, int col, int height, int status, int day) {
			super();
			this.row = row;
			this.col = col;
			this.height = height;
			this.status = status;
			this.day = day;
		}
	}
}
