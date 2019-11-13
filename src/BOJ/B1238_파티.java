package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1238_파티 {
	
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
	static int N, M, X, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		q = new PriorityQueue<>();
		adj = new ArrayList[N + 1];
		for(int i = 1 ; i < N + 1 ; ++i) adj[i] = new ArrayList<>();
		dist = new int[N + 1];
		
		int from, to, cost;
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			adj[from].add(new Edge(to, cost));
		}
		
		int current, go, come;
		for(int i = 1 ; i < N + 1 ; ++i) {
			init(i);
			q.offer(new Edge(i, 0));
			go = dijkstra(X);
			
			init(X);
			q.offer(new Edge(X, 0));
			come = dijkstra(i);
			
			current = go + come;
			ans = current > ans ? current : ans; 
		}
		
		System.out.println(ans);
	}

	private static int dijkstra(int target) {
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
		
		return dist[target];
	}

	private static void init(int start) {
		for(int i = 1 ; i < N + 1 ; ++i) dist[i] = Integer.MAX_VALUE;
		dist[start] = 0;
	}
}
