package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S5656_모의벽돌깨기_2 {
		
	static class Node {
		int r, c;
		
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		@Override
		public String toString() {
			return "[" + r + ", " + c +  "]";
		}
	}
	
	static Queue<Node> q;
	static int N, W, H, T, brickCnt, ans;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] map;
	static boolean[][] visited;
	static int[] marble;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			ans = Integer.MAX_VALUE;
			brickCnt = 0;
			
			q = new LinkedList<>();
			visited = new boolean[H][W];
			map = new int[H][W];
			marble = new int[N];
			
			for(int r = 0 ; r < H ; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0 ; c < W ; ++c) {
					map[r][c] = Integer.parseInt(st.nextToken());
					
					if(map[r][c] != 0) brickCnt++;
				}
			}
			
			permutation(0);
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void permutation(int cnt) {
		
		if(cnt == N) {
			int total = brickCnt;
			int[][] copyMap = copy();
			
			for(int i = 0 ; i < N ; ++i) {
				init();
				total -= shot(marble[i], copyMap);
				fall(copyMap);
			}
			
			ans = ans > total ? total : ans;
			
			return;
		}
		
		for(int i = 0 ; i < W ; ++i) {
			marble[cnt] = i;
			permutation(cnt + 1);
		}
	}

	private static void fall(int[][] arr) {
		for(int c = 0 ; c < W ; ++c) {
			
			for(int r = H - 1 ; r >= 0 ; --r) {
				if(arr[r][c] == 0) {
					int nr = r;
					
					while(nr > 0 && arr[nr][c] == 0) {
						nr--;
					}
					
					arr[r][c] = arr[nr][c];
					arr[nr][c] = 0;
				}
			}
		}
	}

	private static int shot(int c, int[][] arr) {
		int cnt = 0;
		
		// 구슬이 처음 부딫히는 곳
		for(int r = 0 ; r < H ; ++r) {
			if(arr[r][c] != 0) {
				q.offer(new Node(r, c));
				visited[r][c] = true;
				break;
			}
		}
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int len = arr[cur.r][cur.c] - 1;
			
			// 큐에서 꺼냈을 때 벽돌 깨기 
			arr[cur.r][cur.c] = 0;
			cnt++;
			
			if(len == 0) continue;
			
			for(int d = 0 ; d < 4 ; ++d) {
				int nr = cur.r;
				int nc = cur.c;
				for(int i = 0 ; i < len ; ++i) {
					nr += dir[d][0];
					nc += dir[d][1];
					
					if(nr < 0 || nr >= H || nc < 0 || nc >= W || visited[nr][nc]) continue;
					if(arr[nr][nc] == 0) continue;
					
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc));
				}
			}
		}
		
		return cnt;
	}

	private static void init() {
		for(int i = 0 ; i < H ; ++i) {
			for(int j = 0 ; j < W ; ++j) {
				visited[i][j] = false;
			}
		}
	}
	
	private static int[][] copy() {
		int[][] result = new int[H][W];
		
		for(int i = 0 ; i < H ; ++i) {
			for(int j = 0 ; j < W ; ++j) {
				result[i][j] = map[i][j];
			}
		}
		
		return result;
	}
	
	private static void print(int[][] arr) {
		for(int i = 0 ; i < H ; ++i) {
			for(int j = 0 ; j < W ; ++j) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
