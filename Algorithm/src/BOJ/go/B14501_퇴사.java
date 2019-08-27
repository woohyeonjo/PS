package BOJ.go;

import java.util.Scanner;

public class B14501_퇴사 {
	
	static int[][] table;
	static int N, max, profit;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		max = Integer.MIN_VALUE;
		
		table = new int[N][2];
		
		for(int r = 0 ; r < N ; ++r){
			for(int c = 0 ; c < 2 ; ++c){
				table[r][c] = sc.nextInt();
			}
		}
		
		findOut(0);
		
		System.out.println(max);
		
	}

	private static void findOut(int index) {
		
		for(int i = index ; i < N ; ++i){
			if(i + table[i][0] > N) continue;
			profit += table[i][1];
			findOut(i + table[i][0]);
			profit -= table[i][1];
		}
		
		max = profit > max ? profit : max;
		
	}
}
