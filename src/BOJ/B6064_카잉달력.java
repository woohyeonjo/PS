package BOJ;

import java.util.Scanner;

public class B6064_카잉달력 {
	
	static int T, M, N, x, y;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int t = 0 ; t < T ; ++t) {
			M = sc.nextInt();
			N = sc.nextInt();
			x = sc.nextInt();
			y = sc.nextInt();
			
			int lcm = lcm(M, N);
			int cnt = x; // x를 먼저 맞춘다. 
			int curY = x;
			boolean flag = false;
			
			// M:N은 카잉달력의 마지막이기 때문에 M, N의 최소공배수가 될 때가 달력의 마지막이다
			while(cnt <= lcm) {
				// 나머지 연산을 통해 순환시킨다.
				curY = curY % N == 0 ? N : curY % N;
				if(curY == y) {
					flag = true;
					break;
				}
				
				// M만큼 더 해준다. x는 목표 수에 고정된 채 순환된다.
				curY += M;
				cnt += M;
			}
			
			if(flag) System.out.println(cnt);
			else System.out.println(-1);
		}
	}
	
	private static int gcd(int a, int b) {
		if(b == 0) return a;
		else {
			return gcd(b, a % b);
		}
	}
	
	private static int lcm(int a, int b) {
		int gcd = gcd(a, b);
		return a * b / gcd;
	}
}
