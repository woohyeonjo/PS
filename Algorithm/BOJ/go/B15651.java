package Algorithm.BOJ.go;

import java.util.Scanner;

public class B15651 {
	static int N, M;
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		permutation(0);
	}

	private static void permutation(int index) {
		
		if(index == M) {
			System.out.println(sb.toString());
			return;
		}
		
		for(int i = 1 ; i <= N ; ++i) {
			sb.append(i + " ");
			permutation(index + 1);
			sb.deleteCharAt(sb.length() - 1);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
}
