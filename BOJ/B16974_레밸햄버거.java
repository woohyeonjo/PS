package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16974_레밸햄버거 {
	
	static long[] h, p;
	static int N;
	static long X;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		X = Long.parseLong(st.nextToken());
		
		h = new long[N + 1];
		p = new long[N + 1];
		
		h[0] = 1;
		p[0] = 1;
		
		for(int i = 1 ; i <= N ; ++i) {
			h[i] = 1 + h[i - 1] + 1 + h[i - 1] + 1;
			p[i] = p[i - 1] + 1 + p[i - 1];
		}
		
		System.out.println(solve(N, X));
	}

	private static long solve(int n, long x) {
		
		// n이 감소하여 0이 될 수 있다.
		// 레벨 0 햄버거는 패티 한장이다.
		if (n == 0) {
			if (x == 0) return 0;
			else if (x == 1) return 1;
		}
		
		if(x == 1) {
			// 가장 첫번째 재료는 무조건 번이다.
			return 0;
		} else if(x <= 1 + h[n - 1]) {
			// x가 중간 패티 위치 보다 작으면 첫번째 번을 빼고 이전 레밸의 햄버거를 다시 확인 
			return solve(n - 1, x - 1);
		} else if(x == 1 + h[n - 1] + 1) {
			// x가 중간 패티 위치면 이전 레벨의 패티 수 + 1
			return p[n - 1] + 1;
		} else if(x <= 1 + h[n - 1] + 1 + h[n - 1]) {
			// x가 중간 패티 위치 보다 크면 중간 패티까지의 패티 수 + 이전 레벨의 햄버거 다시 확인
			return p[n - 1] + 1 + solve(n - 1, x - (1 + h[n - 1] + 1));
		} else {
			// 두 번째 이전 레벨의 햄버거 보다 크면
			return p[n - 1] + 1 + p[n - 1];
		}
	}

}
