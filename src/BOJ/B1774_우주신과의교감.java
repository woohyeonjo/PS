package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B1774_우주신과의교감 {
	
	static class Edge implements Comparable<Edge> {
		int from, to;
		double dist;
		
		Edge(int from, int to, double dist){
			this.from = from;
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.dist > o.dist) return 1;
			else return -1;
		}
	}
	
	static class God {
		int x, y;
		
		God(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int[] parent;
	static God[] gods;
	static ArrayList<Edge> edges;
	static int N, M;
	static double ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		ans = 0;
		edges = new ArrayList<>();
		gods = new God[N + 1];
		parent = new int[N + 1];
		
		// 부모배열 초기화 
		for(int i = 1 ; i <= N ; ++i) {
			parent[i] = i;
		}
		
		// 신들의 좌표 입력 
		for(int i = 1 ; i <= N ; ++i) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken());
			int y = stoi(st.nextToken());
			gods[i] = new God(x, y);
		}
		
		// 이미 연결된 신 표시
		for(int i = 1 ; i <= M ; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			
			union(from, to);
		}
		
		// 모든 신들 간의 간선
		for(int i = 1 ; i < N ; ++i) {
			for(int j = i + 1 ; j <= N ; ++j) {
				double dist = getDistance(gods[i], gods[j]);
				edges.add(new Edge(i, j, dist));
			}
		}
		
		Collections.sort(edges);
		
		for(Edge e : edges) {
			if(find(e.from) == find(e.to)) continue;
			
			union(e.from, e.to);
			ans += e.dist;
		}

		System.out.printf("%.2f", ans);
	}
	
	private static int find(int a) {
		if(parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}
	
	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		parent[rootB] = rootA;
	}
	
	private static double getDistance(God g1, God g2) {
		double result = 0;
		result = Math.sqrt(Math.pow(g2.x - g1.x, 2) + Math.pow(g2.y - g1.y, 2));

		return result;
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
