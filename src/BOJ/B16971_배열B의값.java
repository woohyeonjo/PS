package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16971_배열B의값 {
	
	static int[][] A;
	static int[] row, col;
	static int N, M, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = 0;
		
		A = new int[N][M];
		row = new int[N];
		col = new int[M];
		
		for(int r = 0 ; r < N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < M ; ++c) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0 ; i < N ; ++i) row[i] = i;
		for(int i = 0 ; i < M ; ++i) col[i] = i;
		
		select(0, 0, true, new int[2]);
		select(0, 0, false, new int[2]);
		
		System.out.println(ans);
	}

	private static void select(int idx, int cnt, boolean isRow, int[] change) {
		
		if(cnt == 2) {
			int value = 0;
			
			if(isRow) {
				// 행 바꾸기 
				row[change[0]] = change[1];
				row[change[1]] = change[0];
				value = calValue();
//				System.out.println(change[0] + "행과 " + change[1] + "행을 교환합니다. " );
//				System.out.println(value);
				ans = value > ans ? value : ans;
				row[change[0]] = change[0];
				row[change[1]] = change[1];
			} else {
				// 열 바꾸기 
				col[change[0]] = change[1];
				col[change[1]] = change[0];
				value = calValue();
//				System.out.println(change[0] + "열과 " + change[1] + "열을 교환합니다. " );
//				System.out.println(value);
				ans = value > ans ? value : ans;
				col[change[0]] = change[0];
				col[change[1]] = change[1];
			}
			
			return;
		}
		
		int max = isRow ? N : M;
		
		for(int i = idx ; i < max ; ++i) {
			change[cnt] = i;
			select(i + 1, cnt + 1, isRow, change);
		}
	}
	
	private static int calValue() {
		int result = 0;
		
		for(int r = 0 ; r < N - 1 ; ++r) {
			for(int c = 0 ; c < M - 1 ; ++c) {
				result += A[row[r]][col[c]] + A[row[r + 1]][col[c]] + A[row[r + 1]][col[c + 1]] + A[row[r]][col[c + 1]];
			}
		}
		
		return result;
	}
}
