package CodingTest.ElevenStreet;

import java.util.ArrayList;
import java.util.Collections;

public class Solution1 {
	public static void main(String[] args) {
		String character = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		ArrayList<Character> output = new ArrayList<>();
		int max = Integer.MIN_VALUE;
		int maxNum = Integer.MIN_VALUE;
		int N = 10;
		int temp;
		
		for(int k = 2 ; k < 10 ; ++k) {
			while(N != 0) {
				output.add(character.charAt(N % k));
				N /= k;
			}
			Collections.reverse(output);
			
			for(char c : output) System.out.print(c);
			System.out.println();
			
			temp = 1;
			for(char c : output) {
				if(c == '0') continue;
				temp *= (c - '0');
			}
			
			if(temp >= max) {
				max = temp;
				maxNum = k;
			}
			
			output.clear();
			N = 10;
		}
		System.out.println(max + " " + maxNum);
	}
}
