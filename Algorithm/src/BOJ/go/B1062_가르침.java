package BOJ.go;

import java.util.Scanner;

public class B1062_가르침 {

	static boolean[] candidate;
	static boolean[] selected;
	static String[] words;
	static int N, K, ans, pre;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		ans = Integer.MIN_VALUE;
		
		if(K < 5) {
			System.out.println(0);
			return;
		} else if (K == 26){
			System.out.println(N);
			return;
		}
		
		ans = 0;
		pre = 0;
		words = new String[N];
		candidate = new boolean[26];
		selected = new boolean[26];
		
		String word;
		for(int i = 0 ; i < N ; ++i) {
			word = sc.next();
			word = word.replaceAll("[antic]", "");
			if(word.length() == 0){
				pre++;
			} else {
				for(char c : word.toCharArray()) candidate[c - 'a'] = true;
				words[i] = word;
			}
		}
		
		learnning(5, 0);
		
		System.out.println(ans + pre);
	}

	private static void learnning(int cnt, int start) {
		if(cnt == K){
			
//			for(int i = 0 ; i < 26 ; ++i){
//				if(selected[i]){
//					System.out.print((char)(i + 'a') + " ");
//				}
//			}
//			System.out.println();
			
			int current = 0;
			char[] word;
			boolean canRead;
			for(int i = 0 ; i < words.length ; ++i){
				canRead = true;
				word = words[i].toCharArray();
				
				for(char c : word){
					if(!selected[c - 'a']){
						canRead = false;
						break;
					}
				}
				
				if(canRead) current++;
			}
			
			ans = current > ans ? current : ans;
			return;
		}
		
		for(int i = start ; i < 26 ; ++i){
			if(candidate[i]){
				selected[i] = true;
				learnning(cnt + 1, i + 1);
				selected[i] = false;
			}
		}
	}
}
