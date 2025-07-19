package SWEA;


import java.util.HashSet;
import java.util.Scanner;

public class S3752 {
	static int[] points;
	static int[] arr;
	static HashSet<Integer> set;
	static int T, N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int t = 1; t <= T; ++t) {
			N = sc.nextInt();

			points = new int[N];
			arr = new int[N];
			set = new HashSet<Integer>();

			for (int n = 0; n < N; ++n) {
				points[n] = sc.nextInt();
			}
			
			for(int m = 0 ; m <= N ; m++) {
				dfs(0, 0, m);
			}
			
			System.out.println("#" + t + " " + set.size());
		}
	}

	private static void dfs(int index, int start, int m) {

		if (index == m) {
			int sum = 0;
			
			for(int j = 0 ; j < arr.length ; ++j) {
				sum += arr[j];
			}
			
			set.add(sum);
			return;
		}

		for (int i = start; i < N; ++i) {
			arr[index] = points[i];
			dfs(index + 1, i + 1, m);
		}
	}
}
