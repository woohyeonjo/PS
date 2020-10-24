package BOJ;


import java.util.ArrayList;
import java.util.Scanner;

public class B1062_가르침 {
	
	static boolean[] learn;
	static ArrayList<String> words;
	static int N, K, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		if(K < 5) {
			System.out.println(0);
			return;
		} else if (K == 26) {
			System.out.println(N);
			return;
	    }
		
		ans = 0;
		learn = new boolean[26];
		words = new ArrayList<String>();
		
		String word;
		for(int i = 0 ; i < N ; ++i) {
			word = sc.next();
			word = word.replace("[antic]", "");
			words.add(word);
		}
		
		learn[0] = true;
		learn[2] = true;
		learn[8] = true;
		learn[13] = true;
		learn[19] = true;
		
		backTracking(5, 0);
		
		System.out.println(ans);
	}

	private static void backTracking(int cnt, int start) {
		
		if(cnt == K) {
			boolean isRead;
			int current = 0;
			
			for(String word : words) {
				isRead = true;
				for(char c : word.toCharArray()) {
					if(!learn[c - 'a']) {
						isRead = false;
						break;
					}
				}
				
				if(isRead) current++;
			}
			
			ans = current > ans ? current : ans;
			return;
		}
		
		for(int i = start + 1 ; i < 26 ; ++i) {
			if(!learn[i]) {
				learn[i] = true;
				backTracking(cnt + 1, i);
				learn[i] = false;
			}
		}
	}
}
