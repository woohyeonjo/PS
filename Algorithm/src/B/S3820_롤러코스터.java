package B;

import java.util.PriorityQueue;
import java.util.Scanner;

public class S3820_롤러코스터 {
	
	static class Rail implements Comparable<Rail> {
		long a, b;
		
		public Rail(long a, long b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Rail o) {
			if((o.a - 1) * this.b - (this.a - 1) * o.b > 0) return 1;
			else return -1;
		}
	}
	
	
	static PriorityQueue<Rail> rollercoster;
	static int T, N;
	static long ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		rollercoster = new PriorityQueue<>();
		
		for(int t = 1; t <= T ; ++t) {
			N = sc.nextInt();
			ans = 1;
			
			for(int i = 0 ; i < N ; ++i) rollercoster.offer(new Rail(sc.nextInt(), sc.nextInt()));
			Rail rail;
			while(!rollercoster.isEmpty()) {
				rail = rollercoster.poll();
				ans = (rail.a * ans + rail.b) % 1000000007;
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}
}
