package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 2�?  ?��?�� 1000�? 간선 10000
public class B1260 {
	
	static Queue<Edge> q;
	static Stack<Edge> s;
	static LinkedList<Edge>[] adj;
	static ArrayList<Integer> order;
	static boolean[] visited;
	static int N,M,V;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		q = new LinkedList<Edge>();
		s = new Stack<Edge>();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		adj = new LinkedList[N + 1];
		for(int n = 1 ; n <= N ; ++n) {
			adj[n] = new LinkedList<Edge>();
		}
		int start, end;
		for(int m = 0 ; m < M ; ++m) {
			st = new StringTokenizer(in.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			adj[start].add(new Edge(start, end));
			adj[end].add(new Edge(end, start));
		}
		
		Comparator aComp = new AscendingComp();
		Comparator dComp = new DecendingComp();
				
		for(int n = 1 ; n <= N ; ++n) {
			Collections.sort(adj[n], dComp);
		}
		
		visited = new boolean[N + 1];
		order = new ArrayList<Integer>();
		order.add(V);
		visited[V] = true;
		for(Edge e : adj[V]) s.add(e);
		dfs();
		for(Integer i : order) System.out.print(i + " ");
		System.out.println();
		
		for(int n = 1 ; n <= N ; ++n) {
			Collections.sort(adj[n], aComp);
		}
		
		visited = new boolean[N + 1];
		order = new ArrayList<Integer>();
		order.add(V);
		visited[V] = true;
		for(Edge e : adj[V]) q.add(e);
		bfs();
		for(Integer i : order) System.out.print(i + " ");
	}
	
	private static void bfs() {
		while(!q.isEmpty()) {
			Edge E = q.poll();
			if(visited[E.end]) continue;
			order.add(E.end);
			visited[E.end] = true;
			for(Edge e : adj[E.end]) q.offer(e);
		}
	}

	private static void dfs() {
		while(!s.isEmpty()) {
			Edge E = s.pop();
			if(visited[E.end]) continue;
			order.add(E.end);
			visited[E.end] = true;
			for(Edge e : adj[E.end]) s.add(e);
		}
	}
	
	static class Edge {
		int start, end;

		public Edge(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
	}
	
	static class AscendingComp implements Comparator<Edge>{

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.end - o2.end;
		}
	}
	
	static class DecendingComp implements Comparator<Edge>{
		
		@Override
		public int compare(Edge o1, Edge o2) {
			return -1 * (o1.end - o2.end);
		}
	}
}
