package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2234_성곽 {
	
	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static Queue<Node> q;
	// 서 북 동 
	static int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
	static boolean[][] visited;
	static int[][] map;
	static int room, max;
	static int M, N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		room = max = 0;
		
		q = new LinkedList<>();
		visited = new boolean[M][N];
		map = new int[M][N];
		
		for(int i = 0 ; i < M ; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; ++j) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		// BFS 라벨링을 통해 초기 상태의 방 갯수와 가장 넓은 방을 찾는다. 
		for(int i = 0 ; i < M ; ++i) {
			for(int j = 0 ; j < N ; ++j) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					q.offer(new Node(i, j));
					
					int cur = bfs(++room);
					max = cur > max ? cur : max;
				}
				
			}
		}
		
		System.out.println(room);
		System.out.println(max);
		
		// 모든 벽을 한번씩 없애가며 다시 BFS를 수행한다.
		// 하지만 모든 방의 넓이를 구할 필요없이
		// 벽을 없앤 방의 넓이만 다시 구해서 기존의 가장 큰 방과 비교하면 된다.
		for(int i = 0 ; i < M ; ++i) {
			for(int j = 0 ; j < N ; ++j) {
				reset();
				for(int k = 1 ; k <= 8 ; k *= 2) {
					// 벽이 있는 경우에만 (비트연산자) 
					if((map[i][j] & k) == k) {
						visited[i][j] = true;
						
						// 벽 없애기 
						map[i][j] = map[i][j] - k;
						q.offer(new Node(i, j));
						int cur = bfs(0);
						max = cur > max ? cur : max;
						// 벽 다시 만들기 
						map[i][j] = map[i][j] + k;
					}
				}
			}
		}
		System.out.println(max);
	}
	
	private static int bfs(int num) {
		int cnt = 1;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			for(int i = 1 ; i <= 8 ; i *= 2) {
				// 벽이 없을 때 
				if((map[now.r][now.c] & i) != i) {
					// 방향
					int d = i / 2;
					if(d == 4) d--;
					
					int nr = now.r + dir[d][0];
					int nc = now.c + dir[d][1];
					
					// 가장 바깥쪽 벽을 없애는 경우가 있어서 필요하다. 
					if(nr >= M || nr < 0 || nc >= N || nc < 0 || visited[nr][nc]) continue;
					
					cnt++;
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc));
				}
			}
			
		}
		return cnt;
	}
	
	private static void reset() {
		for(int i = 0 ; i < M ; ++i) {
			for(int j = 0 ; j < N ; ++j) {
				visited[i][j] = false;
			}
		}
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
