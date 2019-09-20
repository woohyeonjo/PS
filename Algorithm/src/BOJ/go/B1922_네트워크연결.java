package BOJ.go;

import java.util.PriorityQueue;
import java.util.Scanner;

public class B1922_네트워크연결 {
	
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static int N, M, ans;
	static int[] parent;
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		ans = 0;
		
		pq = new PriorityQueue<>();
		parent = new int[N + 1];
		
		for(int i = 0 ; i < N ; ++i) parent[i] = i;
		
		for(int i = 0 ; i < M ; ++i) {
			pq.offer(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
		}
		
		Edge edge;
		while(!pq.isEmpty()) {
			edge = pq.poll();
			
			if(find(edge.from) == find(edge.to)) continue;
			else {
				ans += edge.weight;
				union(edge.from, edge.to);
			}
		}
		
		System.out.println(ans);
		
	}
	
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x != y) parent[y] = x;
	}
	
	private static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
}
