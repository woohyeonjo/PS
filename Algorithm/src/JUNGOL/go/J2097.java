package JUNGOL.go;

import java.util.Scanner;

public class J2097 {
	static int N, M;
	static int[][] time;
	static boolean[] visited;
	static int min;
	static String result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		time = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				time[i][j] = sc.nextInt();
			}
		}
		min = time[1][M];
		result = "1 " + M;
		visited[1] = true;
		go(1, M, 0, "1");
		System.out.println(min);
		System.out.println(result);
		sc.close();
	}

	private static void go(int i, int dest, int count, String s) {
		if (i == dest) {
			if (min > count) {
				min = count;
				result = s;
			}
			return;
		}
		for (int j = N; j >= 2; j--) {
			if (!visited[j] && time[i][j] != 0 && min >= count + time[i][j]) {
				visited[j] = true;
				go(j, dest, count + time[i][j], s + " " + j);
				visited[j] = false;
			}

		}
	}

}
