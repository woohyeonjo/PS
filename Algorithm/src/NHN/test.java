package NHN;

import java.util.Scanner;

public class test {
	
	static String[][] arr;
	static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 시계
	static int[][] dir2 = {{0, 1}, {1, 0}, {-1, 0}, {-1, 0}}; // 반시계
	static boolean clockwise;
	static int N, M;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new String[N][N];
		
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c){
				arr[r][c] = sc.next();
			}
		}
		
		if(M > 0) clockwise = true;
		
		for(int i = 0 ; i < N / 2 ; ++i) {
			for(int j = 0 ; j < Math.abs(M) ; ++j) {
				rotate(i);
				print();
				System.out.println();
			}
			clockwise = !clockwise;
		}
//		print();
	}
	
	private static void rotate(int i) {
		int[][] cur_dir;
		int index_dir = 0;
		int r = i, c = i, nr, nc;
		String temp = arr[i][i];
		
		if(clockwise) cur_dir = dir;
		else cur_dir = dir2;
		
		while(index_dir < 4){
			nr = r + cur_dir[index_dir][0];
			nc = c + cur_dir[index_dir][1];
			if(nr >= i && nr < N - i && nc >= i && nc < N - i){
				arr[r][c] = arr[nr][nc];
				r = nr;
				c = nc;
			} else index_dir++;
		}
		
		if(clockwise) arr[i][i + 1] = temp;
		else  arr[i + 1][i] = temp;
	}

	static private void print() {
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < N ; ++c){
				if(c == N - 1) System.out.print(arr[r][c]);
				else System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}
	}
}
