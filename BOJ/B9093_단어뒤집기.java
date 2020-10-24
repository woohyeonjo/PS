package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B9093_단어뒤집기 {
	
	static class Stack {
		int pointer;
		char[] memory;
		
		public Stack(int size) {
			this.pointer = 0;
			this.memory = new char[size];
		}
		
		void push(char item) {
			memory[pointer++] = item;
		}
		
		char pop() {
			return memory[--pointer];
		}
		
		boolean isEmpty() {
			return pointer == 0;
		}
	}
	
	static int T;
	static Stack stack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		stack = new Stack(30);
		T = stoi(br.readLine());
		
		for(int t = 0 ; t < T ; ++t) {
			st = new StringTokenizer(br.readLine());
			
			while(st.hasMoreTokens()) {
				char[] temp = st.nextToken().toCharArray();
				
				for(int i = 0 ; i < temp.length ; ++i) {
					stack.push(temp[i]);
				}
				
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				sb.append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
