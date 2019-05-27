package Algorithm.BOJ.go;

import java.util.Scanner;

public class B2446 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int size = 2 * N - 1;
		
		for(int l = 0 ; l < size / 2 ; ++l) {
			// ?�� ?��각형
			for(int b = 0 ; b < l ; ++b) System.out.print(" ");
			for(int s = size - l * 2 ; s >= 1 ; --s ) System.out.print("*");
			System.out.println();
		}
		
		for(int l = size / 2 ; l < size ; l++) {
			// 4 5 6 7 8
			for(int b = l + 1 ; b < size ; ++b) System.out.print(" ");
			for(int s = 0 ; s < l - (size - l) + 2 ; ++s) System.out.print("*");
			// ?��?�� 커져?�� ?��  커�??�� �? l : 4 5 6 7 8
			System.out.println();
		}
	}
}
