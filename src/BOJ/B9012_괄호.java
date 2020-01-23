package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B9012_괄호 {
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
			if(pointer == 0) return 'N';
			return memory[--pointer];
		}
		
		boolean isEmpty() {
			return pointer == 0;
		}
		
		void clean() {
			this.memory = new char[this.memory.length];
			this.pointer = 0;
		}
	}

	static int T;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = stoi(br.readLine());
		Stack stack = new Stack(100);
		
		OUTER: for(int t = 0 ; t < T ; ++t) {
			stack.clean();
			
			char[] ps = br.readLine().toCharArray();
			
			for(int i = 0 ; i < ps.length ; ++i) {
				if(ps[i] == '(') {
					stack.push(ps[i]);
				} else if(ps[i] == ')') {
					if(stack.pop() == 'N') {
						System.out.println("NO");
						continue OUTER;
					}
				}
			}
			if(stack.isEmpty()) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
