package BOJ;

import java.util.Scanner;

public class B11051_이항계수2 {
	
	static int[][] pascal;
	static int N, K;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		pascal = new int[1001][1001];
		
		for(int i = 1 ; i <= N ; i++){
	        for(int j = 0 ; j <= N ; j++){
	            if(i == j || j == 0){
	                pascal[i][j] = 1;
	            }
	            else
	            	pascal[i][j] = (pascal[i - 1][j] + pascal[i - 1][j - 1]) % 10007;
	        }
	    }
		
		System.out.println(pascal[N][K] % 10007);
	}
}
