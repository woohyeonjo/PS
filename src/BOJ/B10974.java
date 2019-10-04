package BOJ;

import java.util.Arrays;
import java.util.Scanner;

public class B10974 {
	
	static int N;
	static boolean[] selected;
	static int[] numbers;
	
	private static void permutation(int index) {
		if(index == N) {
			for(int num : numbers) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 1 ; i <= N ; i++) {
			if(!selected[i]) {
				numbers[index] = i;
				selected[i] = true;
				permutation(index + 1);
				selected[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		selected = new boolean[N + 1];
		numbers = new int[N];
		permutation(0);
		
		
	}
}
