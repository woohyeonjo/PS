package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1765_닭싸움팀정하기 {
	
	static class Edge {
		int from, to, type;
		
		Edge(int to, int type){
			this.to = to;
			this.type = type;
		}
		
		@Override
		public String toString() {
			return "[" + to + ", " + type + "]";
		}
	}

	static Queue<Edge> q;
	static ArrayList<Edge>[] adj;
	static boolean[] visited;
	static int N, M, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		q = new LinkedList<>();
		adj = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		ans = 0;
		
		for(int i = 1 ; i <= N ; ++i) adj[i] = new ArrayList<>();
		
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			
			String type = st.nextToken();
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			if(type.equals("E")) {
				adj[from].add(new Edge(to, 0));
				adj[to].add(new Edge(from, 0));
			} else {
				adj[from].add(new Edge(to, 1));
				adj[to].add(new Edge(from, 1));
			}
		}
		
		for(int i = 1 ; i <= N ; ++i) {
			visited[i] = true;
			
			for(Edge e : adj[i]) {
				q.offer(e);
				visited[e.to] = true;
			}
			
			findFriend(i);
			visited = new boolean[N + 1];
			q.clear();
		}
		
		for(int i = 1 ; i <= N ; ++i) {
			if(visited[i]) continue;
			visited[i] = true;
			
			for(Edge e : adj[i]) {
				if(e.type == 1) {
					q.offer(e);
					visited[e.to] = true;
				}
			}
			
			makeTeam();
		}
		
		System.out.println(ans);
	}

	private static void makeTeam() {
		ans++;
		
		while(!q.isEmpty()) {
			Edge e = q.poll();
			
			if(e.type == 0) continue;
			
			for(Edge ne : adj[e.to]) {
				if(visited[ne.to]) continue;
				
				if(ne.type == 1) {
					q.offer(ne);
					visited[ne.to] = true;
				}
			}
		}
	}

	private static void findFriend(int start) {
		ArrayList<Edge> temp = new ArrayList<>();
		
		while(!q.isEmpty()) {
			temp.clear();
			
			Edge e = q.poll();
			
			for(Edge ne : adj[e.to]) {
				if(visited[ne.to]) continue;
				
				if(e.type == 0 && ne.type == 0) {
					temp.add(ne);
					visited[ne.to] = true;
				}
			}
			
			for(Edge ne : temp) {
				adj[start].add(new Edge(ne.to, 1));
				adj[ne.to].add(new Edge(start, 1));
			}
		}
	}
	
}
