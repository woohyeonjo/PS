package BOJ.go;

import java.util.Scanner;

public class B17136_색종이붙이기 {
	
	static class Cell {
		int r, c;
		public Cell(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static final int N = 10;
	
	static int[][] map;
	static boolean[][] marked;
	static int[] paper;
	static int ans, current;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		map = new int[N][N];
		marked = new boolean[N][N];
		paper = new int[6]; 
		ans = Integer.MAX_VALUE;
		current = 0;
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				map[r][c] = sc.nextInt();
			}
		}
		
		go(0);
		if(ans == Integer.MAX_VALUE) ans = -1;
		System.out.println(ans);
		
	}

	private static void go(int current) {
		Cell start = findStart();
		
		if(start == null) {
			ans = ans > current ? current : ans;
			return;
		}
		
		for(int size = 1 ; size < 6 ; ++size) {
			if(paper[size] >= 5) continue;
			if(!isPossible(start.r, start.c, size)) return;
			
			
			marking(start.r, start.c, size, true);
			paper[size]++;
			go(current + 1);
			paper[size]--;
			marking(start.r, start.c, size, false);
		}
	}
	
	private static boolean isPossible(int sr, int sc, int size) {
		
		if(sr + size > N || sc + size > N) return false;
		
		for(int r = sr ; r < sr + size ; ++r) {
			for(int c = sc ; c < sc + size ; ++c) {
				if(map[r][c] == 0 || marked[r][c]) return false;
			}
		}
		return true;
	}
	
	private static void marking(int sr, int sc, int size, boolean state) {
		for(int r = sr ; r < sr + size ; ++r) {
			for(int c = sc ; c < sc + size ; ++c) {
				marked[r][c] = state;
			}
		}
	}
	
	private static Cell findStart() {
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				if(map[r][c] == 1 && !marked[r][c]) return new Cell(r, c);;
			}
		}
		return null;
	}
	
	
	
}

