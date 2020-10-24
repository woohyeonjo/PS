package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1717_집합의표현 {
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		
		for(int i = 0 ; i <= N ; ++i) parent[i] = i;
		
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			if(cmd == 0) {
				union(a, b);
			} else {
				if(find(a) == find(b)) System.out.println("YES");
				else System.out.println("NO");
			}
		}
	}

	private static int find(int a) {
		if(parent[a] == a) return a;
		else return parent[a] = find(parent[a]); 
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		parent[rootA] = rootB;
	}
}
