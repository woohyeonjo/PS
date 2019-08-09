package BOJ.go;

import java.util.Scanner;

public class B14500 {
	static int[][] map;
	static int N, M, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		ans = 0;
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = sc.nextInt();
			}
		}
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				tetroOne(r, c);
				tetroTwo(r, c);
				tetroThree(r, c);
				tetroFour(r, c);
				tetroFive(r, c);
			}
		}
		
		System.out.println(ans);
	}

	private static void tetroFive(int r, int c) {
		int sum = map[r][c];
		
		try {
			sum += map[r][c + 1];
			sum += map[r][c + 2];
			sum += map[r + 1][c + 1];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
		
		try {
		sum = map[r][c];
		sum += map[r + 1][c];
		sum += map[r + 1][c + 1];
		sum += map[r + 2][c];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
		
		try {
		sum = map[r][c];
		sum += map[r + 1][c];
		sum += map[r + 1][c - 1];
		sum += map[r + 2][c];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
		
		try {
		sum = map[r][c];
		sum += map[r + 1][c];
		sum += map[r + 1][c + 1];
		sum += map[r + 1][c - 1];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
	}

	private static void tetroFour(int r, int c) {
		int sum = map[r][c];
		
		try {
		sum += map[r + 1][c];
		sum += map[r + 1][c + 1];
		sum += map[r + 2][c + 1];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
		
		try {
		sum = map[r][c];
		sum += map[r + 1][c];
		sum += map[r + 1][c - 1];
		sum += map[r + 2][c - 1];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
		
		try {
		sum = map[r][c];
		sum += map[r][c + 1];
		sum += map[r + 1][c + 1];
		sum += map[r + 1][c + 2];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
		
		try {
		sum = map[r][c];
		sum += map[r][c + 1];
		sum += map[r - 1][c + 1];
		sum += map[r - 1][c + 2];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
	}

	private static void tetroThree(int r, int c) {
		int sum = map[r][c];
		
		try {
		sum += map[r + 1][c];
		sum += map[r + 2][c];
		sum += map[r + 2][c + 1];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
		
		try {
		sum = map[r][c];
		sum += map[r][c + 1];
		sum += map[r - 1][c + 1];
		sum += map[r - 2][c + 1];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
		
		try {
		sum = map[r][c];
		sum += map[r - 1][c];
		sum += map[r - 1][c + 1];
		sum += map[r - 1][c + 2];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
		
		try {
		sum = map[r][c];
		sum += map[r][c + 1];
		sum += map[r][c + 2];
		sum += map[r + 1][c + 2];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
		
		try {
		sum = map[r][c];
		sum += map[r][c + 1];
		sum += map[r + 1][c + 1];
		sum += map[r + 2][c + 1];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
		
		try {
		sum = map[r][c];
		sum += map[r][c + 1];
		sum += map[r + 1][c];
		sum += map[r + 2][c];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
		
		try {
		sum = map[r][c];
		sum += map[r][c + 1];
		sum += map[r][c + 2];
		sum += map[r - 1][c + 2];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
		
		try {
		sum = map[r][c];
		sum += map[r + 1][c];
		sum += map[r + 1][c + 1];
		sum += map[r + 1][c + 2];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
	}

	private static void tetroTwo(int r, int c) {
		int sum = map[r][c];
		
		try {
		sum += map[r + 1][c];
		sum += map[r][c + 1];
		sum += map[r + 1][c + 1];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
	}

	private static void tetroOne(int r, int c) {
		int sum = map[r][c];
		
		try {
		sum += map[r][c + 1];
		sum += map[r][c + 2];
		sum += map[r][c + 3];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
		
		try {
		sum = map[r][c];
		sum += map[r + 1][c];
		sum += map[r + 2][c];
		sum += map[r + 3][c];
		} catch (Exception e) {}
		ans = sum > ans ? sum : ans;
	}
}
