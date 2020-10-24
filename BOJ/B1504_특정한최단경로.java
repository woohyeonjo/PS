package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1504_특정한최단경로 {
	
	static class Edge implements Comparable<Edge>{
		int to, weight;
		
		Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static PriorityQueue<Edge> pq;
	static ArrayList<ArrayList<Edge>> adj;
	static int[][] dist;
	static int N, E, first, second;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		E = stoi(st.nextToken());
		
		pq = new PriorityQueue<>();
		dist = new int[3][N + 1];
		adj = new ArrayList<>();
		
		for(int i = 0 ; i < 3 ; ++i) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		for(int i = 0 ; i <= N ; ++i) {
			adj.add(new ArrayList<Edge>());
		}
		
		// 인접리스트 만들기 
		for(int i = 0 ; i < E ; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			int weight = stoi(st.nextToken());
			
			adj.get(to).add(new Edge(from, weight));
			adj.get(from).add(new Edge(to, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		first = stoi(st.nextToken());
		second = stoi(st.nextToken());
		
		getMinimumDistance(1, 0);
		getMinimumDistance(first, 1);
		getMinimumDistance(second, 2);
		
		if(dist[0][N] == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		
		int way1 = dist[0][first] + dist[1][second] + dist[2][N];
		int way2 = dist[0][second] + dist[2][first] + dist[1][N];
		
		if(way1 > way2) {
			System.out.println(way2);
		} else {
			System.out.println(way1);
		}
	}
	
	private static void getMinimumDistance(int start, int i) {
		dist[i][start] = 0;
		pq.offer(new Edge(start, 0));
		dijkstra(i);
	}
	
	private static void dijkstra(int i) {
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			for(Edge ne : adj.get(e.to)) {
				if(dist[i][ne.to] > dist[i][e.to] + ne.weight) {
					dist[i][ne.to] = dist[i][e.to] + ne.weight;
					pq.offer(ne);
				}
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
