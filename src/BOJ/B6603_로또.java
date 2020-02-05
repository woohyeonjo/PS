package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B6603_로또 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		while(st.countTokens() != 1) {
			int K = stoi(st.nextToken());
			
			int[] origin = new int[K];
			
			for(int i = 0 ; i < K ; ++i) {
				origin[i] = stoi(st.nextToken());
			}
			
			Arrays.sort(origin);
			
			select(0, 0, new int[6], origin);
			
			
			st = new StringTokenizer(br.readLine());
			System.out.println();
		}
	}

	private static void select(int depth, int idx, int[] picked, int[] origin) {
		if(depth == 6) {
			print(picked);
			return;
		}
		
		for(int i = idx ; i < origin.length ; ++i) {
			picked[depth] = origin[i];
			select(depth + 1, i + 1, picked, origin);
		}
	}

	private static void print(int[] arr) {
		for(int i = 0 ; i < arr.length ; ++i) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
