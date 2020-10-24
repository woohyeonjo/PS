package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B14226_이모티콘 {

	static class Node {
		int cnt;
		int clip;
		
		public Node(int cnt, int clip) {
			this.cnt = cnt;
			this.clip = clip;
		}
	}
	
	// 정점이 같더라도 클립보드 상태에 따라서 다른 결과를 가져올 수 있다. 
	static boolean[][] visited; 
	static Queue<Node> q;
	static int S;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = Integer.parseInt(br.readLine());
		
		visited = new boolean[1001][1001];
		q = new LinkedList<>();
		
		visited[1][0] = true;
		q.offer(new Node(1, 0));
		System.out.println(bfs());
		
	}

	private static int bfs() {
		int time = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			time++;
			for(int i = 0 ; i < size ; ++i) {
				Node now = q.poll();
				
				int[] temp = {now.cnt, now.cnt + now.clip, now.cnt - 1};
				for(int j = 0 ; j < temp.length ; ++j) {
					int next = temp[j];
					
					if(next == S) return time;
					
					switch(j) {
					// 복사
					case 0:
						if(next == 0) continue;
						q.offer(new Node(next, next));
						break;
					// 붙여넣기 
					case 1:
						if(next > 1000 || visited[next][now.clip] || now.clip == 0) continue;
						visited[next][now.clip] = true;
						q.offer(new Node(next, now.clip));
						break;
					// 하나 빼기 
					case 2:
						if(visited[next][now.clip] || next == 0) continue;
						visited[next][now.clip] = true;
						q.offer(new Node(next, now.clip));
						break;
					}
					
				}
			}
		}
		return -1;
	}
}
