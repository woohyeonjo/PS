package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B16562_친구비 {
	
	static int[] rank;
	static int[] parent;
	static int[] cost;
	static int N, M, K, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
	
		ans = 0;
		rank = new int[N + 1];
		parent = new int[N + 1];
		cost = new int[N + 1];
		
		for(int i = 0 ; i <= N ; ++i) {
			parent[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; ++i) {
			cost[i] = stoi(st.nextToken());
		}
		
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int p1 = stoi(st.nextToken());
			int p2 = stoi(st.nextToken());
			if(find(p1) != find(p2)) {
				union(p1, p2);
			}
		}
		
		int[] min = new int[N + 1];
		Arrays.fill(min, Integer.MAX_VALUE);
		
		for(int i = 1 ; i <= N ; ++i) {
			int group = find(i);
			min[group] = min[group] > cost[i] ? cost[i] : min[group];
		}
		
		for(int i = 1 ; i <= N ; ++i) {
			if(min[i] != Integer.MAX_VALUE) {
				ans += min[i];
			}
		}
		
		if(ans > K) System.out.println("Oh no");
		else System.out.println(ans);
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rank[rootA] < rank[rootB]) {
			parent[rootA] = rootB;
		} else {
			parent[rootB] = rootA;
			
			if(rank[rootA] == rank[rootB]) {
				rank[rootA]++;
			}
		}
	}
	
	private static int find(int a) {
		if(parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}