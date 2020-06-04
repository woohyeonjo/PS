package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16236_아기상어_3 {
	
	static class Node implements Comparable<Node> {
		int r, c, d;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		Node(int r, int c, int d){
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			if(this.d == o.d) {
				if(this.r == o.r) {
					return this.c - o.c;
				} else return this.r - o.r;
			} else return this.d - o.d;
		}
	}
	
	static PriorityQueue<Node> pq;
	static Queue<Node> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] map;
	static boolean[][] visited;
	static int[] shark;
	static int N, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		pq = new PriorityQueue<>();
		q = new LinkedList<>();
		map = new int[N][N];
		shark = new int[4];
		
		for(int r = 0 ; r < N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < N ; ++c) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 9) {
					shark[0] = r;
					shark[1] = c;
					shark[2] = 2;
					map[r][c] = 0;
				}
			}
		}
		
		while(true) {
			visited = new boolean[N][N];
			bfs();
			if(pq.size() == 0) break;
			moveAndEat();
			pq.clear();
		}
		
		System.out.println(ans);
	}

	private static void moveAndEat() {
		Node target = pq.poll();
		
		map[target.r][target.c] = 0;
		ans += target.d;
		
		shark[0] = target.r;
		shark[1] = target.c;
		shark[3]++;
			
		if(shark[2] == shark[3]) {
			shark[2]++;
			shark[3] = 0;
		}
	}

	private static void bfs() {
		int dist = 0;
		int sr = shark[0];
		int sc = shark[1];
		int lv = shark[2];
		
		q.offer(new Node(sr, sc));
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i = 0 ; i < size ; ++i) {
				Node cur = q.poll();
				
				for(int d = 0 ; d < 4 ; ++d) {
					int nr = cur.r + dir[d][0];
					int nc = cur.c + dir[d][1];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
					
					if(map[nr][nc] > lv) {
						continue;
					} else if(map[nr][nc] == lv || map[nr][nc] == 0) {
						q.offer(new Node(nr, nc));
						visited[nr][nc] = true;
					} else {
						pq.offer(new Node(nr, nc, dist + 1));
						visited[nr][nc] = true;
					}
				}
			}
			dist++;
		}
	}
}
