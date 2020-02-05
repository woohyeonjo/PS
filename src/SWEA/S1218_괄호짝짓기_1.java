package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class S1218_괄호짝짓기_1 {
	
	static Stack<Character> s;
	static int T;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = 1;
		String input = null;
		OUTER: while((input = br.readLine()) != null) {
			int len = stoi(input);
			s = new Stack<>();
			
			char[] line = br.readLine().toCharArray();
			
			for(int i = 0 ; i < len ; ++i) {
				switch(line[i]) {
				case '(':
				case '{':
				case '[':
				case '<':
					s.push(line[i]);
					break;
				case ')':
					if(s.peek() != '(') {
						System.out.println("#" + (T++) + " " + 0);
						s.clear();
						continue OUTER;
					}
					s.pop();
					break;
				case '}':
					if(s.peek() != '{') {
						System.out.println("#" + (T++) + " " + 0);
						s.clear();
						continue OUTER;
					}
					s.pop();
					break;
				case ']':
					if(s.peek() != '[') {
						System.out.println("#" + (T++) + " " + 0);
						s.clear();
						continue OUTER;
					}
					s.pop();
					break;
				case '>':
					if(s.peek() != '<') {
						System.out.println("#" + (T++) + " " + 0);
						s.clear();
						continue OUTER;
					}
					s.pop();
					break;
				}
			}
			System.out.println("#" + (T++) + " " + 1);
		}
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
