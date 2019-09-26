package BOJ.go;

import java.util.ArrayList;
import java.util.Scanner;

public class B17471_게리맨더링 {
	
	static class Edge {
		int from, to;

		public Edge(int from, int to) {
			super();
			this.from = from;
			this.to = to;
		}
	}
	
	static int[] population;
	static ArrayList<Edge> connection;
	static int[] section;
	static int[] parent;
	static int N, ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		population = new int[N + 1];
		parent = new int[N + 1];
		section = new int[N + 1];
		connection = new ArrayList<>();
		ans = Integer.MAX_VALUE;
		
		for(int i = 1 ; i <= N ; ++i){
			population[i] = sc.nextInt();
		}
		
		int count, from, to;
		for(int i = 1 ; i <= N ; ++i){
			count = sc.nextInt();
			from = i;
			for(int j = 0 ; j < count ; ++j){
				to = sc.nextInt();
				connection.add(new Edge(from, to));
			}
		}
		
		// 두 섹션으로 나누는 모든 경우를 구합니다.
		makeSet(1);
		
		if(ans == Integer.MAX_VALUE) ans = -1;
		System.out.println(ans);
	}
	
	private static void makeSet(int index) {
		if(index > N){
			connect();
			if(isPossible()){
				print(section);
				print(parent);
				System.out.println();
				int current = 0;
				int section1 = 0;
				int section2 = 0;
				for(int i = 1 ; i <= N ; ++i){
					if(section[i] == 1)section1 += population[i];
					else section2 += population[i];
				}
				current = Math.abs(section1 - section2);
				ans = ans > current ? current : ans;
			}
			return;
		}
		
		section[index] = 1;
		makeSet(index + 1);
		section[index] = 2;
		makeSet(index + 1);
	}

	private static void connect() {
		// 입력받았던 간선들 중에서 같은 섹션 사이의 간선만 연결합니다.
		for(int i = 1 ; i <= N ; ++i){
			parent[i] = i;
		}
		
		Edge edge;
		for(int i = 0 ; i < connection.size() ; ++i){
			edge = connection.get(i);
			if(section[edge.from] == section[edge.to]){
				union(edge.from, edge.to);
			}
		}
	}

	private static boolean isPossible() {
		int section1 = 0;
		int section1_root = 0;
		int section2 = 0;
		int section2_root = 0;
		
		for(int i = 1 ; i <= N ; ++i) {
			if(section[i] == 1) {
				section1++;
				section1_root = parent[i];
			}
			else {
				section2++;
				section2_root = parent[i];
			}
		}
		
		// 한쪽 섹션에 속한 선거구의 수가 0인 경우를 체크합니다.
		if(section1 * section2 == 0) return false;
		
		// 같은 섹션이지만 연결되지 않은 경우를 체크합니다.
		for(int i = 1 ; i <= N ; ++i) {
			if(section[i] == 1) {
				if(parent[i] != section1_root) return false;
			}
			else {
				if(parent[i] != section2_root) return false;
			}
		}
		
		return true;
	}

	private static void union(int x, int y){
		x = find(x);
		y = find(y);
		if(x != y) parent[y] = x;
	}

	private static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void print(int[] arr) {
		for(int i = 1 ; i < arr.length ; ++i) System.out.print(arr[i] + " ");
		System.out.println();
	}
}
