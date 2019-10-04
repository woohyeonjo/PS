package BOJ;

import java.util.Arrays;
import java.util.Scanner;

public class B15649 {
	
	static int N, M;
	static int[] numbers;
	static boolean[] selected;
	
	private static void nPm(int index) {
		if(index == M) {
			String ans = Arrays.toString(numbers);
			ans = ans.substring(1, ans.length() - 1);
			ans = ans.replaceAll(",", "");
			System.out.println(ans);
			return;
		}
		
		for(int i = 1; i <= N ; i++) {
			if(!selected[i]) {
				numbers[index] = i;
				selected[i] = true;
				nPm(index + 1);
				selected[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		numbers = new int[M];
		selected = new boolean[N + 1];
		
		nPm(0);
		
	}
}
