package Algorithm.BOJ.go;

import java.util.Scanner;

public class B15652 {
	static int N, M;
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		permutation(0, 1);
	}

	private static void permutation(int index, int start) {
		
		if(index == M) {
			System.out.println(sb.toString());
			return;
		}
		
		for(int i = start ; i <= N ; ++i) {
			sb.append(i + " ");
			permutation(index + 1, i);
			sb.delete(sb.length() - 2, sb.length());
		}
	}
}
