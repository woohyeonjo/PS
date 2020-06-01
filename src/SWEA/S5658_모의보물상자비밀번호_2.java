package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class S5658_모의보물상자비밀번호_2 {
	
	static HashSet<Integer> set;
	static char[] chest;
	static int N, K, T;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			chest = br.readLine().toCharArray();
			set = new HashSet<>();
			
			for(int i = 0 ; i < N - 1 ; ++i) {
				getPassword();
				spinClockwise();
			}

			ArrayList<Integer> arr = new ArrayList<>();
			
			for(int i : set) arr.add(i);
			
			Collections.sort(arr, Collections.reverseOrder());
			
			System.out.println("#" + t + " " + arr.get(K - 1));
		}
	}
	
	private static void spinClockwise() {
		char temp = chest[N - 1];
		
		for(int i = N - 1 ; i > 0 ; --i) {
			chest[i] = chest[i - 1];
		}
		
		chest[0] = temp;
	}
	

	private static void getPassword() {
		for(int i = 0 ; i < N ; i += N / 4) {
			String password_hex = "";

			for(int j = i ; j < i + N / 4 ; ++j) {
				password_hex += chest[j];
			}
			
			int password_dec = Integer.parseInt(password_hex, 16);
			set.add(password_dec);
		}
	}
}	
