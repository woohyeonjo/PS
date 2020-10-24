package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class S1223_계산기2_1 {
	
	static final int T = 10;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 1 ; t <= T ; ++t) {
			int len = stoi(br.readLine());
			
			String infix = br.readLine();
			String postfix = makePostfix(infix.toCharArray(), len);
			int ans = calPostfix(postfix.toCharArray(), len);
			
			System.out.println("#" + t + " " + ans);
		}
	
	}
	
	private static int calPostfix(char[] postfix, int len) {
		Stack<Integer> s = new Stack<>();
		
		for(int i = 0 ; i < len ; ++i) {
			char cur = postfix[i];
			
			if(cur == '+' || cur == '-' || cur == '*' || cur == '/') {
				int b = s.pop();
				int a = s.pop();
				int result = 0;
				
				switch(cur) {
					case '+':
						result = a + b;
						break;
					case '-':
						result = a - b;
						break;
					case '*':
						result = a * b;
						break;
					case '/':
						result = a / b;
						break;
				}
				s.push(result);
				
			} else {
				s.push(cur - '0');
			}
		}
		
		return (int) s.pop();
	}

	private static String makePostfix(char[] infix, int len) {
		Stack<Character> s = new Stack<>();
		
		String postfix = "";
		
		for(int i = 0 ; i < len ; ++i) {
			char cur = infix[i];
			
			if(cur >= '0' && cur <= '9') {
				postfix += cur;
			} else {
				switch(cur) {
				case '+':
				case '-':
					while(!s.isEmpty() &&
						 (s.peek() == '-' || s.peek() == '+' || s.peek() == '*' || s.peek() == '/')) {
						postfix += s.pop();
					}
					s.push(cur);
					break;
				case '*':
				case '/':
					while(!s.isEmpty() &&
						 (s.peek() == '*' || s.peek() == '/')){
						postfix += s.pop();
					}
					s.push(cur);
					break;
				}
			}
		}
		
		while(!s.isEmpty()) {
			postfix += s.pop();
		}
		
		return postfix;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
