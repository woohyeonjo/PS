package BOJ.go;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class B13418_학교탐방하기 {
	
	static ArrayList<Edge> list;
	static int[] parent;
	static int fatigue;
	static int N, M;
	
	static class Edge{
		int from;
		int to;
		int type;
		
		public Edge(int from, int to, int type) {
			super();
			this.from = from;
			this.to = to;
			this.type = type;
		}

		@Override
		public String toString() {
			return this.from + " " + this.to + " " + this.type;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		list = new ArrayList<>();
		parent = new int[N + 1];
		fatigue = 0;
		
		for(int i = 0 ; i < M + 1 ; ++i){
			if(i == 0){
				union(sc.nextInt(), sc.nextInt());
				int temp = sc.nextInt();
				if(temp == 0) fatigue++;
			} else {
				list.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
			}
		}
		
		int min = fatigue + calFatigue(1);
		int max = fatigue + calFatigue(0);
		
		System.out.println((max * max) - (min * min));
	}
	
	private static int calFatigue(int selector) {
		int result = 0;
		
		for(int i = 0 ; i < N + 1 ; ++i) parent[i] = i;
		if(selector == 0){
			Collections.sort(list, new Comparator<Edge>(){
				@Override
				public int compare(Edge o1, Edge o2) {
					return o1.type - o2.type;
				}
			});
		} else {
			Collections.sort(list, new Comparator<Edge>(){
				@Override
				public int compare(Edge o1, Edge o2) {
					return -(o1.type - o2.type);
				}
			});
		}
		
		for(int i = 0 ; i < M ; ++i){
			if(find(list.get(i).from) == find(list.get(i).to)) continue;
			else {
				if(list.get(i).type == 0) result++;
				union(list.get(i).from, list.get(i).to);
//				System.out.println(list.get(i).from + " 정점과 " + list.get(i).to + "정점을 연결하였습니다.");
//				System.out.println("현재 오르막길 수 :" + result);
			}
		}
//		System.out.println();
		
		return result;	
	}

	static private int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static private void union(int x, int y){
		x = find(x);
		y = find(y);
		if(x != y) parent[y] = x;
	}
}
