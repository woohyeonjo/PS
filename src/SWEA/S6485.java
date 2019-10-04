package SWEA;


import java.util.Scanner;

public class S6485 {
	
	static int[] busStopCnt;
	static String ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			ans = "#" + t;
		 	int N = sc.nextInt();
			
		 	busStopCnt = new int[5001];
		 	for(int n = 0 ; n < N ; ++n) {
		 		int An = sc.nextInt();
		 		int Bn = sc.nextInt();
		 		
		 		for(int i = An ; i <= Bn ; ++i) {
		 			busStopCnt[i]++;
		 		}
		 	}
		 	
		 	int P = sc.nextInt();
		 	for(int p = 1 ; p <= P ; ++p) {
		 		ans += " " + busStopCnt[sc.nextInt()];
		 	}
		 	
		 	System.out.println(ans);
		}
	}
}
