package BOJ;

import java.util.Scanner;

public class B1644_소수의연속합 {
	static boolean[] prime;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		prime = new boolean[N + 1];
		
		getPrime();
		
		int left = 2;
		int sum = 0;
		int cnt = 0;
		
		for(int right = 2 ; right <= N ; ++right) {
			if(prime[right]) continue;
			
			sum += right;
			
			if(sum == N) {
//				System.out.println(left + " ~ " + right);
				cnt++;
				while(prime[left]) {
					left++;
				}
				sum -= left++;
			} else if(sum > N) {
				while(sum > N) {
					while(prime[left]) {
						left++;
					}
					sum -= left++;
					
					if(sum == N) {
						cnt++;
						while(prime[left]) {
							left++;
						}
						sum -= left++;
						break;
					}
				}
			}
		}
		
		System.out.println(cnt);
	}

	private static void getPrime() {
		
		for(int i = 2 ; i <= Math.sqrt(N) ; ++i) {
			if(prime[i]) continue;
			for(int j = i + i ; j <= N ; j += i) {
				prime[j] = true;
			}
		}
	}
}
