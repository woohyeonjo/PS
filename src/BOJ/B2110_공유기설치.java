package BOJ;

import java.util.Arrays;
import java.util.Scanner;

public class B2110_공유기설치 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int C = sc.nextInt();
		
		int[] pos = new int[N];
		for(int i = 0 ; i < N ; ++i) {
			pos[i] = sc.nextInt();
		}
		
		Arrays.sort(pos);
		
		System.out.println(binarySearch(C, pos));
	}

	private static long binarySearch(int routerCnt, int[] pos) {
		long left = 1;
		long right = pos[pos.length - 1] - 1;
		long mid = 0;
		long ans = 0;
		
		while(left <= right) {
			mid = (left + right) / 2;
			
			if(isPossible(mid, routerCnt, pos)) {
				ans = mid > ans ? mid : ans;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return ans;
	}

	private static boolean isPossible(long distance, int routerCnt, int[] pos) {
		int cnt = 0;
		long accurmulate = 0;
		
		for(int p : pos) {
			if(p >= accurmulate) {
				cnt++;
				accurmulate = p + distance;
			}
		}
		
		if(cnt >= routerCnt) {
			return true;
		} else return false;
	}
}
