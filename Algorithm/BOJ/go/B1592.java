package B190627;

import java.util.Scanner;

public class B1592 {
	
	static int[] friends;
	static int N, M, L;
	static int current, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		L = sc.nextInt();
		
		ans = 0;
		current = 0;
		
		friends = new int[N];
		friends[current]++;
		
		while(friends[current] < M){
			if(current + 1 % 2 == 0) {
				// 반시계
				current -= L;
				if(current < 0) current += N;
				friends[current]++;
				ans++;
			} else {
				// 시계
				current = (current + L) % N;
				friends[current]++;
				ans++;
			}
		}
		System.out.println(ans);
	}
}
