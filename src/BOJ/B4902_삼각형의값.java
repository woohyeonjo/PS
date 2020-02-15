package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B4902_삼각형의값 {
	// 레벨은 삼각형의 높이를 나타낸다. (행) 
	static final int MAX_LEVEL = 400;
	// 각 레벨의 단위삼각형의 갯수는 (레벨 * 2) - 1 이다. (열) 
	static final int MAX_TRIANGLE_CNT = 801;
	
	static int[][] triangle, allSum;
	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = null;
		
		int T = 0;
		while(true) {
			++T;
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());

			if(N == 0) break;
			
			ans = Integer.MIN_VALUE;
			triangle = new int[MAX_LEVEL + 1][MAX_TRIANGLE_CNT + 1];
			allSum = new int[MAX_LEVEL + 1][MAX_TRIANGLE_CNT + 1];
			
			
			for(int r = 1 ; r <= N ; ++r) {
				// 삼각형의 각 열은 (행 * 2) - 1 까지의 길이를 가진다. 
				for(int c = 1 ; c <= (r * 2) - 1 ; ++c) {
					// 삼각형에 주어진 값 넣기 
					triangle[r][c] = stoi(st.nextToken());
					// 각 행 마다 누적합을 계산한다. 
					allSum[r][c] = allSum[r][c - 1] + triangle[r][c];
				}
			}

			for(int r = 1 ; r <= N ; ++r) {
				for(int c = 1 ; c <= (r * 2) - 1 ; ++c) {
					// 완전 탐색을 위해 각 단위삼각형을 기준으로 삼각형을 키워나간다. 
					solve(r, c, c, 0);
				}
			}

			System.out.println(T + ". " + ans);
		}
	}
	
	private static void solve(int r, int cleft, int cright, int sum) {

		// 가장 큰 삼각형의 범위를 벗어나면 리턴한다. 
		if(r > N || r < 1 || cleft < 1 || cright > (r * 2) - 1) return;
		
		// 각 행에서 해당하는 단위삼각형만 포함하기 위해 오른쪽에서 왼쪽 - 1을 뺀다.
		sum += allSum[r][cright] - allSum[r][cleft - 1];
		ans = sum > ans ? sum : ans;
		
		// 시작하는 단위 삼각형의 열 인덱스가 홀수일 경우
		// 올바른 삼각형 
		if(cleft % 2 == 1) solve(r + 1, cleft, cright + 2, sum);
		// 시작하는 단위 삼각형의 열 인덱스가 짝수일 경우
		// 뒤집힌 삼각형 
		else solve(r - 1, cleft - 2, cright, sum);
		
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
