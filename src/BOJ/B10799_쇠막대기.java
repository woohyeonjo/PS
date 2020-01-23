package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B10799_쇠막대기 {
	
	static Stack<Character> s;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] line = br.readLine().toCharArray();

		int cnt = 0;
		s = new Stack<>();
		
		for(int i = 0 ; i < line.length ; ++i) {
			char ch = line[i];
			
			if(ch == '(') {
				s.push(ch);
			} else if(ch == ')') {
				s.pop();
				if(line[i - 1] == ')') {
					cnt += 1;
				} else {
					cnt += s.size();
				}
			}
		}
		System.out.println(cnt);
	}
}
