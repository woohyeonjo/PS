package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16235_나무재테크_2 {
	
	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	static ArrayList<Integer>[][] tree;
	static int[][] nutrition;
	static int[][] dead;
	static int[][] A;
	static int N, M, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		tree = new ArrayList[N + 1][N + 1];
		nutrition = new int[N + 1][N + 1];
		dead = new int[N + 1][N + 1];
		A = new int[N + 1][N + 1];

		for(int r = 1 ; r <= N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1 ; c <= N ; ++c) {
				A[r][c] = Integer.parseInt(st.nextToken());
				nutrition[r][c] = 5;
				tree[r][c] = new ArrayList<Integer>();
			}
		}
		
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			
			tree[r][c].add(a);
		}
		
		for(int i = 0 ; i < K ; ++i) {
			spring();
			summer();
			fall();
			winter();
		}
		
		int ans = 0;
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= N ; ++c) {
				ans += tree[r][c].size();
			}
		}
		
		System.out.println(ans);
	}

	private static void winter() {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= N ; ++c) {
				nutrition[r][c] += A[r][c];
			}
		}		
	}

	private static void fall() {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= N ; ++c) {
				int size = tree[r][c].size();
				
				if(size == 0) continue;
				
				for(int t : tree[r][c]) {
					if(t < 5 || t % 5 != 0) continue;
					
					for(int d = 0 ; d < 8 ; ++d) {
						int nr = r + dir[d][0];
						int nc = c + dir[d][1];
						if(nr < 1 || nr > N || nc < 1 || nc > N) continue;
						
						tree[nr][nc].add(1);
					}
				}
			}
		}
	}

	private static void summer() {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= N ; ++c) {
				nutrition[r][c] += dead[r][c];
				dead[r][c] = 0;
			}
		}
	}

	private static void spring() {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= N ; ++c) {
				int size = tree[r][c].size();
				int idx = 0;

				if(size == 0) continue;
				
				Collections.sort(tree[r][c]);
				
				for(; idx < size ; ++idx) {
					int t = tree[r][c].get(idx);
					if(nutrition[r][c] >= t) {
						nutrition[r][c] -= t;
						tree[r][c].set(idx, t + 1);
					} else break;
				}
				
				for(int i = size - 1 ; i >= idx ; --i) {
					int t = tree[r][c].remove(i);
					dead[r][c] += t / 2;
				}
			}
		}
	}
}
