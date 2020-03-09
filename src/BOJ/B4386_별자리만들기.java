package BOJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class B4386_별자리만들기 {
	
	static class Star {
		double x, y;
		
		Star(double x, double y){
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int from, to;
		double cost;
		
		Edge(int from, int to, double cost){
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.cost > o.cost) return 1;
			else return -1;
		}
	}
	
	static int[] parent;
	static ArrayList<Star> stars;
	static ArrayList<Edge> edges;
	static int N;
	static double ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		ans = 0;
		parent = new int[N + 1];
		stars = new ArrayList<>();
		edges = new ArrayList<>();
		
		for(int i = 0 ; i < N ; ++i) {
			stars.add(new Star(sc.nextDouble(), sc.nextDouble()));
		}
		
		// 부모배열 초기화 
		for(int i = 1 ; i <= N ; ++i) {
			parent[i] = i;
		}
		
		for(int i = 0 ; i < N - 1 ; ++i) {
			Star from = stars.get(i);
			for(int j = i + 1 ; j < N ; ++j) {
				Star to = stars.get(j);
				
				double distance = getDistance(from, to);
				edges.add(new Edge(i, j, distance));
			}
		}
		
		Collections.sort(edges);
		
		for(Edge e : edges) {
			if(find(e.from) == find(e.to)) continue;
			
			union(e.from, e.to);
			ans += e.cost;
		}
		
		System.out.println(ans);
	}
	
	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		parent[rootB] = rootA;
	}
	
	private static int find(int a) {
		if(parent[a] == a) {
			return a;
		}
		
		return parent[a] = find(parent[a]);
	}
	
	private static double getDistance(Star s1, Star s2) {
		double result = 0;
		
		result = Math.sqrt(Math.pow((s2.x - s1.x), 2) + Math.pow((s2.y - s1.y), 2));
		return result;
		
	}
}
