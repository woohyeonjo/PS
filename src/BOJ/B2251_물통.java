package BOJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B2251_물통 {
	
	static class Bottle {
		int a, b, c;
		
		Bottle(int a, int b, int c){
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
	static boolean[][][] visited;
	static ArrayList<Integer> ans;
	static Queue<Bottle> q;
	static int A, B, C;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		visited = new boolean[201][201][201];
		
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		
		q = new LinkedList<>();
		ans = new ArrayList<>();
		
		Bottle start = new Bottle(0, 0, C);

		visited[0][0][C] = true;
		q.offer(start);
		
		bfs();
		
		for(int i = 0 ; i < 201 ; ++i) {
			for(int j = 0 ; j < 201 ; ++j) {
				for(int k = 0 ; k < 201 ; ++k) {
					if( i == 0 && visited[i][j][k] && !ans.contains(k)) {
						ans.add(k);
					}
				}
			}
		}
		
		Collections.sort(ans);
		
		for(Integer i : ans) {
			System.out.print(i + " ");
		}
	}

	private static void bfs() {
		while(!q.isEmpty()) {
			Bottle cur = q.poll();
			int ca = cur.a;
			int cb = cur.b;
			int cc = cur.c;
			int na = 0;
			int nb = 0;
			int nc = 0;
			
			// a를 b에 부었을 때 
			if(cb + ca > B) {
				na = cb + ca - B;
				nb = B;
				nc = cc;
			} else {
				na = 0;
				nb = cb + ca;
				nc = cc;
			}
			
			if(!visited[na][nb][nc]) {
				visited[na][nb][nc] = true;
				q.offer(new Bottle(na, nb, nc));
			}
			
			na = 0; nb = 0; nc = 0;
			
			// a를 c에 부었을 때 
			if(cc + ca > C) {
				na = cc + ca - C;
				nb = cb;
				cc = C;
			} else {
				na = 0;
				nb = cb;
				nc = cc + ca;
			}
			
			if(!visited[na][nb][nc]) {
				visited[na][nb][nc] = true;
				q.offer(new Bottle(na, nb, nc));
			}
			
			na = 0; nb = 0; nc = 0;

			// b를 a에 부었을 때
			if(ca + cb > A) {
				na = A;
				nb = ca + cb - A;
				nc = cc; 
			} else {
				na = ca + cb;
				nb = 0;
				nc = cc;
			}
			
			if(!visited[na][nb][nc]) {
				visited[na][nb][nc] = true;
				q.offer(new Bottle(na, nb, nc));
			}
			
			na = 0; nb = 0; nc = 0;
			
			// b를 c에 부었을 때 
			if(cc + cb > C) {
				na = ca;
				nb = cc + cb - C;
				nc = C;
			} else {
				na = ca;
				nb = 0;
				nc = cc + cb;
			}
			
			if(!visited[na][nb][nc]) {
				visited[na][nb][nc] = true;
				q.offer(new Bottle(na, nb, nc));
			}
			
			na = 0; nb = 0; nc = 0;
			
			// c를 a에 부었을 때 
			if(ca + cc > A) {
				na = A;
				nb = cb;
				nc = ca + cc - A;
			} else {
				na = ca + cc;
				nb = cb;
				nc = 0;
			}
			
			if(!visited[na][nb][nc]) {
				visited[na][nb][nc] = true;
				q.offer(new Bottle(na, nb, nc));
			}
			
			na = 0; nb = 0; nc = 0;
			
			// c를 b에 부었을 때
			if(cb + cc > B) {
				na = ca;
				nb = B;
				nc = cb + cc - B;
			} else {
				na = ca;
				nb = cb + cc;
				nc = 0;
			}
			
			if(!visited[na][nb][nc]) {
				visited[na][nb][nc] = true;
				q.offer(new Bottle(na, nb, nc));
			}
		}
	}
}
