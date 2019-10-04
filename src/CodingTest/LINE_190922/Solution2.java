package CodingTest.LINE_190922;

import java.util.Arrays;
import java.util.Scanner;

public class Solution2 {
	
	static int[] input;
	static boolean[] selected;
	static int[] answer;
	
	static int K, cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char[] line = sc.nextLine().replace(" ", "").toCharArray();
		K = sc.nextInt();
		
		input = new int[line.length];
		answer = new int[line.length];
		selected = new boolean[line.length];
		
		for(int i = 0 ; i < line.length ; ++i) input[i] = line[i] - '0';
		Arrays.sort(input);
		
		find(0);
	}

	private static boolean find(int index) {
		if(index == input.length) {
			cnt++;
			if(cnt == K) {
				for(int i = 0 ; i < answer.length ; ++i) System.out.print(answer[i]);
				return true;
			}
			else return false;
		}
		
		for(int i = 0 ; i < input.length ; ++i) {
			if(!selected[i]) {
				selected[i] = true;
				answer[index] = i;
				if(find(index + 1)) return true;
				selected[i] = false;
			}
		}
		return false;
	}
}
