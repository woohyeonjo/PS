package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17136_색종이붙이기_2 {
	
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static final int SIZE = 10;
	
	static int[][] map;
	static int[] paper = {0, 5, 5, 5, 5, 5};
	static boolean possible;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		map = new int[SIZE][SIZE];
		ans = Integer.MAX_VALUE;
		
		for(int r = 0 ; r < SIZE ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < SIZE ; ++c) {
				map[r][c] = -(stoi(st.nextToken()));
			}
		}
		
		backtracking(findStartPoint());
		
		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
	
	private static void backtracking(Point start) {
		if(start == null) {
			int cnt = 25;
			
			for(int i = 1 ; i < 6 ; ++i) {
				cnt -= paper[i];
			}
			
			ans = ans > cnt ? cnt : ans;
			return;
		}
		
		for(int i = 1 ; i <= 5 ; ++i) {
			if(paper[i] > 0 && check(start, i)) {
				paper[i]--;
				fill(start, i, i);
				backtracking(findStartPoint());
				fill(start, i, -1);
				paper[i]++;
			}
		}
	}

	private static void fill(Point start, int size, int filler) {
		for(int r = start.r ; r < start.r + size ; ++r) {
			for(int c = start.c ; c < start.c + size ; ++c) {
				map[r][c] = filler;
			}
		}
	}

	private static boolean check(Point start, int size) {
		if(start.r + size > SIZE || start.c + size > SIZE) return false;
		
		for(int r = start.r ; r < start.r + size ; ++r) {
			for(int c = start.c ; c < start.c + size ; ++c) {
				if(map[r][c] != -1) return false;
			}
		}
		return true;
	}
	
	private static Point findStartPoint() {
		for(int r = 0 ; r < SIZE ; ++r) {
			for(int c = 0 ; c < SIZE ; ++c) {
				if(map[r][c] == -1) return new Point(r, c);
			}
		}
		return null;
	}

	static private int stoi(String s) {
		return Integer.parseInt(s);
	}
}
