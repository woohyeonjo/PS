package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16971_배열B의값 {
	static int[][] A;
	static int N, M, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = 0;
		
		A = new int[N][M];
		
		for(int r = 0 ; r < N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < M ; ++c) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 원본배열의 B배열 값 계산
		int origin = 0;
		for(int i = 0 ; i < N ; ++i) {
			if(i == 0 || i == N - 1) origin += rowSum(A[i], false);
			else origin += rowSum(A[i], true);
		}
		ans = origin;
		
		if(N > 2) {
			// 0행과 N - 1행 중에 합이 가장 큰 것과 1 ~ N - 2행 중에 합이 가장 작은 것 교환
			int maxRow = rowSum(A[0], true) > rowSum(A[N - 1], true) ? 0 : N - 1;
			int minRow = -1;
			int minSum = Integer.MAX_VALUE;
			for(int i = 1 ; i < N - 1 ; ++i) {
				int sum = rowSum(A[i], true);
				
				if(minSum > sum) {
					minSum = sum;
					minRow = i;
				}
			}
			
			// 교환한 배열 합
			int changedRow = 0;
			for(int i = 0 ; i < N ; ++i) {
				if(i == 0 || i == N - 1) {
					if(i == maxRow) changedRow += rowSum(A[i], true);
					else changedRow += rowSum(A[i], false);
				} else {
					if(i == minRow) changedRow += rowSum(A[i], false);
					else changedRow += rowSum(A[i], true);
				}
			}
			ans = changedRow > ans ? changedRow : ans;
		}

		if(M > 2) {
			// 0열과 M - 1열 중에 합이 가장 큰 것과 1 ~ M - 2열 중에 합이 가장 작은 것 교환
			int maxCol = colSum(A, 0, true) > colSum(A, M - 1, true) ? 0 : M - 1;
			int minCol = -1;
			int minSum = Integer.MAX_VALUE;
			for(int i = 1 ; i < M - 1 ; ++i) {
				int sum = colSum(A, i, true);
				
				if(minSum > sum) {
					minSum = sum;
					minCol = i;
				}
			}
			
			// 교환한 배열 합 
			int changedCol = 0;
			for(int i = 0 ; i < M ; ++i) {
				if(i == 0 || i == M - 1) {
					if(i == maxCol) changedCol += colSum(A, i, true);
					else changedCol += colSum(A, i, false);
				} else {
					if(i == minCol) changedCol += colSum(A, i, false);
					else changedCol += colSum(A, i, true);
				}
			}
			ans = changedCol > ans ? changedCol : ans;
		}
		
		System.out.println(ans);
	}
	
	private static int colSum(int[][] arr, int c, boolean isMiddle) {
		int result = 0;
		
		if(isMiddle) {
			result = 2 * (arr[0][c] + arr[N - 1][c]);
			
			for(int i = 1 ; i < N - 1 ; ++i) {
				result += 4 * arr[i][c];
			}
		} else {
			result = arr[0][c] + arr[N - 1][c];
			
			for(int i = 1 ; i < N - 1 ; ++i) {
				result += 2 * arr[i][c];
			}
		}
		
		return result;
	}
	
	private static int rowSum(int[] arr, boolean isMiddle) {
		int result = 0;
		
		if(isMiddle) {
			result = 2 * (arr[0] + arr[M - 1]);
			
			for(int i = 1 ; i < M - 1 ; ++i) {
				result += 4 * arr[i];
			}
		} else {
			result = arr[0] + arr[M - 1];
			
			for(int i = 1 ; i < M - 1 ; ++i) {
				result += 2 * arr[i];
			}
		}
		
		return result;
	}
}
