package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B13913_숨바꼭질4 {
	
	static final int MAX = 100000;
	static int N, K, time;
	static int[] ways;
	static Queue<Integer> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N != K) { // N과 K가 같은 경우에는 잡은 것이기 때문에 바로 끝낸다. 
			ways = new int[MAX + 1];
			q = new LinkedList<>();
			
			// ways는 이전 방문을 확인하는 visited와 지나온 경로를 역추적하는 역할을 한다. 
			Arrays.fill(ways, -1);
			q.offer(N);
			
			time = bfs();
			sb.append(time + "\n");
			
			int[] result = new int[time + 1];
			int before = K;
			for(int i = time ; i >= 0 ; --i) {
				result[i] = before;
				before = ways[before];
			}
			
			for(int i = 0 ; i <= time ; ++i) {
				sb.append(result[i] + " ");
			}
		} else {
			sb.append(0 + "\n" + N);
		}
		
		System.out.println(sb.toString());
	}

	private static int bfs() {
		int depth = 0;
		
		while(!q.isEmpty()) {
			depth++;
			int size = q.size();
			
			// size를 구하고 기존에 큐에 있던 만큼만 반복함으로써 몇번째 depth인지 알 수 있다. 
			for(int i = 0 ; i < size ; ++i) {
				int cur = q.poll();
				// 다음과 같이 배열을 선언하므로써 코드를 많이 줄일 수 있었다.
				int[] next = { cur + 1, cur - 1, cur * 2};
				
				for(int j = 0 ; j < 3 ; ++j) {
					if(next[j] >= 0 && next[j] <= MAX &&
					   ways[next[j]] == - 1) { // 이전 방문 여부 확인
						ways[next[j]] = cur; // 방문했다고 기록 
						
						if(next[j] == K) return depth; // 다음으로 방문할 곳이 K면 depth를 리턴 
						
						q.offer(next[j]);
					}
				}
			}
		}
		return -1;
	}

}
