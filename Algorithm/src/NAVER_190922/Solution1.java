package NAVER_190922;

import java.util.Scanner;

public class Solution1 {
	static long[] memo = new long[1000001];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		memo[1] = 2;
		
		long gap = 2;
		long next = 2;
		long[] twoNumber = {3, 4};
		long[] threeNumber = {1, 2, 3};
		for(int i = 2 ; i <= n ; ++i) {
			memo[i] = 1;
//			System.out.println(i + " 번째 숫자 ");
			if(i == next) {
				for(int j = 0 ; j < 3 ; ++j) {
//					System.out.print(threeNumber[j] + " ");
					memo[i] *= threeNumber[j];
					threeNumber[j]++;
				}
				gap++;
				next = next + gap;
			} else {
				for(int j = 0 ; j < 2 ; ++j) {
//					System.out.print(twoNumber[j] + " ");
					memo[i] *= twoNumber[j];
					twoNumber[j]++;
				}
			}
//			System.out.println();
		}
		for(int i = 1 ; i < 30 ; ++i) {
			System.out.println(i + "번째 숫자는 " + memo[i]);
		}
	}
}
