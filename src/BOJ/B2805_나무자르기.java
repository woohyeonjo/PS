package BOJ;

import java.util.Arrays;
import java.util.Scanner;

public class B2805_나무자르기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] tree = new int[N];
		
		for(int i = 0 ; i < N ; ++i) {
			tree[i] = sc.nextInt();
		}
		
		Arrays.sort(tree);
		
		System.out.println(binarySearch(M, tree));
	}

	private static long binarySearch(int m, int[] tree) {
		long left = 0;
		long right = tree[tree.length - 1] - 1;
		long mid = 0;
		long ans = 0;
		long length = 0;
		
		while(left <= right) {
			mid = (left + right) / 2;
			length = cutTree(mid, tree);
			
			if(length >= m) {
				ans = mid > ans ? mid : ans;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return ans;
	}

	private static long cutTree(long mid, int[] tree) {
		long result = 0;
		
		for(int i = 0 ; i < tree.length ; ++i) {
			if(tree[i] > mid) {
				result += tree[i] - mid;
			}
		}
		
		return result;
	}
}
