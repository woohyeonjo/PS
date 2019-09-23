package BOJ.go;

import java.util.PriorityQueue;
import java.util.Scanner;

public class B1647_도시분할계획 {
	
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
		
		@Override
		public String toString() {
			return from + " " + to + " " + weight;
		}
	}
	
	static int N, M, ans, current;
	static int[] parent;
	static int[] vilige;
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		ans = 0;
		
		parent = new int[N + 1];
		pq = new PriorityQueue<>();
		
		for(int i = 0 ; i < M ; ++i) pq.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
		for(int i = 1 ; i < N + 1 ; ++i) parent[i] = i;
		
		Edge edge;
		int cnt = 0;
		while(!pq.isEmpty() && cnt < N - 2) {
			edge = pq.poll();
			
			if(find(edge.from) != find(edge.to)) {
				ans += edge.weight;
				cnt++;
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
