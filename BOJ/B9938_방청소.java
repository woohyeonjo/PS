package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B9938_방청소 {
	
	static int[] parent;
	static boolean[] visited;
	static int N, L;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		visited = new boolean[L + 1];
		parent = new int[L + 1];
		
		for(int i = 1 ; i <= L ; ++i) {
			parent[i] = i;
		}
		
		for(int i = 1 ; i <= N ; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(!visited[a]) {
				visited[a] = true;
				union(a, b);
				continue;
			}
			
			if(!visited[b]) {
				visited[b] = true;
				union(b, a);
				continue;
			}
			
			if(!visited[find(a)]) {
				visited[find(a)] = true;
				union(a, b);
				continue;
			}
			
			if(!visited[find(b)]) {
				visited[find(b)] = true;
				union(b, a);
				continue;
			}
			
			sb.append("SMECE\n");
		}
		
		System.out.println(sb);
	}
	
	private static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		sb.append("LADICA\n");
		parent[rootA] = rootB;
	}
}
