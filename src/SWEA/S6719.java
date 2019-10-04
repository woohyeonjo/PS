package SWEA;


import java.util.Arrays;
import java.util.Scanner;

public class S6719 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			double level = 0;
			int index = 0;
			
			int N = sc.nextInt(); // N 개의 강좌
			int K = sc.nextInt(); // N �? �? ?���??�� K개의 강좌
			
			int[] all = new int[N];
			int[] pick = new int[K];
			
			for(int n = 0 ; n < N ; ++n) {
				all[n] = sc.nextInt();
			}
			
			Arrays.sort(all);
			
			for(int i = all.length - K ; i < all.length ; ++i) {
				if(index < K) pick[index++] = all[i];
				else break;
			}
			
			for(int k = 0 ; k < K ; ++k) {
				level = (level + pick[k]) / 2;
			}
			
			System.out.println("#" + t + " " + level);
			
		}
	}
	
	// ?��?�� A 강좌?���? M
	// ?��?�� (A+M)/2
}
