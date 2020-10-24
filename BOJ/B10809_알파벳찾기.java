package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B10809_알파벳찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] arr = new int[26];
		char[] input = br.readLine().toCharArray();
		
		Arrays.fill(arr, -1);
		
		for(int i = 0 ; i < input.length ; ++i) {
			int ascii = input[i] - 'a';
			if(arr[ascii] == -1) arr[ascii] = i;
		}
		
		for(int i = 0 ; i < arr.length ; ++i) {
			System.out.print(arr[i] + " ");
		}
		
	}
}
