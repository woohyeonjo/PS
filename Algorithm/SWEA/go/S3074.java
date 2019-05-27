package Algorithm.SWEA.go;

import java.util.Scanner;

public class S3074 {
	static long[] judge;
	static int T, N, M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			N = sc.nextInt();
			M = sc.nextInt();
			judge = new long[N];
			
			long max = 0;
			for(int n = 0 ; n < N ; ++n) {
				judge[n] = sc.nextLong();
				max = Math.max(max, judge[n]);
			}
			System.out.println("#" + t + " " + binarySearch(max * M, M));
		}
	}
	private static long binarySearch(long max, long target) {
		long left = 0;
		long right = max;
		
		while(left <= right) {
			long mid = (left + right)/2;
			long allSum = 0;
			
			for(int i = 0; i < judge.length; i++) {
				allSum += mid / judge[i];
			}
			
			if(target > allSum) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}
}