package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16917_양념반후라이드반 {
	
	static int A, B, C, X, Y;
	static long ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
	
		// 한 마리씩 사는 것 보다 반마리 두 개가 저렴할 때 
		if(A + B > 2 * C) {
			if(X > Y) {
				ans += Y * C * 2;
				
				int remain = X - Y;
				// 양념보다 반마리 두 개가 저렴 
				if(A > 2 * C) {
					ans += remain * C * 2;
				} else {
					ans += remain * A;
				}
			} else {
				ans += X * C * 2;
				
				int remain = Y - X;
				
				if(B > 2 * C) {
					ans += remain * C * 2;
				} else {
					ans += remain * B;
				}
			}
		// 한 마리씩 사는 것이 더 저렴할 때 
		} else {
			if(X > Y) {
				ans += Y * (A + B);
				
				int remain = X - Y;
				
				if(A > 2 * C) {
					ans += remain * C * 2;
				} else {
					ans += remain * A;
				}
			} else {
				ans += X * (A + B);
				
				int remain = Y - X;
				
				if(B > 2 * C) {
					ans += remain * C * 2;
				} else {
					ans += remain * B;
				}
			}
		}
		
		System.out.println(ans);
	}
}
