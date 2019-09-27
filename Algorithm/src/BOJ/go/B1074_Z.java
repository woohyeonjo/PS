package BOJ.go;

import java.util.Scanner;

public class B1074_Z {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		solve();
	}
	
	private static void solve() {
	    int n = sc.nextInt();
	    int r = sc.nextInt();
	    int c = sc.nextInt();
	    int ans = 0;
	    int y = (int) Math.pow(2, n) / 2;
	    int x = y;
	 
	    while (n-- > 0) {
	        int temp = (int) Math.pow(2, n) / 2;
	        int skip = (int) Math.pow(4, n);
	 
	        if (r < y && c < x) {
	            // 1
	            x -= temp;
	            y -= temp;
	        } else if (r < y && x <= c) {
	            // 2
	            x += temp;
	            y -= temp;
	            ans += skip;
	        } else if (y <= r && c < x) {
	            // 3
	            x -= temp;
	            y += temp;
	            ans += skip * 2;
	        } else {
	            // 4
	            x += temp;
	            y += temp;
	            ans += skip * 3;
	        }
	    }
	    System.out.println(ans);
	}
}
