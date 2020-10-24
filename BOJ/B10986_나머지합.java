package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10986_나머지합 {
	
	static final int MAX = 1000000 + 1;
	static long[] psum, cnt;
	static long N, M, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stol(st.nextToken());
		M = stol(st.nextToken());
		
		psum = new long[MAX];
		cnt = new long[(int)M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; ++i) {
			long num = stol(st.nextToken());
			// 부분합의 나머지를 저장한다.
			psum[i] = (psum[i - 1] + num) % M;
			// 나머지가 같은 부분합을 그룹화한다.
			cnt[(int) psum[i]]++;
			// 부분합의 나머지가 0인 경우는 답이므로 바로 카운팅한다. 
			if(psum[i] == 0) ans++;
		}
		
		// 각 부분합의 나머지가 같은 그룹에서 nC2를 구한다.
		for(int i = 0 ; i < M ; ++i) {
			ans += cnt[i] * (cnt[i] - 1) / 2;
		}
		System.out.println(ans);
	}
	
	private static long stol(String s) {
		return Long.parseLong(s);
	}
}
