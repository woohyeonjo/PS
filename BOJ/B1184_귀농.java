package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B1184_귀농 {
	
	static int[][] map;
	static int[][] cache;
	static ArrayList<Integer> sum1, sum2;
	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		cache = new int[N][N];
		sum1 = new ArrayList<>();
		sum2 = new ArrayList<>();
		ans = 0;

		for(int r = 0 ; r < N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < N ; ++c) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 내부 꼭지점들에 대해서 수행
		for(int r = 0 ; r < N - 1 ; ++r) {
			for(int c = 0 ; c < N - 1 ; ++c) {
				// 왼쪽 위와 오른쪽 아래 비교
				sumUpperLeft(r, c);
				sumUnderRight(r + 1, c + 1);
				count();
				clean();

				// 오른쪽 위와 왼쪽 아래 비교
				sumUpperRight(r, c + 1);
				sumUnderLeft(r + 1, c);
				count();
				clean();
			}
		}
		
		System.out.println(ans);
	
	}

	private static void sumUnderLeft(int R, int C) {
		for(int r = R ; r < N ; ++r) {
			int sum = 0;
			for(int c = C ; c >= 0 ; --c) {
				sum += map[r][c];
				
				if(r == R) {
					cache[r][c] = sum;
				} else {
					cache[r][c] = cache[r - 1][c] + sum;
				}
				
				sum2.add(cache[r][c]);
			}
		}
	}

	private static void sumUpperRight(int R, int C) {
		for(int r = R ; r >= 0 ; --r) {
			int sum = 0;
			for(int c = C ; c < N ; ++c) {
				sum += map[r][c];

				if(r == R) {
					cache[r][c] = sum;
				} else {
					cache[r][c] = cache[r + 1][c] + sum;
				}
				
				sum1.add(cache[r][c]);
			}
		}
	}

	private static void sumUnderRight(int R, int C) {
		for(int r = R ; r < N ; ++r) {
			int sum = 0;
			for(int c = C ; c < N ; ++c) {
				sum += map[r][c];
				
				if(r == R) {
					cache[r][c] = sum;
				} else {
					cache[r][c] = cache[r - 1][c] + sum;
				}
				
				sum2.add(cache[r][c]);
			}
		}
	}

	private static void sumUpperLeft(int R, int C) {
		for(int r = R ; r >= 0 ; --r) {
			int sum = 0;
			for(int c = C ; c >= 0 ; --c) {
				sum += map[r][c];

				if(r == R) {
					cache[r][c] = sum; 
				} else {
					cache[r][c] = cache[r + 1][c] + sum;
				}
				
				sum1.add(cache[r][c]);
			}
		}
	}

	private static void count() {
		for(int i : sum1) {
			for(int j : sum2) {
				if(i == j) ans++;
			}
		}
	}

	private static void clean() {
		sum1.clear();
		sum2.clear();
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				cache[r][c] = 0;
			}
		}
	}
}
