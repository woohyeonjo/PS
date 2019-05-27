package Algorithm.BOJ.go;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class B2458 {

	static int[][] adj;
	static boolean[] isVisited;
	static int N, M, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); 
		M = sc.nextInt();
		
		adj = new int[N + 1][N + 1];
		ans = 0;
		
		for(int m = 0 ; m < M ; ++m) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			adj[a][b] = 1;
			adj[b][a] = 2;
		}
		
		for(int n = 1 ; n <= N ; ++n) {
			int sum = 0;
			isVisited = new boolean[N + 1];
			sum += CntBigger(n);
			isVisited = new boolean[N + 1];
			sum += CntSmaller(n);
			if(sum == N - 1) ans++;
		}
		
		System.out.println(ans);
	}

	private static int CntSmaller(int index) {
		int cnt = 0;
		
		for(int i = 1 ; i <= N ; ++i) {
			if(isVisited[i]) continue;
			if(adj[index][i] == 2) {
				isVisited[i] = true;
				cnt++;
				cnt += CntSmaller(i);
			}
		}
		return cnt;
	}

	private static int CntBigger(int index) {
		int cnt = 0;
		
		for(int i = 1 ; i <= N ; ++i) {
			if(isVisited[i]) continue;
			if(adj[index][i] == 1) {
				isVisited[i] = true;
				cnt++;
				cnt += CntBigger(i);
			}
		}
		return cnt;
	}
	
}
