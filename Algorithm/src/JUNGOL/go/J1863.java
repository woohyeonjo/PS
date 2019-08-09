package JUNGOL.go;

import java.util.Arrays;
import java.util.Scanner;

public class J1863 {

	static int[] parents;
	static int ans;

	static int find(int v) {
		if (parents[v] < 0)
			return v;
		return parents[v] = find(parents[v]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String temp[] = sc.nextLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);

		ans = N;
		parents = new int[N + 1];
		Arrays.fill(parents, -1);

		for (int m = 0; m < M; ++m) {
			if(union(sc.nextInt() - 1, sc.nextInt() - 1)) {
				ans--;
			}
		}

		System.out.println(ans);
	}
}
