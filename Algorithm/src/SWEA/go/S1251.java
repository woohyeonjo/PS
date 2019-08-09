package SWEA.go;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

// ?���? 1?�� 1?���? ?��?���? ?��?��???��?��
public class S1251 {
	
	static Queue<Edge> queue;
	static Vertex[] vList;
	static int[] root;
	static Double E;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = Integer.parseInt(sc.nextLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			
			int N = Integer.parseInt(sc.nextLine());
			String[] X = sc.nextLine().split(" ");
			String[] Y = sc.nextLine().split(" ");
			E = Double.parseDouble(sc.nextLine());
			
			Double totalWeight = 0.0;
			queue = new PriorityQueue<Edge>();
			root = new int[N + 1];
			vList = new Vertex[N];
			Arrays.fill(root, -1);
			
			for (int n = 0 ; n < vList.length ; ++n) {
				vList[n] = new Vertex(n, Integer.parseInt(X[n]), Integer.parseInt(Y[n]));
			}
			
			for (int i = 0 ; i < vList.length ; ++i) {
				for (int j = i + 1 ; j < vList.length ; ++j) {
					queue.offer(new Edge(vList[i], vList[j]));
				}
			}
			
			while(!queue.isEmpty()) {
				Edge e = queue.poll();
				if(union(e.v1.id, e.v2.id)) {
					totalWeight += e.weight;
				}
			}
			
			System.out.println("#" + t + " " + Math.round(totalWeight));
		}
	}
	
	static int find(int v) {
		if(root[v] < 0) return v;
		return root[v] = find(root[v]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot != bRoot) {
			root[bRoot] = aRoot;
			return true;
		}
		return false;
	}
	
	static class Edge implements Comparable<Edge> {
		Vertex v1;
		Vertex v2;
		Double weight;
		
		public Edge(Vertex v1, Vertex v2) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = calWeight();
		}
		
		private Double calWeight(){
			Double L = Math.sqrt(Math.pow((v1.x - v2.x), 2) + Math.pow((v1.y - v2.y), 2));
			return (Math.pow(L, 2) * E);
		}

		@Override
		public int compareTo(Edge o) {
			if(this.weight > o.weight) return 1;
			else if(this.weight < o.weight) return -1;
			return 0;
		}
		
	}
	
	static class Vertex{
		int id;
		int x;
		int y;

		public Vertex(int id, int x, int y) {
			super();
			this.id = id;
			this.x = x;
			this.y = y;
		}
	}
}
