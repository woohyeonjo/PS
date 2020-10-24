package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11659_구간합구하기4 {
	static final int MAX = 100000;
	static int[] psum;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		psum = new int[N + 1];
		
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; ++i) {
			psum[i] = psum[i - 1] + stoi(st.nextToken());
		}
		
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			System.out.println(psum[to] - psum[from - 1]);
		}
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
