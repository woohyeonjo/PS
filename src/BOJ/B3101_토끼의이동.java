package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3101_토끼의이동 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input1 = br.readLine();
		n = Integer.parseInt(input1.split(" ")[0]);
		int t = Integer.parseInt(input1.split(" ")[1]);
		long x = 0;
		long y = 0;
		long sum = 1;
		
		String input2 = br.readLine();
		
		for (int p = 0; p < t; p++) {
			char c = input2.charAt(p);
			switch (c) {
			case 'U':
				y--;
				break;
			case 'D':
				y++;
				break;
			case 'L':
				x--;
				break;
			case 'R':
				x++;
				break;
			}
			if(x>=0 && x<n && y>=0 && y<n) {
				sum += value(x, y);
			}
		}
		System.out.println(sum);
	}

	static int n;

	static long value(long x, long y) {
		long m = x + y;
		if (m < n) {
			if (m % 2 == 0) {
				return 1 + m * (m + 1) / 2 + x;
			} else {
				return 1 + m * (m + 1) / 2 + y;
			}
		} else {
			if (m % 2 == 0) {
				return n * n - ((n - 1) * 2 - m) * ((n - 1) * 2 + 1 - m) / 2 - (n - 1) + x;
			} else {
				return n * n - ((n - 1) * 2 - m) * ((n - 1) * 2 + 1 - m) / 2 - (n - 1) + y;
			}
		}
	}
}
