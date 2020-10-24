package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10866_덱 {
	
//	push_front X: 정수 X를 덱의 앞에 넣는다.
//	push_back X: 정수 X를 덱의 뒤에 넣는다.
//	pop_front: 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//	pop_back: 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//	size: 덱에 들어있는 정수의 개수를 출력한다.
//	empty: 덱이 비어있으면 1을, 아니면 0을 출력한다.
//	front: 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//	back: 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	
	static class Node {
		int data;
		Node llink, rlink;
		
		public Node(int data) {
			this.data = data;
		}
		
		public Node(int data, Node llink, Node rlink) {
			this.data = data;
			this.llink = llink;
			this.rlink = rlink;
		}
	}
	
	static class Deque {
		Node front;
		Node rear;
		
		Deque(){
			this.front = null;
			this.rear = null;
		}
		
		int empty() {
			if(front == null) return 1;
			return 0;
		}
		
		int size() {
			int cnt = 0;
			
			Node node = front;
			while(node != null) {
				cnt++;
				node = node.rlink;
			}
			
			return cnt;
		}
		
		void pushFront(int item) {
			Node newNode = new Node(item);
			if(empty() == 1) {
				front = newNode;
				rear = newNode;
			} else {
				newNode.rlink = front;
				newNode.llink = null;
				front.llink = newNode;
				front = newNode;
			}
		}
		
		void pushBack(int item) {
			Node newNode = new Node(item);
			if(empty() == 1) {
				front = newNode;
				rear = newNode;
			} else {
				newNode.llink = rear;
				newNode.rlink = null;
				rear.rlink = newNode;
				rear = newNode;
			}
		}
		
		int popFront() {
			if(empty() == 1) {
				return -1;
			}
			
			int result = front.data;
			
			if(front.rlink == null) {
				front = null;
				rear = null;
			} else {
				front = front.rlink;
				front.llink = null;
			}
			
			return result;
		}
		
		int popBack() {
			if(empty() == 1) {
				return -1;
			}
			
			int result = rear.data;
			
			if(rear.llink == null) {
				front = null;
				rear = null;
			} else {
				rear = rear.llink;
				rear.rlink = null;
			}
			
			return result;
		}
		
		int front() {
			if(empty() == 1) return -1;
			return front.data;
		}
		
		int back() {
			if(empty() == 1) return -1;
			return rear.data;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int cmd_cnt = stoi(br.readLine());
		Deque dq = new Deque();
		
		for(int i = 0 ; i < cmd_cnt ; ++i) {
			st = new StringTokenizer(br.readLine());
			switch(st.nextToken()) {
			case "push_front":
				dq.pushFront(stoi(st.nextToken()));
				break;
			case "push_back":
				dq.pushBack(stoi(st.nextToken()));
				break;
			case "pop_front":
				System.out.println(dq.popFront());
				break;
			case "pop_back":
				System.out.println(dq.popBack());
				break;
			case "front":
				System.out.println(dq.front());
				break;
			case "back":
				System.out.println(dq.back());
				break;
			case "size":
				System.out.println(dq.size());
				break;
			case "empty":
				System.out.println(dq.empty());
				break;
			}
		}
		
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
