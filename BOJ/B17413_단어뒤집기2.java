package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class B17413_단어뒤집기2 {
	static Stack<Character> s;
	static Queue<Character> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		s = new Stack<>();
		q = new LinkedList<>();
		
		char[] line = br.readLine().toCharArray();
		int idx = 0;
		
		while(idx < line.length) {
			char ch = line[idx];
			
			if(ch == '<') {
				q.offer(ch);
				while(ch != '>') {
					ch = line[++idx];
					q.offer(ch);
				}
				while(!s.isEmpty()) {
					sb.append(s.pop());
				}
				while(!q.isEmpty()) {
					sb.append(q.poll());
				}
			} else if(ch == ' ') {
				while(!s.isEmpty()) {
					sb.append(s.pop());
				}
				sb.append(ch);
			} else {
				s.push(ch);
			}
			idx++;
		}
		
		while(!s.isEmpty()) {
			sb.append(s.pop());
		}
		
		System.out.println(sb.toString());
	}
}
