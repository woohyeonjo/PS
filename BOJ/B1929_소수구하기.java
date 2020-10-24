package BOJ;

import java.util.Scanner;

public class B1929_소수구하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		boolean[] primes = new boolean[M + 1];
		primes[1] = true;
		
		getPrimes(primes, M);

		for(int i = N ; i <= M ; ++i) {
			if(!primes[i]) System.out.println(i);
		}
 	}
	
	private static void getPrimes(boolean[] primes, int n){
		for(int i = 2 ; i <= Math.sqrt(n) ; ++i){
		    if(primes[i]) continue; // 이미 체크된 i는 넘어가기
		    for(int j = i + i ; j <= n ; j += i){ // i를 제외한 i의 배수 체크
		      primes[j] = true;
		    }
		}
	}
}
