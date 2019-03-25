package Algorithm.BOJ.go;

import java.util.Scanner;

public class B2444 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		for (int n = 1; n <= N; ++n) {
			for (int i = N - n; i > 0; --i) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2 * n - 1; ++j) {
				System.out.print("*");
			}
			System.out.println();
		}

		for (int n = N - 1; n > 0; --n) {
			for (int i = 1; i <= N - n; ++i) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2 * n - 1; ++j) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
