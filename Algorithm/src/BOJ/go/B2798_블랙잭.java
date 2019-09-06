package BOJ.go;

import java.util.Scanner;

public class B2798_블랙잭 {
	
	static int[] card;
	static boolean[] visit;
	static int N, M, ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		card = new int[N];
		visit = new boolean[N];
		for(int n = 0 ; n < N ; ++n) card[n] = sc.nextInt();
		
		go(0, 0);
		System.out.println(ans);
	}
	
	private static void go(int index, int sum) {
		if(sum > M) return;
		if(index == 3){
			ans = sum > ans ? sum : ans;
			return;
		}
		
		for(int i = 0 ; i < N ; ++i){
			if(visit[i]) continue;
			visit[i] = true;
			go(index + 1, sum + card[i]);
			visit[i] = false;
		}
	}
}
