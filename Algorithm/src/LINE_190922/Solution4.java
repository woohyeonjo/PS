package LINE_190922;

import java.util.Scanner;

public class Solution4 {
	
	static int[] seat;
	static int N, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
	
		seat = new int[N + 1];
		for(int i = 1 ; i <= N ; ++i) seat[i] = sc.nextInt();
		
		
		int first = 0, second = 0;
		int distance = 0;
		
		for(int i = 1 ; i <= N ; ++i) {
			if(seat[i] == 1) {
				if(first == 0) {
					first = i;
					continue;
				}
				else {
					second = i;
					distance = second - first;
					if(distance > 1) distance /= 2;
					ans = distance > ans ? distance : ans;
					first = second;
				}
			}
		}
		
		if(second == 0) {
			if(N - first > first) ans = N - first;
			else ans = first - 1;
		}
		
		System.out.println(ans);
	}
}
