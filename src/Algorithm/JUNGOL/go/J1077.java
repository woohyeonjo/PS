package Algorithm.JUNGOL.go;

import java.util.Arrays;
import java.util.Scanner;

public class J1077 {
	static int[] dp;
	static Jem[] jemList;
	static int N, W, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		W = sc.nextInt();
		
		dp = new int[W + 1];
		jemList = new Jem[N];
		
		for(int n = 0 ; n < N ; ++n) {
			jemList[n] = new Jem(sc.nextInt(), sc.nextInt());
		}
		
		Arrays.sort(jemList);
		
		for(int n = 0 ; n < N ; ++n) {
			for (int w = 1 ; w <= W ; ++w) {
				if(w - jemList[n].weight > 0) {
					dp[w] = Math.max(jemList[n].value + dp[w - jemList[n].weight], dp[w]);
				} else if(w - jemList[n].weight == 0) {
					dp[w] = jemList[n].value;
				}
			}
		}
		
		System.out.println(dp[W]);
	}
	
	static class Jem implements Comparable<Jem>{
		int value, weight;

		public Jem(int weight, int value) {
			super();
			this.value = value;
			this.weight = weight;
		}

		@Override
		public int compareTo(Jem o) {
			return this.weight - o.weight;
		}
		
		@Override
		public String toString() {
			return weight + "";
		}
	}
}
