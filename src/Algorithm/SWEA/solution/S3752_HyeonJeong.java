package Algorithm.SWEA.solution;

import java.util.Scanner;

public class S3752_HyeonJeong {

	static int N,answer;
	static int[] score; 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			
			int sum = 0;
			score = new int[10001];
			for(int i=0; i<N; i++) {
				int num = sc.nextInt();
				sum+=num;
				for(int j=sum; j>=0; j--) {
					if(score[j]>0) score[j+num]++;
				}
				score[num]++;
			}
			
			answer = 1;
			for(int i=0; i<=sum; i++) {
				if(score[i]>0) answer++;
			}
			
			System.out.println("#"+tc+" "+answer);
		}
	}
}
