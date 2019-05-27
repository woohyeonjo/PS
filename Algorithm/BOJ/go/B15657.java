package Algorithm.BOJ.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B15657 {
	static int N, M;
	static int[] numbers;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[N];
		
		st = new StringTokenizer(in.readLine());
		
		for(int i = 0 ; i < N ; ++i	) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers);
		
		permutation(0, 0, "");
		
		System.out.println(sb.toString());
	}
	private static void permutation(int index, int start, String str) {
		if(index == M) {
			sb.append(str + "\n");
			return;
		}
		
		for(int i = start ; i < N ; ++i) {
			permutation(index + 1, i, str + numbers[i] + " ");
		}
	}
}
