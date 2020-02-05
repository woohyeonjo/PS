package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1759_암호만들기 {
	
	static int L, C;
	static char[] alphabets;
	static ArrayList<String> ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = stoi(st.nextToken());
		C = stoi(st.nextToken());
		
		alphabets = new char[C];
		ans = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < C ; ++i) {
			alphabets[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alphabets);
		
		select(0, 0, "");
		
		for(String s : ans) System.out.println(s);
	}
	
	private static void select(int idx, int depth, String password) {
		if(depth == L) {
			if(check(password)) {
				ans.add(password);
			}
			return;
		}
		
		for(int i = idx ; i < C ; ++i) {
			select(i + 1, depth + 1, password + alphabets[i]);
		}
	}

	private static boolean check(String password) {
		int	consonant = 0; // 자음 
		int collection = 0; // 모음
		
		for(int i = 0 ; i < L ; ++i) {
			char cur = password.charAt(i);
			
			if(cur == 'a' || cur == 'e' || cur == 'i' || cur == 'o' || cur == 'u') {
				collection++;
			} else {
				consonant++;
			}
		}
		
		if(consonant >= 2 && collection >= 1) return true;
		else return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
