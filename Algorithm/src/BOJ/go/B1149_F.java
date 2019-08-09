package BOJ.go;

import java.util.Scanner;

public class B1149_F {
	
	static int[][] house;
	static int N, sum, ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		house = new int[N][3];
		
		for(int n = 0 ; n < N ; ++n) {
			for(int c = 0 ; c < 3 ; ++c) {
				house[n][c] = sc.nextInt();
			}
		}
		
		sum = 0;
		dfs(0, -1);
		
		System.out.println(ans);
	}

	private static void dfs(int index, int color) {
		if(index == N) {
			ans = ans > sum ? sum : ans;
			return;
		}
		
		for(int i = 0 ; i < 3 ; ++i) {
			if(i == color) continue;
			sum += house[index][i];
			dfs(index + 1, i);
			sum -= house[index][i];
		}
	}
}
