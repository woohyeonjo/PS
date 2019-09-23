package CodingTest.LINE_190922;

import java.util.Scanner;

public class Solution3 {

	static final int MAX = 150000;
	static int[] count;
	static int N, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		count = new int[MAX];
		ans = 0;
		
		int start, arrive;
		for(int i = 0 ; i < N ; ++i) {
			start = sc.nextInt();
			arrive = sc.nextInt();
			for(int j = start ; j < arrive ; ++j) count[j]++;
		}
		
		for(int i = 0 ; i < MAX ; ++i) ans = count[i] > ans ? count[i] : ans;
		
		System.out.println(ans);
	}
}
