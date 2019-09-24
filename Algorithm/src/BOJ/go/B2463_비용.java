package BOJ.go;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class B2463_비용 {

	static class Edge implements Comparable<Edge>{
		int x, y, z;

		public Edge(int x, int y, int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public int compareTo(Edge o) {
			return this.z - o.z;
		}
	}
	
	static PriorityQueue<Edge> pq;
	static int[] parent;
	static int[] copy;
	static int N, M, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		pq = new PriorityQueue<>();
		parent = new int[N + 1];
		ans = 0;
		
		for(int i = 1 ; i < N + 1 ; ++i) parent[i] = i;
		int x,y,z;
		for(int i = 0 ; i < M ; ++i) {
			x = sc.nextInt();
			y = sc.nextInt();
			z = sc.nextInt();
			pq.offer(new Edge(x, y, z));
			union(parent, x, y);
		}
		
		Edge edge;
//		for(int u = 1 ; u < N ; ++u){
//			for(int v = u + 1 ; v < N + 1 ; ++v){
				copy();
				int u = 2;
				int v = 6;
				while(!pq.isEmpty()){
					edge = pq.poll();
					if(find(copy, u) == find(copy, v)){
						for(int i = 1 ; i < N + 1 ; ++i) System.out.print(copy[i]);
						System.out.println();
						System.out.println("(" + edge.x + ", " + edge.y + ")");
						ans += edge.z;
						copy[edge.x] = edge.x;
						copy[edge.y] = edge.y;
					} else break;
				}
//			}
//		}
		System.out.println(ans);
	}

	private static void copy() {
		copy = new int[N + 1];
		for(int i = 0 ; i < N + 1 ; ++i) copy[i] = parent[i];
	}

	private static void union(int[] arr, int x, int y) {
		x = find(arr, x);
		y = find(arr, y);
		if(x != y) arr[y] = x;
	}

	private static int find(int[] arr, int x) {
		if(arr[x] == x) return x;
		else return arr[x] = find(arr, arr[x]);
	}
}
