package BOJ.go;

import java.util.Scanner;

public class B16926_배열돌리기1 {
	
	static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static int[][] map;
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	static int N, M, R;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();
		
		map = new int[N][M];
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = sc.nextInt(); 
			}
		}
		
		for(int i = 0 ; i < R ; ++i) {
			rotate();
		}
		print();
		
		
	}

	private static void rotate() {
		int depth = Integer.min(N, M) / 2;
		int startNum;
		int r, c, nr, nc, d;
		
		for(int i = 0 ; i < depth ; ++i) {
			startNum = map[i][i];
			r = i;
			c = i;
			d = 0;
			
			while(d < 4){
				nr = r + dir[d][0];
				nc = c + dir[d][1];
				if(nr >= i && nr < N - i && nc >= i && nc < M - i) {
					map[r][c] = map[nr][nc];
					r = nr;
					c = nc;
				}
				else d++;
			}
			map[i + 1][i] = startNum;
		}
	}

	private static void print() {
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				System.out.print(map[r][c] + " "); 
			}
			System.out.println();
		}
		System.out.println();
	}
}
