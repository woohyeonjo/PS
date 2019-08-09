package BOJ.go;

import java.util.Scanner;

public class B14647 {
	
	static int[][] bingo;
	static int ans;
	static int N, M, nineCntAll;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		bingo = new int[N][M];
		ans = 0;
		
		for(int row = 0 ; row < N ; ++row) {
			for(int col = 0 ; col < M ; ++col) {
				bingo[row][col] = sc.nextInt();
			}
		}
		for(int col = 0 ; col < M ; ++col) ans = Math.max(ans, colCheck(col));
		for(int row = 0 ; row < N ; ++row) ans = Math.max(ans, rowCheck(row));
		
		
		for(int row = 0 ; row < N ; ++row) {
			for(int col = 0 ; col < M ; ++col) {
				nineCntAll += nineCounter(bingo[row][col]);
			}
		}
		System.out.println(nineCntAll - ans);
	}
	
	private static int rowCheck(int row) {
		int nineCnt = 0;
		for(int col = 0 ; col < M ; ++col) {
			nineCnt += nineCounter(bingo[row][col]);
		}
		return nineCnt;
	}
	
	private static int colCheck(int col) {
		int nineCnt = 0;
		for(int row = 0 ; row < N ; ++row) {
			nineCnt += nineCounter(bingo[row][col]);
		}
		return nineCnt;
	}
	
	private static int nineCounter(int num) {
		int nineCnt = 0;
		String number = num + "";
		
		for(int i = 0 ; i < number.length() ; ++i) {
			if(number.charAt(i) == '9') nineCnt++;
		}
		return nineCnt;
	}
}
