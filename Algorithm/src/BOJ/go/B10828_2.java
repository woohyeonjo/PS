package BOJ.go;

import java.util.Scanner;

public class B10828_2 {
	
	final static int MAX_SIZE = 10000;
	static int[] stack = new int[MAX_SIZE];
	static int pointer = 0;
	
	public static void push(int item) {
		stack[pointer++] = item;
	}
	
	public static int pop() {
		if(empty() == 1) return -1;
		return stack[--pointer];
	}
	
	public static int size() {
		return pointer;
	}
	
	public static int empty() {
		return pointer == 0 ? 1 : 0;
	}
	
	public static int top() {
		if(empty() == 1) return -1;
		return stack[pointer - 1];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		
		for(int n = 0 ; n < N ; ++n) {
			String[] input = sc.nextLine().split(" ");
			String command = input[0];
			int item = 0;
			if(input.length > 1) {
				item = Integer.parseInt(input[1]);
			}
			
			switch(command) {
			case "push":
				push(item);
				break;
			case "pop":
				System.out.println(pop());
				break;
			case "size":
				System.out.println(size());
				break;
			case "empty":
				System.out.println(empty());
				break;
			case "top":
				System.out.println(top());
				break;
				
			}
		}
	}
}
