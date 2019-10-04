package SWEA;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class S1249 {

	static Vertex[][] vList;
	static int[] root;
	static Queue<Road> queue;
	
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			int N = sc.nextInt();
			sc.nextLine();
			
			vList = new Vertex[N][N];
			root = new int[N * N + 1];
			queue = new PriorityQueue<Road>();
			Arrays.fill(root, -1);
			int count = 1;
			int totalWeight = 0;
			
			for(int row = 0 ; row < N ; ++row) {
				char[] temp = sc.nextLine().toCharArray();
				for(int col = 0 ; col < N ; ++col) {
					vList[row][col] = new Vertex(count++, row, col, temp[col] - '0');
				}
			}
			
			// ?���? ?���?
			for(int row = 0; row < N ; ++row) {
				for(int col = 0 ; col < N ; ++col) {
					if(col < N - 1) queue.offer(new Road(vList[row][col], vList[row][col + 1]));
					if(row < N - 1) queue.offer(new Road(vList[row][col], vList[row + 1][col]));
				}
			}
			
			while(!queue.isEmpty()) {
				Road r = queue.poll();
				if(union(r.v1.id, r.v2.id)) {
					totalWeight += r.weight;
				}
			}
			
			System.out.println("#" + t + " " + totalWeight);
			
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
	
	static class Road implements Comparable<Road>{
		Vertex v1;
		Vertex v2;
		int weight;
		
		public Road(Vertex v1, Vertex v2) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = v1.weight + v2.weight;
		}

		@Override
		public int compareTo(Road o) {
			if(this.weight > o.weight) return 1;
			else if(this.weight < o.weight) return -1;
			return 0;
		}
	}
	
	static class Vertex{
		int id;
		int row;
		int col;
		int weight;
		
		public Vertex(int id, int row, int col, int weight) {
			this.id = id;
			this.row = row;
			this.col = col;
			this.weight = weight;
		}
	}
}
