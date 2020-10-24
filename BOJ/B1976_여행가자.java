package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1976_여행가자 {

	static int[] parent;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		M = stoi(br.readLine());
		
		parent = new int[N + 1];
		
		for(int i = 1 ; i <= N ; ++i) {
			parent[i] = i;
		}
		
		for(int r = 1 ; r <= N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1 ; c <= N ; ++c) {
				int edge = stoi(st.nextToken());
				
				if(edge == 1) {
					if(find(r) != find(c)) {
						union(r, c);
					}
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int root = find(stoi(st.nextToken()));
		for(int i = 0 ; i < M - 1 ; ++i) {
			if(root != find(stoi(st.nextToken()))) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
	
	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		parent[rootB] = rootA;
	}
	
	private static int find(int a) {
		if(parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
