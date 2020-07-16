package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B10775_공항 {
	
	static int[] parent;
	static int G, P, cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());

		parent = new int[G + 1];
		cnt = 0;
		
		for(int i = 1 ; i <= G ; ++i) parent[i] = i;
		
		for(int i = 1 ; i <= P ; ++i) {
			int g = Integer.parseInt(br.readLine());
			
			int target = find(g);
			if(target != 0) {
				cnt++;
				union(target, target - 1);
			} else {
				break;
			}
		}
		
		System.out.println(cnt);
	}

	private static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		parent[rootA] = rootB;
	}
}
