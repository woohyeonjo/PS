package BOJ.go;

import java.util.Scanner;

public class B16938_캠프준비 {
	
	static class pair {
		int x, y;
		public pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, L, R, X, cnt;
	static int[] numbers;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		X = sc.nextInt();
		
		numbers = new int[N];
		
		for(int n = 0 ; n < N ; ++n) numbers[n] = sc.nextInt();
		
		for(int i = 0 ; i < N ; ++i) {
			for(int j = i + 1 ; j < N ; ++j) {
				if(numbers[i] + numbers[j] < L) continue;
				if(numbers[i] + numbers[j] > R) continue;
				if(Math.abs(numbers[i] - numbers[j]) < X) continue;
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
