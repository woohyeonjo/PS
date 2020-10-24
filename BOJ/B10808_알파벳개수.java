package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B10808_알파벳개수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] alphabet = new int[26];
		char[] input = br.readLine().toCharArray();
		
		for(int i = 0 ; i < input.length ; ++i) {
			int ascii = input[i] - 'a';
			
			alphabet[ascii]++;
		}
		
		for(int i = 0 ; i < alphabet.length ; ++i) {
			System.out.print(alphabet[i] + " ");
		}
	}
}
