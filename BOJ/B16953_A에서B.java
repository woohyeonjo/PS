package BOJ;

import java.util.Scanner;

public class B16953_A에서B {
	
	static long A, B, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		A = sc.nextLong();
		B = sc.nextLong();
		
		ans = Long.MAX_VALUE;
		
		dfs(A, 0);
		
		if(ans == Long.MAX_VALUE) ans = -1;
		else ans += 1;
		
		System.out.println(ans);
	}

	private static void dfs(long num, long depth) {
		if(num > B) return;
		
		if(num == B) {
			ans = ans > depth ? depth : ans;
			return;
		}
		
		// * 2
		dfs(num * 2, depth + 1);
		// 뒤에 1 붙이기
		dfs((num * 10) + 1, depth + 1);
	}
}
