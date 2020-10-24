package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B12851_숨바꼭질2 {
	static boolean[] visited;
	static Queue<Integer> q;
	static int N, K;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		visited = new boolean[100001];
		q = new LinkedList<>();
		
		q.offer(N);
		visited[N] = true;
		
      	// 이미 같이 있는 경우
		if(N == K) {
			System.out.println(0);
			System.out.println(1);
			return;
		}
		
		bfs();
	}

	private static void bfs() {
		int time = 0;
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			time++;
			for(int i = 0 ; i < size ; ++i) {
				int cur = q.poll();
				// 꺼낼 때 방문체크 하므로써 다른 방법으로 같은 시간에 도착한 경우들을 허용한다.
				visited[cur] = true;
				
				int[] next = {cur - 1, cur + 1, cur * 2};
				for(int j = 0 ; j < 3 ; ++j) {
					if(next[j] >= 0 && next[j] <= 100000 && !visited[next[j]]) {
						if(next[j] == K) {
							cnt++;
							continue;
						}
//						visited[cur][next[j]] = true;
						q.offer(next[j]);
					}
				}
			}
			// cnt가 올라간건 최소시간에 도착했다는 것으로 이후 시간에 도착하는 것은 무의미하다.
			if(cnt != 0) q.clear();
		}
		System.out.println(time);
		System.out.println(cnt);
	}
}
