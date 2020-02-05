package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11724_연결요소의개수 {
	static class Node {
		int from, to;
		
		Node(int from, int to){
			this.from = from;
			this.to = to;
		}
	}
	
	static int[] parent;
	static int N, M, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		ans = 0;
		parent = new int[N + 1];
		for(int i = 1 ; i < N + 1 ; ++i) {
			parent[i] = i ;
		}
		
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			
			union(from, to);
		}
		
		for(int i = 1 ; i < N + 1 ; ++i) {
			if(parent[i] == i) ans++;
		}
		
		System.out.println(ans);
	}
	
	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA != rootB) {
			parent[rootB] = rootA;
			return true;
		} else {
			return false;
		}
	}
	
	private static int find(int a) {
		if(parent[a] == a) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
