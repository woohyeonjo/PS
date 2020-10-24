package BOJ;

import java.util.Scanner;

public class B17103_골드바흐파티션 {
	static final int MAX = 1000001;

	static boolean[] primes;
	static int T, N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		primes = new boolean[MAX];
		getPrimes();
		
		T = sc.nextInt();
		
		for(int i = 0 ; i < T ; ++i) {
			N = sc.nextInt();
			int cnt = 0;
			
			// upper은 계속 증가하고 lower는 계속 감소하기 때문에 자리가 바뀔수 없다. 
			for(int upper = N / 2 ; upper < N ; ++upper) {
				int lower = N - upper;
				if(!primes[upper] && !primes[lower]) {
//					System.out.println(upper + ", " + lower);
					cnt++;
				}
			}
			System.out.println(cnt);
		}
	}

	private static void getPrimes() {
		// 1, 0은 소수가 아니다. 
		primes[0] = true;
		primes[1] = true;
		for(int i = 2 ; i < MAX ; ++i) {
			if(primes[i]) continue;
			for(int j = i + i ; j < MAX ; j += i) {
				primes[j] = true;
			}
		}
	}
}
