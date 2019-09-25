package BOJ.go;

import java.util.ArrayList;
import java.util.Collections;
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
			return -(this.z - o.z);
		}
	}
	
	static final int MOD = 1000000000;
	static ArrayList<Edge> list;
	static int[] parent, size;
	static int N, M;
	static long total;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		list = new ArrayList<>();
		parent = new int[N + 1];
		size = new int[N + 1];
		total = 0;
		
		init();
		int x,y,z;
		for(int i = 0 ; i < M ; ++i) {
			x = sc.nextInt();
			y = sc.nextInt();
			z = sc.nextInt();
			list.add(new Edge(x, y, z));
			total += z;
		}
		
		Collections.sort(list);
		
		Edge edge;
		long sum = 0;
		for(int i = 0 ; i < M ; ++i){
			edge = list.get(i);
			int X = find(edge.x);
			int Y = find(edge.y);
			
			if(X != Y){
				sum += ((size[X] * size[Y]) % MOD) * total % MOD;
				sum = sum % MOD;
				union(X, Y);
			}
			total -= edge.z;
		}
		System.out.println(sum);
	}
	
	private static void init() {
		for(int i = 0 ; i < N + 1 ; ++i) {
			parent[i] = i;
			size[i] = 1;
		}
	}

	private static void union(int x, int y) {
		parent[y] = x;
		size[x] += size[y];
		size[y] = 1;
	}

	private static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}
}
