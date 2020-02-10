package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3019_테트리스 {
	
	static final int BOTTOM = 109;
	
	static int[][][][] blocks =
		{
			{},	
			// 1
			{
				{{0, 0}, {-1, 0}, {-2, 0}, {-3, 0}},
				{{0, 0}, {0, 1}, {0, 2}, {0, 3}}
			},
			// 2
			{
				{{0, 0}, {-1, 0}, {-1, 1}, {0, 1}}
			},
			// 3
			{
				{{0, 0}, {0, 1}, {-1, 1}, {-1, 2}},
				{{0, 0}, {-1, 0}, {0, 1}, {1, 1}}
			},
			// 4
			{
				{{0, 0}, {0, 1}, {1, 1}, {1, 2}},
				{{0, 0}, {-1, 0}, {-1, 1}, {-2, 1}}
			},
			// 5
			{	
				{{0, 0}, {0, 1}, {-1, 1}, {0, 2}},
				{{0, 0}, {-1, 0}, {-2, 0}, {-1, 1}},
				{{0, 0}, {0, 1}, {1, 1}, {0, 2}},
				{{0, 0}, {0, 1}, {-1, 1}, {1, 1}}
			},
			// 6
			{
				{{0, 0}, {0, 1}, {0, 2}, {-1, 2}},
				{{0, 0}, {0, 1}, {-1, 0}, {-2, 0}},
				{{0, 0}, {-1, 0}, {-1, 1}, {-1, 2}},
				{{0, 0}, {0, 1}, {1, 1}, {2, 1}}
			},
			// 7
			{
				{{0, 0}, {-1, 0}, {0, 1}, {0, 2}},
				{{0, 0}, {-1, 0}, {-2, 0}, {-2, 1}},
				{{0, 0}, {0, 1}, {0, 2}, {1, 2}},
				{{0, 0}, {0, 1}, {-1, 1}, {-2, 1}}
			}
		};
	
	static int C, P;
	static int[][] map;
	static int[] height;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = stoi(st.nextToken());
		P = stoi(st.nextToken());
		
		ans = 0;
		map = new int[110][C];
		height = new int[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < C ; ++i) {
			height[i] = stoi(st.nextToken());
		}
		
		for(int i = 0 ; i < C ; ++i) {
			for(int j = BOTTOM ; j > BOTTOM - height[i] ; --j) {
				map[j][i] = 1;
			}
		}
		
		for(int i = 0 ; i < C ; ++i) {
			ans += fitting(BOTTOM - height[i], i);
		}
		
		System.out.println(ans);
	}
	
	private static int fitting(int r, int c) {
		int cnt = 0;
		
		int[][][] block = blocks[P];
		
		for(int i = 0 ; i < block.length ; ++i) {
			boolean isPossible = true;
			for(int j = 0 ; j < block[i].length ; ++j) {
				int nr = r + block[i][j][0];
				int nc = c + block[i][j][1];
				
				if(nr >= 110 || nr < 0 || nc >= C || nc < 0 || map[nr][nc] == 1) {
					isPossible = false;
					break;
				}
			}
			if(isPossible) {
				fill(r, c, block[i], 1);
				if(check(r, c, block[i])) {
					cnt++;
//					print();
//					System.out.println();
				}
				fill(r, c, block[i], 0);
			}
		}
		return cnt;
	}

	private static void fill(int r, int c, int[][] b, int item) {
		for(int i = 0 ; i < b.length ; ++i) {
			int nr = r + b[i][0];
			int nc = c + b[i][1];
			map[nr][nc] = item;
		}
	}

	private static boolean check(int r, int c, int[][] b) {
		
		for(int i = 0 ; i < b.length ; ++i) {
			int nr = r + b[i][0];
			int nc = c + b[i][1];
			if(nr < 109 && nr >= 0 && nc < C && nc >= 0 && map[nr + 1][nc] == 0) {
				return false;
			}
		}
		return true;
	}

	private static void print() {
		for(int i = 100 ; i < map.length ; ++i) {
			for(int j = 0 ; j < C ; ++j) {
				if(map[i][j] == 1) System.out.print("◼︎");
				else System.out.print("◻");
			}
			System.out.println();
		}
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
