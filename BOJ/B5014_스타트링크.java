package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B5014_스타트링크 {
	
	static Queue<Integer> q;
	static boolean[] visited;
	static int F, S, G, U, D;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		F = sc.nextInt();
		S = sc.nextInt();
		G = sc.nextInt();
		U = sc.nextInt();
		D = sc.nextInt();

		q = new LinkedList<>();
		visited = new boolean[F + 1];
		
		bfs();
	}

	private static void bfs() {
		int times = 0;
		
		q.offer(S);
		visited[S] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			times++;
			
			for(int i = 0 ; i < size ; ++i) {
				int now = q.poll();
				
				if(now == G) {
					System.out.println(times - 1);
					return;
				}
				
				int[] next = {now + U, now - D};

				for(int j = 0 ; j < 2 ; ++j) {
					if(next[j] >= 1 && next[j] <= F && !visited[next[j]]) {
						visited[next[j]] = true;
						q.offer(next[j]);
					}
				}
			}
		}
		System.out.println("use the stairs");
	}
}
