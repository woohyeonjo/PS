package BOJ.go;

import java.util.Scanner;

public class B1547 {
	
	static boolean[] cup = new boolean[4];
	static int M;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		cup[1] = true;
		
		for(int i = 0 ; i < M ; ++i) swap(sc.nextInt(), sc.nextInt());
		
		for(int i = 1 ; i < 4 ; ++i) {
			if(cup[i]) {
				System.out.println(i);
				break;
			}
		}
	}

	private static void swap(int from, int to) {
		boolean temp = cup[to];
		cup[to] = cup[from];
		cup[from] = temp;
	}
	
}
