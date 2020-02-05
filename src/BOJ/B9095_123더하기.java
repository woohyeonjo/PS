package BOJ;

import java.util.Scanner;

public class B9095_123더하기 {
	static int[] numbers = {1, 2, 3};
	static int T, N, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int i = 0 ; i < T ; ++i) {
			N = sc.nextInt();
			ans = 0;
			
			go(0);
			
			System.out.println(ans);
		}
	}

	private static void go(int sum) {
		if(sum > N) return;
		if(sum == N) {
			ans++;
			return;
		}
		
		for(int i = 0 ; i < 3 ; ++i) {
			go(sum + numbers[i]);
		}
	}
}
