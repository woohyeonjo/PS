package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17406_배열돌리기4_3 {
	
	static int N, M, K, ans;
	static int[][] cmds;
	static int[][] map;
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		
		map = new int[N + 1][M + 1];
		cmds = new int[K][3];
		selected = new boolean[K];
		ans = Integer.MAX_VALUE;
		
		for(int r = 1 ; r <= N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1 ; c <= M ; ++c) {
				map[r][c] = stoi(st.nextToken());
			}
		}
		
		for(int r = 0 ; r < K ; ++r) {
			st = new StringTokenizer(br.readLine());
			cmds[r][0] = stoi(st.nextToken());
			cmds[r][1] = stoi(st.nextToken());
			cmds[r][2] = stoi(st.nextToken());
		}
		
		permutation(0, new int[K]);
		
		System.out.println(ans);
	}
	
	private static void permutation(int idx, int[] seq) {
		if(idx == K) {
			int[][] copy = copy();
			
			for(int i : seq) {
				rotation(copy, cmds[i][0], cmds[i][1], cmds[i][2]);
			}
			
			int min = calMin(copy);
			ans = ans > min ? min : ans;

			return;
		}
		
		for(int i = 0 ; i < K ; ++i) {
			if(selected[i]) continue;
			seq[idx] = i;
			selected[i] = true;
			permutation(idx + 1, seq);
			selected[i] = false;
		}
	}
	
	private static int calMin(int[][] arr) {
		int min = Integer.MAX_VALUE;
		
		for(int r = 1 ; r < arr.length ; ++r) {
			int sum = 0;
			for(int c = 1 ; c < arr[r].length ; ++c) {
				sum += arr[r][c];
			}
			min = min > sum ? sum : min;
		}
		
		return min;
	}

	private static void rotation(int[][] arr, int r, int c, int s) {
		int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		int len = s * 2 + 1;
		int depth = len / 2;
		int sr = r - s, sc = c - s, cr, cc, nr, nc, d = 0;
		
		for(int i = 0 ; i < depth ; ++i) {
			cr = sr;
			cc = sc;
			int temp = arr[sr][sc];
			while(d < 4) {
				nr = cr + dir[d][0];
				nc = cc + dir[d][1];
				if(nr >= sr && nr < sr + len - i * 2 && nc >= sc && nc < sc + len - i * 2) {
					arr[cr][cc] = arr[nr][nc];
					cr = nr;
					cc = nc;
				} else d++;
			}
			arr[cr][cc + 1] = temp;
			d = 0;
			sr++;
			sc++;
		}
	}
	
	private static int[][] copy(){
		int[][] result = new int[map.length][map[0].length];
		
		for(int r = 1 ; r < result.length ; ++r) {
			for(int c = 1 ; c < result[r].length ; ++c) {
				result[r][c] = map[r][c];
			}
		}
		
		return result;
	}

	private static void print(int[][] arr) {
		for(int r = 1 ; r < arr.length ; ++r) {
			for(int c = 1 ; c < arr[r].length ; ++c) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
