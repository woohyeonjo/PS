package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16936_나3곱2 {

	static boolean[] selected;
	static long[] numbers;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());

		numbers = new long[N];
		selected = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			numbers[i] = Long.parseLong(st.nextToken());
		}

		go(0, new long[N]);
	}

	private static boolean go(int idx, long[] p) {

		if(idx == N) {
			for(int i = 0 ; i < N ; ++i) System.out.print(p[i] + " ");
			return true;
		}

		for(int i = 0 ; i < N; ++i) {
			if(selected[i]) continue;
			
			if(idx == 0) {
				p[idx] = numbers[i];
				selected[i] = true;
				if(go(idx + 1, p)) return true;
				selected[i] = false;
			} else {
				if(p[idx - 1] * 2 == numbers[i] || (p[idx - 1] % 3 == 0 && p[idx - 1] / 3 == numbers[i])) {
					p[idx] = numbers[i];
					selected[i] = true;
					if(go(idx + 1, p)) return true;
					selected[i] = false;
				}
			}
		}
		return false;
	}
}
