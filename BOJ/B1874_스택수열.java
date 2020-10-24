package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1874_스택수열 {
	
	static class Stack {
		int pointer;
		int[] memory;
		
		public Stack(int size) {
			this.pointer = 0;
			this.memory = new int[size];
		}
		
		void push(int item) {
			sb.append("+\n");
			this.memory[pointer++] = item;
		}
		
		int pop() {
			if(this.pointer == 0) return -1;
			sb.append("-\n");
			return this.memory[--pointer];
		}
	}
	
	static int num, target;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int N = stoi(br.readLine());
		
		Stack stack = new Stack(100000);
		num = 1;
		
		for(int i = 1 ; i <= N ; ++i) {
			target = stoi(br.readLine());
			
			if(target >= num) {
				while(target >= num) {
					stack.push(num++);
				}
				stack.pop();
			} else {
				int item = stack.pop();
				
				while(target != item) {
					if(item == -1) {
						System.out.println("NO");
						return;
					}
					item = stack.pop();
				}
			}
			
		}
		System.out.println(sb.toString());
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
