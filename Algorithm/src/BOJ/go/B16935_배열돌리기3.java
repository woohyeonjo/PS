package BOJ.go;

import java.util.Scanner;

public class B16935_배열돌리기3 {
	
	static int[][] map;
	static int[][] temp;
	static int[] cal;
	static int N, M, R;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();
		
		map = new int[N][M];
		temp = new int[M][N];
		cal = new int[R];
		
		for(int i = 0 ; i < N ; ++i) {
			for(int j = 0 ; j < M ; ++j) {
				map[i][j] = sc.nextInt();
			}
		}
		for(int i = 0 ; i < M ; ++i)rotateRight();
		
		print();
		
//		for(int i = 0 ; i < R ; ++i) {
//			cal[i] = sc.nextInt();
//		}
//		
//		for(int i = 0 ; i < R ; ++i) {
//			transform(cal[i]);
//		}
//		print();
	}

	private static void print() {
		for(int i = 0 ; i < N ; ++i) {
			for(int j = 0 ; j < M ; ++j) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void transform(int select) {
		switch(select) {
		case 1:
			upSideDown();
			break;
		case 2:
			mirroring();
			break;
		case 3:
			rotateRight();
			break;
		case 4:
			rotateLeft();
			break;
		case 5:
			clusterRotateRight();
			break;
		case 6:
			clusetRotateLeft();
			break;
		}
	}

	private static void rotateRight() {
		          
		
	}

	private static void mirroring() {
		for(int c = 0 ; c < M / 2 ; ++c) {
			for(int r = 0 ; r < N ; ++r) {
				swap(r, c, r, M - 1 - c);
			}
		}
	}

	private static void upSideDown() {
		for(int r = 0 ; r < N / 2 ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				swap(r, c, N - 1 - r, c);
			}
		}
	}
	
	private static void swap(int r1, int c1, int r2, int c2) {
		int temp = map[r1][c1];
		map[r1][c1] = map[r2][c2];
		map[r2][c2] = temp;
	}
}
