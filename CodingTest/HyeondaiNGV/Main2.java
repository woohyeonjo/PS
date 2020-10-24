package CodingTest.HyeondaiNGV;

import java.util.Scanner;

public class Main2 {
	
	static int N, M, ans, cnt;
	static int[] parent;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		ans = 0;
		
		parent = new int[N + 1];
		
		for(int i = 1 ; i <= N ; ++i) {
			parent[i] = sc.nextInt();
		}
		
		for(int i = 0 ; i < M ; ++i) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			cnt = 0;
			count(A, B, A);
			System.out.println(cnt);
		}
		
		System.out.println(ans);
	}

	private static void count(int a, int b, int cur) {
		for(int i = 1 ; i <= N ; ++i) {
			if(parent[i] == cur && i != b) {
				cnt++;
				count(a, b, i);
			}
		}
	}
}
