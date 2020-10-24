package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14395_4연산 {
	
	static class Node {
		long n;
		StringBuilder sb;
		
		Node(long n){
			this.n = n;
			this.sb = new StringBuilder();
		}
	}
	
	static Queue<Node> q;
	static HashSet<Long> set;
	static long S, T;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		S = Long.parseLong(st.nextToken());
		T = Long.parseLong(st.nextToken());
		
		// S와 T가 같으면 즉시 종료 
		if(S == T) {
			System.out.println(0);
			return;
		}
		
		q = new LinkedList<>();
		set = new HashSet<>();
		
		set.add(S);
		q.offer(new Node(S));
		
		StringBuilder ans = bfs();
		
		// 바꿀수 없는 경우에 종료 
		if(ans == null) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(ans.toString());
	}

	private static StringBuilder bfs() {
		while(!q.isEmpty()) {
			
			Node now = q.poll();
			
			if(now.n > Long.MAX_VALUE) return null;
			
			if(now.n == T) {
				return now.sb;
			}
			
			// *
			Node next = copy(now);
			next.n = now.n * now.n;
			if(!set.contains(next.n)) {
				next.sb.append('*');
				set.add(next.n);
				q.offer(next);
			}
			
			// +
			next = copy(now);
			next.n = now.n + now.n;
			if(!set.contains(next.n)) {
				next.sb.append('+');
				set.add(next.n);
				q.offer(next);
			}
			
			// -
			next = copy(now);
			next.n = now.n - now.n;
			if(!set.contains(next.n)) {
				next.sb.append('-');
				set.add(next.n);
				q.offer(next);
			}
			
			// /
			if(now.n != 0) {
				next = copy(now);
				next.n = now.n / now.n;
				if(!set.contains(next.n)) {
					next.sb.append('/');
					set.add(next.n);
					q.offer(next);
				}
			}
		}
		
		return null;
	}
	
	private static Node copy(Node origin) {
		Node result = new Node(origin.n);
		result.sb = new StringBuilder(origin.sb.toString());
		
		return result;
	}
}
