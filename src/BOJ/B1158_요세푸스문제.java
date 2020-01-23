package BOJ;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class B1158_요세푸스문제 {
	
	static Deque<Integer> dq;
	static int N, K;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		dq = new LinkedList<Integer>();
		
		for(int i = 1 ; i <= N ; ++i) {
			dq.addLast(i);
		}
		
		while(!dq.isEmpty()) {
			for(int i = 0 ; i < K - 1 ; ++i) {
				dq.addLast(dq.removeFirst());
			}
			sb.append(dq.removeFirst() + ", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(">");
		System.out.println(sb.toString());
	}
}
