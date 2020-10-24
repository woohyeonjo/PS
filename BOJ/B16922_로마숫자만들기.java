package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B16922_로마숫자만들기 {
	
	static Queue<Integer> q;
	static boolean[][] visited;
	static int[] romNumber = {1, 5, 10, 50};
	static int N, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		q = new LinkedList<>();
		visited = new boolean[N * 50 + 1][21];
		
		ans = bfs();
		System.out.println(ans);
	}

	private static int bfs() {
		int cnt = 1;
		
		for(int i = 0 ; i < 4 ; ++i) {
			q.offer(romNumber[i]);
		}
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			if(cnt == N) {
				return size;
			}
			
			for(int i = 0 ; i < size ; ++i) {
				int now = q.poll();
				
				for(int j = 0 ; j < 4 ; ++j) {
					if(!visited[now + romNumber[j]][cnt]) {
						visited[now + romNumber[j]][cnt] = true;
						q.offer(now + romNumber[j]);
					}
				}
			}
			cnt++;
		}
		return 0;
	}
}
