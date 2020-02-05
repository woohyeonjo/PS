package SWEA;

import java.util.Scanner;

public class S1204_최빈수구하기_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int[] score;
		
		for(int t = 1 ; t <= T ; ++t) {
			score = new int[101];
			sc.nextInt();
			for(int i = 0 ; i < 1000 ; ++i) {
				score[sc.nextInt()]++;
			}
			
			int maxIndex = 0;
			int maxCount = -1;
			
			for(int i = 0 ; i <= 100 ; ++i) {
				if(score[i] >= maxCount) {
					maxCount = score[i];
					maxIndex = i;
				}
			}
			
			System.out.println("#" + t + " " + maxIndex);
		}
	}
}
