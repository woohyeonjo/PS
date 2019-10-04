package BOJ;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class B1753 {
	static ArrayList<Edge>[] adj;
	static int[] distance;
	static boolean[] visited;
	static Queue<Edge> q;
	static int V, E, start;
	static class Edge implements Comparable<Edge> {
		int to, weight;

		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		start = sc.nextInt();
		
		distance = new int[V + 1];
		visited = new boolean[V + 1];
		adj = new ArrayList[V + 1];
		for(int i = 1 ; i <= V ; ++i) adj[i] = new ArrayList<Edge>();
		q = new PriorityQueue<Edge>();
		
		for(int i = 0 ; i < E ; ++i) adj[sc.nextInt()].add(new Edge(sc.nextInt(), sc.nextInt()));
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		q.offer(new Edge(start, distance[start]));
		
		while(!q.isEmpty()) {
			Edge e = q.poll();
			if(visited[e.to]) continue;
			visited[e.to] = true;
			
			for(Edge ne : adj[e.to]) {
				if(distance[ne.to] > distance[e.to] + ne.weight) {
					distance[ne.to] = distance[e.to] + ne.weight;
					q.offer(new Edge(ne.to, distance[ne.to]));
				}
			}
			
		}
		
		for(int i = 1 ; i < distance.length ; ++i) {
			if(distance[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(distance[i]);
		}
	}
}
