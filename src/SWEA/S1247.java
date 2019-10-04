package SWEA;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class S1247 {
	
	static int N;
	static Node temp, home, company;
	static ArrayList<Node> costomerList;
	static ArrayList<Node[]> route;
	static Node[] points;
	static boolean[] isVisited;
	
	public static void main(String[] args) throws CloneNotSupportedException {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; ++t) {
			int distance;
			int optimumDistance = Integer.MAX_VALUE;
			N = sc.nextInt();
			
			costomerList = new ArrayList<Node>();
			route = new ArrayList<Node[]>();
			isVisited = new boolean[N];
			points = new Node[N + 2];
			
			company = new Node(sc.nextInt(), sc.nextInt());
			home = new Node(sc.nextInt(), sc.nextInt());
			temp = new Node();
			
			for(int n = 0 ; n < N ; ++n) {
				temp.x = sc.nextInt();
				temp.y = sc.nextInt();
				costomerList.add((Node) temp.clone());
			}
			
			points[0] = company;
			permutation(1);
			
			for(Node[] nodeArr : route) {
				distance = 0;
				
				for(int i = 0 ; i < nodeArr.length - 1 ; ++i) {
					distance += nodeArr[i].distanceTo(nodeArr[i + 1]);
				}
				if(distance < optimumDistance) optimumDistance = distance;
			}
			
			System.out.println("#" + t + " " + optimumDistance);
		}
	}

	private static void permutation(int index) {
		if(index == N + 1) {
			points[N + 1] = home;
			route.add(points.clone());
			return;
		}
		
		for(int i = 0 ; i < N ; ++i) {
			if(!isVisited[i]) {
				points[index] = costomerList.get(i);
				isVisited[i] = true;
				permutation(index + 1);
				isVisited[i] = false;
			}
		}
	}

	static class Node implements Cloneable{
		public int x, y;
		
		public Node() {
			
		}
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
		
		@Override
		public String toString() {
			return "[" + x + ", " + y + "]";
		}

		public int distanceTo(Node n) {
			return Math.abs(x - n.x) + Math.abs(y - n.y);
		}
	}
}

