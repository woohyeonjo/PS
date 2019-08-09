package BOJ.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B15656 {
	static int[] input;
	static int N, M;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		String[] temp = in.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);

		input = new int[N];
		temp = in.readLine().split(" ");
		for (int i = 0; i < N; ++i) {
			input[i] = Integer.parseInt(temp[i]);
		}
		Arrays.sort(input);

		permutation(0, "");
		
		System.out.println(sb.toString());
	}

	private static void permutation(int index, String str) {
		if (index == M) {
			sb.append(str + "\n");
			return;
		}

		for (int i = 0 ; i < N; ++i) {
			permutation(index + 1, str + input[i] + " ");
		}
	}
}
