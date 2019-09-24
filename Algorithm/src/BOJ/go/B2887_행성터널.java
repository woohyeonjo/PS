package BOJ.go;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class B2887_행성터널 {

	static class Star {
		int id;
		int x, y, z;

		public Star(int id, int x, int y, int z) {
			super();
			this.id = id;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	static class Gate implements Comparable<Gate> {
		int from, to, weight;

		public Gate(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Gate o) {
			return this.weight - o.weight;
		}
	}

	static ArrayList<Star> galaxy;
	static PriorityQueue<Gate> pq;
	static int[] parent;
	static int N, ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		parent = new int[N + 1];
		pq = new PriorityQueue<>();
		galaxy = new ArrayList<>();
		
		for(int i = 0 ; i < N ; ++i) {
			galaxy.add(new Star(i, sc.nextInt(), sc.nextInt(), sc.nextInt()));
			parent[i] = i;
		}

		Collections.sort(galaxy, new Comparator<Star>(){
			@Override
			public int compare(Star o1, Star o2) {
				return o1.x - o2.x;
			}
		});
		for(int i = 1 ; i < N ; ++i){
			pq.offer(new Gate(galaxy.get(i).id,
					  galaxy.get(i - 1).id,
					  Math.abs(galaxy.get(i).x - galaxy.get(i - 1).x)));
		}
		
		Collections.sort(galaxy, new Comparator<Star>(){
			@Override
			public int compare(Star o1, Star o2) {
				return o1.y - o2.y;
			}
		});
		for(int i = 1 ; i < N ; ++i){
			pq.offer(new Gate(galaxy.get(i).id,
					  galaxy.get(i - 1).id,
					  Math.abs(galaxy.get(i).y - galaxy.get(i - 1).y)));
		}
		
		Collections.sort(galaxy, new Comparator<Star>(){
			@Override
			public int compare(Star o1, Star o2) {
				return o1.z - o2.z;
			}
		});
		for(int i = 1 ; i < N ; ++i){
			pq.offer(new Gate(galaxy.get(i).id,
							  galaxy.get(i - 1).id,
							  Math.abs(galaxy.get(i).z - galaxy.get(i - 1).z)));
		}
		
		Gate gate;
		while(!pq.isEmpty()){
			gate = pq.poll();
			
			if(find(gate.from) == find(gate.to)) continue;
			else {
				ans += gate.weight;
				union(gate.from, gate.to);
			}
		}
		
		System.out.println(ans);
		
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x != y) parent[y] = x;
	}

	private static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}
}
