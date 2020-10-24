package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B9613_GCD합 {
	
	static int[] arr, selected;
	static long ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		selected = new int[2];
		ans = 0;
		
		int N = stoi(br.readLine());
		
		for(int i = 0 ; i < N ; ++i) {
			st = new StringTokenizer(br.readLine());
			arr = new int[stoi(st.nextToken())];
			ans = 0;
			
			for(int j = 0 ; j < arr.length ; ++j) {
				arr[j] = stoi(st.nextToken());
			}
			
			select(0, 0);
			System.out.println(ans);
		}
	}
	
	private static void select(int cnt, int idx) {
		if(cnt == 2) {
			ans += gcd(selected[0], selected[1]);
			return;
		}
		
		for(int i = idx ; i < arr.length ; ++i) {
			selected[cnt] = arr[i];
			select(cnt + 1, i + 1);
		}
	}
	
	private static long gcd(long a, long b) {
		if(b == 0) {
			return a;
		} else {
			return gcd(b, a%b);
		}
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
