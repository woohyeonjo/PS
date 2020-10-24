package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class B1719_택배 {
	
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
	
	static PriorityQueue<Edge> q;
	static ArrayList<Edge>[] adj;
	static int[] dist, trace;
	static int V, E;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		q = new PriorityQueue<>();
		adj = new ArrayList[V + 1];
		for(int i = 1 ; i < V + 1 ; ++i) adj[i] = new ArrayList<>();
		dist = new int[V + 1];
		trace = new int[V + 1];
		
		int from, to, weight;
		for(int i = 0 ; i < E ; ++i) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			adj[from].add(new Edge(to, weight));
			adj[to].add(new Edge(from, weight));
		}
		
		for(int i = 1 ; i < V + 1 ; ++i) {
			init(i);
			dijkstra(i);
			print(i);
		}
	}

	private static void print(int start) {
		int current;
		
		for(int i = 1 ; i < V + 1 ; ++i) {
			if(i == start) System.out.print("- ");
			else if(trace[i] == start) System.out.print(i + " ");
			else {
				current = i;
				while(true) {
					if(trace[current] == start) {
						System.out.print(current + " ");
						break;
					}
					else current = trace[current];
				}
			}
		}
		System.out.println();
	}

	private static void dijkstra(int start) {
		q.offer(new Edge(start, 0));
		
		while(!q.isEmpty()) {
			Edge now = q.poll();
			
			for(Edge next : adj[now.to]) {
				if(dist[next.to] > dist[now.to] + next.weight) {
					dist[next.to] = dist[now.to] + next.weight;
					trace[next.to] = now.to;
					q.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}
	}

	private static void init(int start) {
		for(int i = 1 ; i < V + 1 ; ++i) dist[i] = Integer.MAX_VALUE;
		dist[start] = 0;
	}
}
