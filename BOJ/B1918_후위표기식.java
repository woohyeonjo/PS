package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B1918_후위표기식 {
	
	static Stack<Character> s;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] input = br.readLine().toCharArray();
		s = new Stack<Character>();
		String ans = "";
		
		for(int i = 0 ; i < input.length ; ++i) {
			if(input[i] >= 'A' && input[i] <= 'Z') ans += input[i];
			else {
				switch(input[i]) {
					case '+':
					case '-':
						while(!s.isEmpty() && (s.peek() == '+' || s.peek() == '-' || s.peek() == '*' || s.peek() == '/')) {
							ans += s.pop();
						}
						s.push(input[i]);
						break;
					case '*':
					case '/':
						while(!s.isEmpty() && (s.peek() == '*' || s.peek() == '/')) {
							ans += s.pop();
						}
						s.push(input[i]);
						break;
					case '(':
						s.push(input[i]);
						break;
					case ')':
						while(s.peek() != '(') {
							ans += s.pop();
						}
						s.pop();
				}
			}
		}
		while(!s.isEmpty()) ans += s.pop();
		
		System.out.println(ans);
	}
}
