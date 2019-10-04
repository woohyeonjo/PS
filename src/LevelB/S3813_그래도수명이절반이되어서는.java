package LevelB;

import java.util.Scanner;

public class S3813_그래도수명이절반이되어서는 {
	
	static final int MAX = 200000;
	static int[] memory;
	static int[] data;
	static int T, N, K, W;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		for(int t = 1 ; t <= T ; ++t) {
			N = sc.nextInt();
			K = sc.nextInt();
			W = 0;
			
			memory = new int[N + 1];
			data =  new int[K];
			
			for(int i = 1 ; i <= N ; ++i) memory[i] = sc.nextInt();
			for(int i = 0 ; i < K ; ++i) data[i] = sc.nextInt();
			
			search();
			System.out.println("#" + t + " " + W);
		}
		
	}
	
	private static void search() {
		int left = 0;
		int right = MAX;
		int mid = 0;
		int ans = 0;
		
		while(right > left) {
			mid = (left + right) / 2;
			//System.out.println("MID : " + mid + " LEFT : " + left + " RIGHT : " + right);
			if(check(mid)) {
				right = mid;
				ans = mid;
			}
			else left = mid + 1;
		}
		W = ans;
	}
	
	private static boolean check(int wear) {
		int index = 0;
		int cnt = 0;
		
		for(int i = 1 ; i <= N ; ++i) {
			
			if(memory[i] <= wear) cnt++;
			else cnt = 0;
			
			if(data[index] == cnt) {
				index++;
				if(index == K) return true;
				cnt = 0;
			}
		}
		return false;
	}
}
