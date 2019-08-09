package SWEA.go;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class S1206 {
	
	static int[] arr = new int[1000];
	static int max = Integer.MIN_VALUE;
		
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		for(int t = 1 ; t <= 10 ; ++t) {
			int N = sc.nextInt();
			int count = 0;
			int add = 0;
			
			for(int n = 0 ; n < N ; ++n) {
				arr[n] = sc.nextInt();
			}
			
			//3, 5, 2, 4, 9, 0, 6, 4
			
			for(int n = 2 ; n < N - 2 ; ++n) {
				max = arr[n-2];
				max = arr[n-1] > max ? arr[n-1] : max;
				max = arr[n+1] > max ? arr[n+1] : max;
				max = arr[n+2] > max ? arr[n+2] : max;
				
				if((add = arr[n] - max) > 0) {
					count += add; 
				}
			}
			
			System.out.println("#" + t + " " + count);
		}
	}
}
