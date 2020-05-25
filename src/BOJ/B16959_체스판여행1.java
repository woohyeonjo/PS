package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B16959_체스판여행1 {

	static class Node {
		int r, c, type, time, next;

		Node(int r, int c, int type, int time, int next) {
			this.r = r;
			this.c = c;
			this.type = type;
			this.time = time;
			this.next = next;
		}
	}

	static int[][][] dir = { { { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 } },
			{ { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 } },
			{ { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }, };

	static final int BISHOP = 0;
	static final int KNIGHT = 1;
	static final int ROOK = 2;

	static Queue<Node> q;
	static int[][] map;
	static boolean[][][][] visited;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		q = new LinkedList<>();
		map = new int[N][N];
		visited = new boolean[N][N][3][N * N + 1];

		for (int r = 0; r < N; ++r) {
			String[] line = br.readLine().split(" ");
			for (int c = 0; c < N; ++c) {
				map[r][c] = Integer.parseInt(line[c]);

				if (map[r][c] == 1) {
					for (int i = 0; i < 3; ++i) {
						q.offer(new Node(r, c, i, 0, 2));
						visited[r][c][i][2] = true;
					}
				}
			}
		}

		System.out.println(bfs());
	}

	private static int bfs() {
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if(cur.next == N * N + 1) {
				return cur.time;
			}
			
			// 말 바꾸기
			for(int i = 0 ; i < 3 ; ++i) {
				if(cur.type == i || visited[cur.r][cur.c][i][cur.next]) continue;
				
				q.offer(new Node(cur.r, cur.c, i, cur.time + 1, cur.next));
				visited[cur.r][cur.c][i][cur.next] = true;
			}
			
			// 말 옮기기 
			switch(cur.type) {
			case BISHOP:
				for(int d = 0 ; d < 4 ; ++d) {
					int nr = cur.r + dir[BISHOP][d][0];
					int nc = cur.c + dir[BISHOP][d][1];
					
					while(nr >= 0 && nr < N && nc >= 0 && nc < N) {
						if(!visited[nr][nc][BISHOP][cur.next]) {
							visited[nr][nc][BISHOP][cur.next] = true;
							
							if(map[nr][nc] == cur.next) {
								q.offer(new Node(nr, nc, BISHOP, cur.time + 1, cur.next + 1));
							} else {
								q.offer(new Node(nr, nc, BISHOP, cur.time + 1, cur.next));
							}
						}
						
						nr += dir[BISHOP][d][0];
						nc += dir[BISHOP][d][1];
					}
				}
				break;
				
			case KNIGHT:
				for(int d = 0 ; d < 8 ; ++d) {
					int nr = cur.r + dir[KNIGHT][d][0];
					int nc = cur.c + dir[KNIGHT][d][1];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc][KNIGHT][cur.next]) continue;
						
					visited[nr][nc][KNIGHT][cur.next] = true;
					
					if(map[nr][nc] == cur.next) {
						q.offer(new Node(nr, nc, KNIGHT, cur.time + 1, cur.next + 1));
					} else {
						q.offer(new Node(nr, nc, KNIGHT, cur.time + 1, cur.next));
					}
				}
				break;
				
			case ROOK:
				for(int d = 0 ; d < 4 ; ++d) {
					int nr = cur.r + dir[ROOK][d][0];
					int nc = cur.c + dir[ROOK][d][1];
					
					while(nr >= 0 && nr < N && nc >= 0 && nc < N) {
						if(!visited[nr][nc][ROOK][cur.next]) {
							visited[nr][nc][ROOK][cur.next] = true;
							
							if(map[nr][nc] == cur.next) {
								q.offer(new Node(nr, nc, ROOK, cur.time + 1, cur.next + 1));
							} else {
								q.offer(new Node(nr, nc, ROOK, cur.time + 1, cur.next));
							}
						}
						
						nr += dir[ROOK][d][0];
						nc += dir[ROOK][d][1];
					}
				}
				break;
				
			}
		}
		
		return -1;
	}
}
