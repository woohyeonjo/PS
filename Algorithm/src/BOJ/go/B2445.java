package BOJ.go;

import java.util.Scanner;

public class B2445 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		for (int n = 1; n <= N; ++n) {
			for(int i = 0 ; i < n ; ++i) {
				System.out.print("*");
			}
			for(int j = 0 ; j < N * 2 - (n * 2) ; ++j) {
				System.out.print(" ");
			}
			for(int i = 0 ; i < n ; ++i) {
				System.out.print("*");
			}
			System.out.println();
		}

		for (int n = 1; n <= N; ++n) {
			for(int i = 0 ; i < N - n ; ++i) {
				System.out.print("*");
			}
			for(int j = 0 ; j < n * 2  ; ++j) {
				System.out.print(" ");
			}
			for(int i = 0 ; i < N - n ; ++i) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}
}
