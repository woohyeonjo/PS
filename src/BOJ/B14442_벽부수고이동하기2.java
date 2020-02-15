package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14442_벽부수고이동하기2 {
	
	static class Node {
		// Node 객체에 좌표, 이동한 거리, 부순 벽의 개수를 저장한다. 
		int r, c, dist, broken;
		
		Node(int r, int c, int dist, int broken){
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.broken = broken;
		}
	}
	
	static Queue<Node> q;
	static boolean[][][] visited;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] map;
	static int N, M, K;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		
		ans = Integer.MAX_VALUE;
		q = new LinkedList<>();
		map = new int[N + 1][M + 1];
		// 방문체크를 현재 좌표에 벽을 몇번 부수고 왔는지로 체크한다. 
		visited = new boolean[N + 1][M + 1][K + 1];
		
		for(int r = 1 ; r <= N ; ++r) {
			char[] line = br.readLine().toCharArray();
			for(int c = 1 ; c <= M ; ++c) {
				map[r][c] = line[c - 1] - '0';
			}
		}
		
		visited[1][1][0] = true;
		q.offer(new Node(1, 1, 1, 0));

		go();
		
		
		if(ans == Integer.MAX_VALUE) ans = -1;
		
		System.out.println(ans);
	}

	private static void go() {
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			if(now.r == N && now.c == M) {
				ans = ans > now.dist ? now.dist : ans;
				return;
			}
			
			for(int d = 0 ; d < 4; ++d) {
				int nr = now.r + dir[d][0];
				int nc = now.c + dir[d][1];
				
				if(nr > N || nr < 1 || nc > M || nc < 1) continue;
				
				if(map[nr][nc] == 1) {
					if(now.broken < K && !visited[nr][nc][now.broken + 1]) {
						visited[nr][nc][now.broken + 1] = true;
						q.offer(new Node(nr, nc, now.dist + 1, now.broken + 1));
					}
				} else {
					if(!visited[nr][nc][now.broken]) {
						visited[nr][nc][now.broken] = true;
						q.offer(new Node(nr, nc, now.dist + 1, now.broken));
					}
				}
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
