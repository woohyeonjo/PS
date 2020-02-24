package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B12871_무한문자열 {
	
	static String s, t;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		s = br.readLine();
		t = br.readLine();
		int slen = s.length();
		int tlen = t.length();
		
		if(slen == tlen) {
			if(s.equals(t)) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
			return;
		}
		
		int lcm = lcm(slen, tlen);
		
		StringBuilder ss = new StringBuilder(s);
		StringBuilder tt = new StringBuilder(t);
		
		for(int i = 0 ; i < lcm / slen - 1 ; ++i) {
			ss.append(s);
		}
		
		for(int i = 0 ; i < lcm / tlen - 1 ; ++i) {
			tt.append(t);
		}
		
		if(ss.toString().equals(tt.toString())) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}
	
	private static int gcd(int a, int b) {
		if(b == 0) return a;
		
		return gcd(b, a % b);
	}
	
	private static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}
}
