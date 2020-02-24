package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B12904_A와B {
	
	static Queue<StringBuilder> q;
	static String S, T;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = br.readLine();
		T = br.readLine();
		
		q = new LinkedList<>();
		StringBuilder sb = new StringBuilder(T);
		
		if(bfs(sb)) System.out.println(1);
		else System.out.println(0);
	}

	private static boolean bfs(StringBuilder sb) {
		q.offer(sb);
		
		while(!q.isEmpty()) {
			StringBuilder now = q.poll();
			
			if(now.toString().equals(S)) {
				return true;
			}
			
			// 목표 문자열의 길이보다 커야한다.
			if(now.length() > S.length()) {
				StringBuilder next = null;
				// 뒤에서 A를 제거하기 
				if(now.charAt(now.length() - 1) == 'A') {
					next = new StringBuilder(now.substring(0, now.length() - 1));
					q.offer(next);
				}
				
				// 뒤에서 B를 제거하고 뒤집기
				if(now.charAt(now.length() - 1) == 'B') {
					next = new StringBuilder(now.subSequence(0, now.length() - 1));
					next.reverse();
					q.offer(next);
				}
			}
		}
		return false;
	}

}
