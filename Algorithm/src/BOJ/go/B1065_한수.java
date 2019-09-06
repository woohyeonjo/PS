package BOJ.go;

import java.util.Scanner;

public class B1065_한수 {
	static int N;
	static int len, gap, ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		ans = 0;
		
		for(int n = 1 ; n <= N ; ++n){
			if(n < 10) ans++;
			else if(n < 100)ans++;
			else if(n < 1000){
				if( (n / 100) - ((n % 100) / 10) ==
				    ((n % 100) / 10) - (n % 10)
				   ) ans++;
				else if( (n % 10) - ((n % 100) / 10) ==
						((n % 100) / 10) - (n / 100)
					    ) ans++;
			}
			else {
				if( 
					(n / 1000) - ((n % 1000) / 100) == (n / 100) - ((n % 100) / 10) &&
					(n / 100) - ((n % 100) / 10) == ((n % 100) / 10) - (n % 10) &&
				    (n / 1000) - ((n % 1000) / 100) == ((n % 100) / 10) - (n % 10)
				   ) ans++;
				else if(
						(n % 10) - ((n % 100) / 10) == ((n % 100) / 10) - (n / 100) &&
						((n % 100) / 10) - (n / 100) == ((n % 1000) / 100) - (n / 1000) &&
						(n % 10) - ((n % 100) / 10) == ((n % 1000) / 100) - (n / 1000)
					    ) ans++;
			}
		}
		System.out.println(ans);
	}
}