package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B18808_스티커붙이기 {
	
	static int[][] map;
	static int N, M, S, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		S = stoi(st.nextToken());
		
		ans = 0;
		map = new int[N][M];
		
		// 스티커 갯수만큼 수행 
		for(int s = 0 ; s < S ; ++s) {
			// 스티커 입력 받기 
			st = new StringTokenizer(br.readLine());
			int R = stoi(st.nextToken());
			int C = stoi(st.nextToken());
			int[][] sticker = new int[R][C];
			
			for(int r = 0 ; r < R ; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0 ; c < C ; ++c) {
					sticker[r][c] = stoi(st.nextToken());
				}
			}
			
			// 회전하기
			OUTER: for(int d = 0 ; d < 4 ; ++d) {
				sticker = rotate(d, sticker);
				
				// 시작지점 찾기 
				for(int r = 0 ; r < N ; ++r) {
					for(int c = 0 ; c < M ; ++c) {
						// 현재 지점에 스티커가 있거나 붙일 스티커가 노트북을 벗어날 경우
						if(r + sticker.length > N || c + sticker[0].length > M) continue;
						
						int[][] temp = copy(map);
						
						if(attach(temp, r, c, sticker)) {
							map = temp;
							break OUTER;
						}
					}
				}
			}
		}
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				if(map[r][c] == 1) ans++;
			}
		}
		
		System.out.println(ans);
	}
	
	private static boolean attach(int[][] temp, int r, int c, int[][] sticker) {
		for(int i = 0 ; i < sticker.length ; ++i) {
			for(int j = 0 ; j < sticker[i].length ; ++j) {
				if(sticker[i][j] == 1) {
					if(temp[r + i][c + j] == 1) return false;
					temp[r + i][c + j] = sticker[i][j];
				}
			}
		}
		return true;
	}

	static int[][] rotate(int d, int[][] arr) {
		if(d == 0) return arr;
		
		int[][] result = new int[arr[0].length][arr.length];
		
		for(int i = 0 ; i < d ; ++i) {
			for(int r = 0 ; r < arr.length ; ++r) {
				for(int c = 0 ; c < arr[r].length ; ++c) {
					result[c][arr.length - 1 - r] = arr[r][c];
				}
			}
		}
		
		return result;
	}
	
	static int[][] copy(int[][] arr) {
		int[][] result = new int[N][M];
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				result[r][c] = arr[r][c];
			}
		}
		
		return result;
	}
	
	static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
