package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B1406_에디터 {
	static class Editor {
		Stack<Character> left;
		Stack<Character> right;
	
		public Editor() {
			this.left = new Stack<>();
			this.right = new Stack<>();
		}
		
		void L() {
			if(left.isEmpty()) return;
			right.push(left.pop());
		}
		
		void D() {
			if(right.isEmpty()) return;
			left.push(right.pop());
		}
		
		void B() {
			if(left.isEmpty()) return;
			left.pop();
		}
		
		void P(char ch) {
			left.push(ch);
		}
		
		public void print() {
			while(!left.isEmpty()) {
				right.push(left.pop());
			}
			
			while(!right.isEmpty()) {
				System.out.print(right.pop());
			}
		}
	}
	
	static char[] input;
	static int cmd_cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		Editor editor = new Editor();
		
		input = br.readLine().toCharArray();
		cmd_cnt = stoi(br.readLine());
		
		for(int i = 0 ; i < input.length ; ++i) {
			editor.P(input[i]);
		}
		
		for(int i = 0 ; i < cmd_cnt ; ++i) {
			st = new StringTokenizer(br.readLine());
			
			switch(st.nextToken()) {
			case "L":
				editor.L();
				break;
			case "D":
				editor.D();
				break;
			case "B":
				editor.B();
				break;
			case "P":
				editor.P(st.nextToken().charAt(0));
				break;
			}
			
		}
		
		editor.print();
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
