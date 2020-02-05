package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10845_큐 {
	private static class Queue {
		int front, rear;
		int[] memory;
		
		public Queue(int size) {
			this.front = 0;
			this.rear = 0;
			this.memory = new int[size];
		}
		
		void push(int item) {
			memory[rear++] = item;
		}
		
		int pop() {
			if(front == rear) return -1;
			return memory[front++];
		}
		
		int size() {
			return rear - front;
		}
		
		int empty() {
			if(front == rear) return 1;
			return 0;
		}
		
		int front() {
			if(front == rear) return -1;
			return memory[front];
		}

		int back() {
			if(front == rear) return -1;
			return memory[rear - 1];
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int cmd_cnt = stoi(br.readLine());
		Queue q = new Queue(10000);
		
		for(int i = 0 ; i < cmd_cnt ; ++i) {
			st = new StringTokenizer(br.readLine());
			
			switch(st.nextToken()) {
			case "push":
				q.push(stoi(st.nextToken()));
				break;
			case "pop":
				System.out.println(q.pop());
				break;
			case "size":
				System.out.println(q.size());
				break;
			case "empty":
				System.out.println(q.empty());
				break;
			case "front":
				System.out.println(q.front());
				break;
			case "back":
				System.out.println(q.back());
				break;
			}
		}
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
