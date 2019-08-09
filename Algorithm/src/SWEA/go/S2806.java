package SWEA.go;

import java.util.Scanner;

public class S2806 {
	
	static int[][] diagonal = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
						//좌상 ?��?�� ?��?�� 좌하
	static boolean[][] onBoard;
	static int result, queenCount;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			int N = sc.nextInt();
			onBoard = new boolean[N][N];
			result = 0;
			
			for(int row = 0 ; row < N ; ++row) {
				queenCount = 0;
				dfs(N, row, 0);
			}
			
			System.out.println("#" + t + " " + result);
		}
	}

	private static void dfs(int N, int row, int col) {
		if(queenCount == N) {
			result++;
			return;
		}
		
		if(check(N, row, col)) {
			onBoard[row][col] = true;
			queenCount++;
			dfs(N, row, col + 1);
			onBoard[row][col] = false;
		} 
	}

	private static boolean check(int N, int row, int col) {
		if(row < 0 || row >= N || col < 0 || col >= N) return false;
		
		// check row
		for(int i = 0 ; i < N ; ++i) {
			if(onBoard[i][col]) return false;
		}
		// check col
		for(int i = 0 ; i < N ; ++i) {
			if(i == col) continue;
			if(onBoard[row][i]) return false;
		}
		// check diagonal
		for(int i = 0 ; i < diagonal.length ; ++i) {
			int nextR = row, nextC = col;
			while(true) {
				nextR = nextR + diagonal[i][0];
				nextC = nextC + diagonal[i][1];
				if(nextR >= 0 && nextR < N && nextC >= 0 && nextC < N) {
					if(onBoard[nextR][nextC] == true) return false;
				} else break;
			}
		}
		return true;
	}
	
	
}
