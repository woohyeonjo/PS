package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B7453_합이0인네정수 {
	
	static long[] A, B, C, D;
	static long[] AB, CD;
	static int N;
	static long cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		A = new long[N];
		B = new long[N];
		C = new long[N];
		D = new long[N];
		AB = new long[N * N];
		CD = new long[N * N];
		
		for(int i = 0 ; i < N ; ++i) {
			st = new StringTokenizer(br.readLine());
			A[i] = stol(st.nextToken());
			B[i] = stol(st.nextToken());
			C[i] = stol(st.nextToken());
			D[i] = stol(st.nextToken());
		}
		
		// AB, CD 만들기
		int idx = 0;
		for(int i = 0 ; i < N ; ++i) {
			for(int j = 0 ; j < N ; ++j) {
				AB[idx++] = A[i] + B[j];
			}
		}
		
		idx = 0;
		for(int i = 0 ; i < N ; ++i) {
			for(int j = 0 ; j < N ; ++j) {
				CD[idx++] = C[i] + D[j];
			}
		}
		
		Arrays.sort(AB);
		Arrays.sort(CD);
		
		cnt = 0;
		for(int i = 0 ; i < AB.length ; ++i) {
//			System.out.println("목표 : " + (-AB[i]));
//			System.out.println(lower_bound(0, CD.length, -AB[i]) + " ~ " + upper_bound(0, CD.length, -AB[i]));
			cnt += upper_bound(0, CD.length, -AB[i]) - lower_bound(0, CD.length, -AB[i]);
		}

		System.out.println(cnt);
	}
	
	private static int upper_bound(int left, int right, long target) {
		while(left < right) {
			int mid = (left + right) / 2;
			if(CD[mid] <= target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}
	
	private static int lower_bound(int left, int right, long target) {
		while(left < right) {
			int mid = (left + right) / 2;
			if(CD[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}
	
	private static long stol(String s) {
		return Long.parseLong(s);
	}
}
