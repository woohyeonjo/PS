package BOJ;

import java.util.Arrays;
import java.util.Scanner;

public class B1654_랜선자르기 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] wire = new int[N];
		
		for(int i = 0 ; i < N ; ++i) {
			wire[i] = sc.nextInt();
		}
		
		Arrays.sort(wire);
		
		System.out.println(binerySearch(K, wire));
	}

	private static long binerySearch(int k, int[] wire) {
		long left = 1;
		long right = wire[wire.length - 1];
		long mid = 0;
		long cnt = 0;
		long ans = 0;
		
		while(left <= right) {
			mid = (left + right) / 2;
			cnt = cutWire(mid, wire);
			
			if(cnt >= k) {
				ans = mid > ans ? mid : ans;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return ans;
	}

	private static int cutWire(long length, int[] wire) {
		int cnt = 0;
		
		for(int w : wire) {
			cnt += w / length;
		}
		
		return cnt;
	}
	
}
