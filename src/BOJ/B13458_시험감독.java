package BOJ;


import java.util.Scanner;

public class B13458_시험감독 {
	
	static int[] room;
	static int N;
	static int B, C;
	static long ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		ans = 0;
		
		room = new int[N];
		for(int i = 0 ; i < N ; ++i) {
			room[i] = sc.nextInt();
		}
		
		B = sc.nextInt();
		C = sc.nextInt();
		
		for(int i = 0 ; i < N ; ++i) {
			ans++;
			room[i] -= B;
			
			if (room[i] > 0) {
				ans += Math.ceil(1.0 * room[i] / C);
			}
		}
		
		System.out.println(ans);
		
	}
	
}
