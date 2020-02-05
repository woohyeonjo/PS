package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1018_체스판다시칠하기 {
	
	static int[][] board;
	static int N, M, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		ans = Integer.MAX_VALUE;
		
		for(int r = 0 ; r < N ; ++r) {
			char[] line = br.readLine().toCharArray();
			for(int c = 0 ; c < M ; ++c) {
				if(line[c] == 'W') {
					board[r][c] = 0;
				} else {
					board[r][c] = 1;
				}
			}
		}
		
		for(int r = 0 ; r <= N - 8 ; ++r) {
			for(int c = 0 ; c <= M - 8 ; ++c) {
				int min = check(r, c);
				ans = ans > min ? min : ans;
			}
		}
		
		System.out.println(ans);
	}

	private static int check(int R, int C) {
		int min = 64;
		int cnt = 0;
		boolean flag = false;
		
		// 첫 번째 셀이 검은색일 때 1
		for(int r = R ; r < R + 8 ; ++r) {
			flag = !flag;
			for(int c = C ; c < C + 8 ; ++c) {
				if(flag && board[r][c] == 0) cnt++;
				if(!flag && board[r][c] == 1) cnt++;
				flag = !flag;
			}
		}
		min = min > cnt ? cnt : min;
		cnt = 0;
		flag = false;
		
		// 첫 번째 셀이 흰색일 때 0
		for(int r = R ; r < R + 8 ; ++r) {
			flag = !flag;
			for(int c = C ; c < C + 8 ; ++c) {
				if(flag && board[r][c] == 1) cnt++;
				if(!flag && board[r][c] == 0) cnt++;
				flag = !flag;
			}
		}
		min = min > cnt ? cnt : min;
		return min;
	}
}
