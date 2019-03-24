package Algorithm.BOJ.go;

import java.util.Arrays;
import java.util.Scanner;

public class B15655 {
	static int[] input;
	static boolean[] selected;
	static int N, M;
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		input = new int[N];
		selected = new boolean[N];
		
		for(int i = 0 ; i < N ; ++i) {
			input[i] = sc.nextInt();
		}
		Arrays.sort(input);
		
		permutation(0, 0);
	}
	private static void permutation(int index, int start) {
		if(index == M) {
			System.out.println(sb.toString().trim());
			return;
		}
		
		for(int i = start ; i < N ; ++i) {
			if(!selected[i]) {
				int digit = (input[i] + "").length();
				selected[i] = true;
				sb.append(input[i] + " ");
				permutation(index + 1, i + 1);
				sb.delete(sb.length() - (digit + 1), sb.length());
				selected[i] = false;
			}
		}
	}
}
