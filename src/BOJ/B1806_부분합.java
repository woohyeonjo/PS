package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1806_부분합 {
	static int N, S;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		S = stoi(st.nextToken());

		numbers = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			numbers[i] = stoi(st.nextToken());
		}

		int left = 0;
		int min = Integer.MAX_VALUE;
		int length = 0;
		long sum = 0;
		for (int right = 0; right < N; ++right) {
			sum += numbers[right];
			
			if (sum >= S) {
				while(sum >= S) {
					length = right - left + 1;
					min = min > length ? length : min;
					sum -= numbers[left++];
				}
			}
		}
		
		if(min == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(min);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
