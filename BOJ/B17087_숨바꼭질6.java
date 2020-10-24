package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17087_숨바꼭질6 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = stoi(st.nextToken());
		long S = stol(st.nextToken());
		
		long[] children = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; ++i) {
			children[i] = Math.abs(stol(st.nextToken()) - S);
		}
		System.out.println(gcd(children));
	}
	
	private static long gcd(long[] arr) {
		if(arr.length < 2) {
			return arr[0];
		}
		
		long result = gcd(arr[0], arr[1]);

		for(int i = 2 ; i < arr.length ; ++i) {
			result = gcd(result, arr[i]);
		}
		
		return result;
	}
	
	private static long gcd(long a, long b) {
		if(b == 0) {
			return a;
		}
		
		return gcd(b, a%b);
	}
	
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	private static long stol(String s) {
		return Long.parseLong(s);
	}
}
