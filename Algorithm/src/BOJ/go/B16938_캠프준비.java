package BOJ.go;

import java.util.Scanner;

public class B16938_캠프준비 {
	
	static int N, L, R, X, ans;
	static int[] level;
	static boolean[] set;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		X = sc.nextInt();

		level = new int[N];
		set = new boolean[N];
		
		for(int n = 0 ; n < N ; ++n) level[n] = sc.nextInt();
		
		go(0);
		System.out.println(ans);
	}

	private static void go(int cnt) {
		if(cnt == N ) {
			if(check()) ans++;
			return;
		}		
		set[cnt] = true;
		go(cnt + 1);
		set[cnt] = false;
		go(cnt + 1);
		
	}

	private static boolean check() {
		int sum = 0;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for(int i = 0 ; i < N ; ++i) {
			if(set[i]) {
				sum += level[i];
				min = min > level[i] ? level[i] : min;
				max = max < level[i] ? level[i] : max;
			}
		}
		
		if(sum >= L && sum <= R && (max - min) >= X)return true;
		else return false;
	}
}
