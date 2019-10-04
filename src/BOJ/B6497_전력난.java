package BOJ;


import java.util.PriorityQueue;
import java.util.Scanner;

public class B6497_전력난 {
	static class Road implements Comparable<Road> {
		int x, y, z;

		public Road(int x, int y, int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public int compareTo(Road o) {
			return this.z - o.z;
		}
	}
	
	static PriorityQueue<Road> pq;
	static int[] parent;
	static int N, M, amount, save;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			if(N == 0 && M == 0) break;
			
			save = 0;
			amount = 0;
			pq = new PriorityQueue<>();
			parent = new int[N + 1];
			
			for(int i = 1 ; i < N + 1 ; ++i) parent[i] = i;
			for(int i = 0 ; i < M ; ++i) pq.offer(new Road(sc.nextInt(), sc.nextInt(), sc.nextInt()));
			
			Road road;
			while(!pq.isEmpty()) {
				road = pq.poll();
				amount += road.z;
				
				if(find(road.x) == find(road.y)) continue;
				save += road.z;
				union(road.x, road.y);
			}
			System.out.println(amount - save);
		}
	}
	
	private static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x != y) parent[y] = x;
	}
}
