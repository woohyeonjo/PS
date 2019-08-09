package SWEA.go;

import java.util.Scanner;

public class S5215 {
	static int[][] ingredients = new int[20][];
	static boolean isUsed[];
	static int N, limit, highScore;
	static int sumCal, sumScore;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; t++) {
			N = sc.nextInt();
			limit = sc.nextInt();
			isUsed = new boolean[N];
			
			highScore = 0;
			
			for(int n = 0 ; n < N ; n++) {
				int score = sc.nextInt();
				int cal = sc.nextInt();
				int[] ingredient = {score, cal};
				
				ingredients[n] = ingredient;
			}
			
			find(0);
			
			System.out.println("#" + t + " " + highScore);
		}
	}
	public static void find(int i) {
		if(i >= N) return;
		if(isUsed[i]) find(i + 1);
		if(sumCal + ingredients[i][1] > limit) return;
		sumCal += ingredients[i][1];
		sumScore += ingredients[i][0];
		
		highScore = sumScore;
		find(i + 1);
	}
}
