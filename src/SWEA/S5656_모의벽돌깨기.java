package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S5656_모의벽돌깨기 {
	// Queue에 넣을 객체 좌표 값과 벽돌이 깨질 때 범위를 필드로 가지고 있다.
	static class Brick {
		int h, w, range;
		
		public Brick(int h, int w, int range) {
			this.h = h;
			this.w = w;
			this.range = range;
		}
	}
	
	static Queue<Brick> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우 

	static int[] spots;
	static int[][] map;
	static boolean[][] visited;
	static int ans;
	static int T, N, W, H;

	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		T = stoi(br.readLine());

		for (int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			W = stoi(st.nextToken());
			H = stoi(st.nextToken());
			
			q = new LinkedList<>();
			spots = new int[N];
			map = new int[H][W];
			ans = Integer.MAX_VALUE;

			for (int h = 0; h < H; ++h) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; ++w) {
					map[h][w] = stoi(st.nextToken());
				}
			}

			makeSet(0);

			System.out.println("#" + t + " " + ans);

		}
	}

	// spots를 지정해주는 함수
	private static void makeSet(int spot) {
		if (spot == N) {
			// 벽돌 선별 및 부시기
			int[][] copy = copyMap();
			int brick = 0;

			for (int i = 0; i < N; ++i) {
				visited = new boolean[H][W];
				selectAndCresh(copy, spots[i]);
				compact(copy);
			}
			
			brick = count(copy);
			// 현재 남은 벽돌 수와 지금까지 가장 적게 남은 벽돌수를 비교해 ans 갱신
			ans = ans > brick ? brick : ans;

			return;
		}

		for (int w = 0; w < W; ++w) {
			spots[spot] = w;
			makeSet(spot + 1);
		}
	}

	// 남은 벽돌 개수를 세어보는 함수
	private static int count(int[][] copy) {
		int brick = 0;
		
		for (int h = 0; h < H; ++h) {
			for (int w = 0; w < W; ++w) {
				if(copy[h][w] > 0) brick++;
			}
		}
		return brick;
	}
    
	// 벽돌이 부셔진 이후에 벽돌을 바닥으로 몰아넣는 함수
	private static void compact(int[][] copy) {
		Queue<Integer> temp;
		
		for(int w = 0 ; w < W ; ++w) {
			temp = new LinkedList<>();
			
			for(int h = H - 1 ; h >= 0 ; --h) {
				if(copy[h][w] > 0) temp.offer(copy[h][w]);
			}
			
			for(int h = H - 1 ; h >= 0 ; --h) {
				if(!temp.isEmpty()) {
					copy[h][w] = temp.poll();
				} else {
					copy[h][w] = 0;
				}
			}
		}
	}

	// 구슬과 부딪혔거나 연쇄반응을 일으킨 벽돌을 찾아 부시는 함수
	private static void selectAndCresh(int[][] copy, int spot) {
		// spot에서 떨어진 구슬이 가장 처음 만나는 벽돌을 찾는 부분
		for(int h = 0 ; h < H ; ++h) {
			if(copy[h][spot] > 0) {
				q.offer(new Brick(h, spot, copy[h][spot]));
				break;
			}
		}
		
		while(!q.isEmpty()) {
			Brick now = q.poll();
			int nh, nw;
			
			for(int d = 0 ; d < 4 ; ++d) {
				for(int r = 0 ; r < now.range ; ++r) {
					nh = now.h + dir[d][0] * r;
					nw = now.w + dir[d][1] * r;

					if(nh >= 0 && nh < H && nw >= 0 && nw < W && !visited[nh][nw]) {
						visited[nh][nw] = true;
						q.offer(new Brick(nh, nw, copy[nh][nw]));
						copy[nh][nw] = 0;
					}
				}
			}
		}
	}

	private static int[][] copyMap() {
		int[][] arr = new int[H][W];

		for (int h = 0; h < H; ++h) {
			for (int w = 0; w < W; ++w) {
				arr[h][w] = map[h][w];
			}
		}

		return arr;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}