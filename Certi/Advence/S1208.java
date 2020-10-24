package SWEA;


import java.util.Arrays;
import java.util.Scanner;

public class S1208 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[100];
		
		for(int t = 1; t <= 10 ; ++t) {
			int N = sc.nextInt();
			
			for(int i = 0 ; i < 100 ; ++i) {
				arr[i] = sc.nextInt();
			}
			
			for(int n = 0 ; n < N ; ++n) {
				Arrays.sort(arr);
				arr[0]++;
				arr[99]--;
			}
			
			Arrays.sort(arr);
			System.out.println("#" + t + " " + (arr[99] - arr[0]));
		}
	}
}
