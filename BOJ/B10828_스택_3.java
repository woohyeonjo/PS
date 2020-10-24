package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10828_스택_3 {
	static class Stack {
		int pointer;
		int[] memory;
		
		public Stack(int size) {
			this.pointer = 0;
			this.memory = new int[size];
		}
		
		void push(int item) {
			if(pointer == memory.length) {
				System.out.println("메모리가 가득 찼습니다.");
				return;
			}
			memory[pointer++] = item;
		}
		
		int pop() {
			if(pointer == 0) {
				return -1;
			}
			return memory[--pointer];
		}
		
		int size() {
			return pointer;
		}
		
		int empty() {
			return pointer == 0 ? 1 : 0;
		}
		
		int top() {
			if(pointer == 0) {
				return -1;
			}
			return memory[pointer - 1];
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		Stack stack = new Stack(10000);
		
		int N = stoi(br.readLine());
		
		for(int i = 0 ; i < N ; ++i) {
			st = new StringTokenizer(br.readLine());
			switch(st.nextToken()) {
			case "push":
				stack.push(stoi(st.nextToken()));
				break;
			case "pop":
				System.out.println(stack.pop());
				break;
			case "size":
				System.out.println(stack.size());
				break;
			case "empty":
				System.out.println(stack.empty());
				break;
			case "top":
				System.out.println(stack.top());
				break;
			}
		}
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}