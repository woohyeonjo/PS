package Algorithm.SWEA.go;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class S3124 {

	static Queue<Edge> queue;
	static int[] parents;
	
	static int find(int v) {
		if(parents[v] < 0) return v;
		return parents[v] = find(parents[v]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			int V = sc.nextInt();
			int E = sc.nextInt();

			long totalWeight = 0;
			parents = new int[V + 1];
			queue = new PriorityQueue<Edge>();
			Arrays.fill(parents, -1);
			
			for(int e = 1 ; e <= E ; ++e) {
				queue.offer(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
			}
			
			while(!queue.isEmpty()) {
				Edge e = queue.poll();
				if(union(e.v1, e.v2)) {
					totalWeight += e.weight;
				}
			}
			
			System.out.println("#" + t + " " + totalWeight);
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int v1;
		int v2;
		int weight;
		
		public Edge() {
			
		}
		
		public Edge(int v1, int v2, int weight){
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.weight > o.weight) return 1;
			else if( this.weight < o.weight) return -1;
			return 0;
		}
	}
}
