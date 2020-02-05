package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B9019_DSLR {
	
	private static class Calculator {
		int register;
		String history;
		
		Calculator(int register, String history){
			this.register = register;
			this.history = history;
		}
	}
	
	static int T, A, B;
	static Queue<Calculator> q = new LinkedList<>();
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int i = 0 ; i < T ; ++i) {
			A = sc.nextInt();
			B = sc.nextInt();
			
			visited = new boolean[10000];
			visited[A] = true;
			q.offer(new Calculator(A, ""));
			bfs();
			q.clear();
		}
	}

	private static void bfs() {
		while(!q.isEmpty()) {
			Calculator cal = q.poll();
			
			if(cal.register == B) {
				System.out.println(cal.history);
				return;
			}
			
			int d = D(cal.register);
			int s = S(cal.register);
			int l = L(cal.register);
			int r = R(cal.register);
			
			if(!visited[d]) {
				visited[d] = true;
				q.offer(new Calculator(d, cal.history + "D"));
			}
			if(!visited[s]) {
				visited[s] = true;
				q.offer(new Calculator(s, cal.history + "S"));
			}
			if(!visited[l]) {
				visited[l] = true;
				q.offer(new Calculator(l, cal.history + "L"));
			}
			if(!visited[r]) {
				visited[r] = true;
				q.offer(new Calculator(r, cal.history + "R"));
			}
		}
	}
	
	private static int D(int number) {
		return (number * 2) % 10000;
	}
	
	private static int S(int number) {
		return number == 0 ? 9999 : number - 1;
	}
	
	private static int L(int number) {
		return ((number % 1000) * 10) + (number / 1000);
	}
	
	private static int R(int number) {
		return ((number % 10) * 1000) + (number / 10);
	}

}
