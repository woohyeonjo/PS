package SWEA;


import java.util.Scanner;

public class S3752_2 {
	static int[] points;
	static int T, N;
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int t = 1; t <= T; ++t) {
			N = sc.nextInt();
			arr = new int[101];
			int ans = N * 2 + 1;
			points = new int[N];

			for (int n = 0; n < N; ++n) {
				arr[sc.nextInt()]++;
			}
			
			for(int n = 1 ; n < 101 ; ++n) {
				if(arr[n] > 1) {
					ans -= arr[n];
				}
			}
			System.out.println("#" + t + " " + ans);
		}
	}
}
