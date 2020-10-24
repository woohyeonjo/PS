package BOJ;

import java.util.Scanner;

public class B10824_네수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long ans = 0;
		
		for(int i = 0 ; i < 2 ; ++i) {
			ans += Long.parseLong("" + sc.nextInt() + sc.nextInt());
		}
		
		System.out.println(ans);
	}
}
