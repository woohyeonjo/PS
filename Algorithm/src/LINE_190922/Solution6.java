package LINE_190922;

import java.util.Scanner;

public class Solution6 {
	
	static int V_SIZE;
	static String ALIGN;
	static int[][][] numbers;
	static int[][] input;
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		ALIGN = sc.next();
		
		numbers = new int[N][][];
		input = new int[N][3];
		
		int size, num;
		int max = 0;
		for(int i = 0 ; i < N ; ++i) {
			size = sc.nextInt();
			num = sc.nextInt();
			max = size > max ? size : max;
			input[i][0] = size;
			input[i][1] = num;
			input[i][2] = size * 2 - 1;
		}
		
		V_SIZE = max * 2 - 1;
		
		
		
	
	}
}
