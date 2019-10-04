package BOJ;


import java.util.Scanner;

public class B17406_배열돌리기4 {
	
	static int[][] arr;
	static int[][] copied;
	static int[][] rotation_arg;
	static int[] rotation_order;
	static boolean[] visited;
	static int N, M, K;
	static int ans, current;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		arr = new int[N + 1][M + 1];
		rotation_arg = new int[K + 1][3 + 1];
		rotation_order = new int[K];
		visited = new boolean[K + 1];
		ans = Integer.MAX_VALUE;
		
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= M ; ++c) {
				arr[r][c] = sc.nextInt();
			}
		}
		
		for(int i = 1 ; i <= K ; ++i) {
			for(int j = 1 ; j <= 3 ; ++j) {
				rotation_arg[i][j] = sc.nextInt();
			}
		}
		
		permutation(0);
		
		System.out.println(ans);
		
	}

	private static void permutation(int order) {
		
		if(order == K) {
			arr_copy();
			go();
			return;
		}
		
		for(int i = 1 ; i <= K ; ++i) {
			if(visited[i]) continue;
			rotation_order[order] = i;
			visited[i] = true;
			permutation(order + 1);
			visited[i] = false;
		}
		
	}

	private static void arr_copy() {
		copied = new int[N + 1][M + 1];
		
		for(int i = 1 ; i <= N ; ++i) {
			for(int j = 1 ; j <= M ; ++j) {
				copied[i][j] = arr[i][j];
			}
		}
	}

	private static void go() {
		for(int i = 0 ; i < K ; ++i) {
			int order = rotation_order[i];
			rotate(rotation_arg[order][1], rotation_arg[order][2], rotation_arg[order][3]);
		}
		current = findMinRow();
		ans = current < ans ? current : ans;
	}

	private static int findMinRow() {
		int sum;
		int min = Integer.MAX_VALUE;
		
		for(int r = 1 ; r < copied.length ; ++r) {
			sum = 0;
			for(int c = 1 ; c <copied[r].length ; ++c) {
				sum += copied[r][c];
			}
			min = sum < min ? sum : min;
		}
		return min;
	}

	private static void rotate(int R, int C, int S) {
		if(S < 1) return;
		
		int start = copied[R - S][C - S];
		
		for(int r = R - S ; r < R + S ; ++r) {
			copied[r][C - S] = copied[r + 1][C - S];
		}
		
		for(int c = C - S ; c < C + S ; ++c) {
			copied[R + S][c] = copied[R + S][c + 1];
		}
		
		for(int r = R + S ; r > R - S ; --r) {
			copied[r][C + S] = copied[r - 1][C + S];
		}
		
		for(int c = C + S ; c > C - S + 1 ; --c) {
			copied[R - S][c] = copied[R - S][c - 1];
		}
		
		copied[R - S][C - S + 1] = start;
		
		rotate(R, C, S - 1);
	}
}
