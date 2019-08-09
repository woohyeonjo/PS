package JUNGOL.go;

import java.util.Scanner;

public class J1681 {
	static int N;
	static int[][] cost;
	static boolean[] visited;
	
	static int pCost;
	static int mincost;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		cost = new int[N][N];
		visited = new boolean[N];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				cost[i][j] = sc.nextInt();
			}
		}
		
		mincost = Integer.MAX_VALUE;
		pCost = 0;
		
		visited[0] = true;
		for(int i=1; i<N; ++i) {
			if(cost[0][i] != 0) {
				visited[i] = true;
				pCost += cost[0][i];
				dfs(0, i, 1);
				visited[i] = false;
				pCost -= cost[0][i];
			}
		}
		
		System.out.println(mincost);
	}
	
	private static void dfs(int start, int end, int num) {
		if(num == N - 1) {
			if(cost[end][0] != 0) {
				pCost += cost[end][0];
				if(mincost > pCost)
					mincost = pCost;
				pCost -= cost[end][0];
			}
			return;
		}
		
		for(int i=1; i<N; ++i) {
			if(visited[i]==false && cost[end][i]!=0) {
				visited[i] = true;
				pCost += cost[end][i];
				dfs(end, i, num+1);
				visited[i] = false;
				pCost -= cost[end][i];
			}
		}
	}
}
