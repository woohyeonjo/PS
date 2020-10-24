package BOJ;


import java.util.Scanner;

public class B2443 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		for (int n = N ; n > 0; --n) {
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
