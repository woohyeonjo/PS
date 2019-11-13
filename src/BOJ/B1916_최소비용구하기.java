package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1916_최소비용구하기 {
	
	static class Edge implements Comparable<Edge> {
		int to, cost;

		public Edge(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost; 
		}
	}
	
	static PriorityQueue<Edge> q;
	static ArrayList<Edge>[] adj;
	static int[] dist;
	static int V, E, start, end;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);
		
		q.offer(new Edge(start, 0));
		
		dijkstra();
		System.out.println(dist[end]);
	}

	private static void dijkstra() {
		Edge now;
		
		while(!q.isEmpty()) {
			now = q.poll();
			
			for(Edge next : adj[now.to]) {
				if(dist[next.to] > dist[now.to] + next.cost) {
					dist[next.to] = dist[now.to] + next.cost;
					q.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}
	}

	private static void init(BufferedReader br) throws IOException {
		StringTokenizer st;
		
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		
		q = new PriorityQueue<>();
		adj = new ArrayList[V + 1];
		for(int i = 1 ; i < V + 1 ; ++i) adj[i] = new ArrayList<>();
		dist = new int[V + 1];
		
		int from, to, cost;
		for(int i = 0 ; i < E ; ++i) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			adj[from].add(new Edge(to, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		for(int i = 1 ; i < V + 1 ; ++i) dist[i] = Integer.MAX_VALUE;
		dist[start] = 0;
	}
}
