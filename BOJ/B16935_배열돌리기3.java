package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B16935_배열돌리기3 {
	
	
	static int[][] map;
	static int N, M, R;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int r = 0 ; r < N ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < R ; ++i) {
			int cmd = Integer.parseInt(st.nextToken());
			
			switch(cmd) {
			case 1:
				one();
				break;
			case 2:
				two();
				break;
			case 3:
				three();
				break;
			case 4:
				four();
				break;
			case 5:
				five();
				break;
			case 6:
				six();
				break;
			}
		}
		
		print();
	}
	
	private static void one() {
		for(int c = 0 ; c < map[0].length ; ++c) {
			for(int r1 = 0, r2 = map.length - 1 ; r1 < r2 ; r1++, r2--) {
				int temp = map[r1][c];
				map[r1][c] = map[r2][c];
				map[r2][c] = temp;
			}
		}
	}
	
	private static void two() {
		for(int r = 0 ; r < map.length ; ++r) {
			for(int c1 = 0, c2 = map[0].length - 1 ; c1 < c2 ; c1++, c2--) {
				int temp = map[r][c1];
				map[r][c1] = map[r][c2];
				map[r][c2] = temp;
			}
		}
	}
	
	private static void three() {
		int[][] result = new int[map[0].length][map.length];
		
		for(int r = 0 ; r < map.length ; ++r) {
			for(int c = 0 ; c < map[0].length ; ++c) {
				result[c][map.length - 1 - r] = map[r][c];
			}
		}
		
		map = result;
	}
	
	private static void four() {
		int[][] result = new int[map[0].length][map.length];
		
		for(int r = 0 ; r < result.length ; ++r) {
			for(int c = 0 ; c < result[0].length ; ++c) {
				result[r][c] = map[c][result.length - 1 - r];
			}
		}
		
		map = result;
	}
	
	private static void five() {
		int[][] result = new int[map.length][map[0].length];
		
		int rowSize = map.length;
		int colSize = map[0].length;
		
		// 1번 -> 2번 
		for(int r = 0 ; r < rowSize / 2 ; ++r) {
			for(int c = 0 ; c < colSize / 2 ; ++c) {
				result[r][c + colSize / 2] = map[r][c];
			}
		}
		
		// 2번 -> 3번
		for(int r = 0 ; r < rowSize / 2 ; ++r) {
			for(int c = colSize / 2 ; c < colSize ; ++c) {
				result[r + rowSize / 2][c] = map[r][c];
			}
		}
		
		// 3번 -> 4번
		for(int r = rowSize / 2 ; r < rowSize ; ++r) {
			for(int c = colSize / 2 ; c < colSize ; ++c) {
				result[r][c - colSize / 2] = map[r][c];
			}
		}
		
		// 4번 -> 1번 
		for(int r = rowSize / 2 ; r < rowSize ; ++r) {
			for(int c = 0 ; c < colSize / 2 ; ++c) {
				result[r - rowSize / 2][c] = map[r][c];
			}
		}
		
		map = result;
	}
	
	private static void six() {
		int[][] result = new int[map.length][map[0].length];
		
		int rowSize = map.length;
		int colSize = map[0].length;
		
		// 1번 -> 4번 
		for(int r = 0 ; r < rowSize / 2 ; ++r) {
			for(int c = 0 ; c < colSize / 2 ; ++c) {
				result[r + rowSize / 2][c] = map[r][c];
			}
		}
		
		// 4번 -> 3번
		for(int r = rowSize / 2 ; r < rowSize ; ++r) {
			for(int c = 0 ; c < colSize / 2 ; ++c) {
				result[r][c + colSize / 2] = map[r][c];
			}
		}
		
		// 3번 -> 2번
		for(int r = rowSize / 2 ; r < rowSize ; ++r) {
			for(int c = colSize / 2 ; c < colSize ; ++c) {
				result[r - rowSize / 2][c] = map[r][c];
			}
		}
		
		// 2번 -> 1번 
		for(int r = 0 ; r < rowSize / 2 ; ++r) {
			for(int c = colSize / 2 ; c < colSize ; ++c) {
				result[r][c - colSize / 2] = map[r][c];
			}
		}
		
		map = result;
	}
	
	private static void print() {
		for(int r = 0 ; r < map.length ; ++r) {
			for(int c = 0 ; c < map[0].length ; ++c) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
