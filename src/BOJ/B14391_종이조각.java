package BOJ;


import java.util.Scanner;

public class B14391_종이조각 {
	
	static class Square {
		int r_size, c_size;

		public Square(int r_size, int c_size) {
			super();
			this.r_size = r_size;
			this.c_size = c_size;
		}
	}
	
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static String[][] map;
	static boolean[][] visited;
	static Square[] squareList;
	static int N, M, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new String[N][M];
		visited = new boolean[N][M];
		squareList = new Square[N + M - 1];
		
		String[] line;
		for(int r = 0 ; r < N ; ++r) {
			line = sc.next().split("");
			for(int c = 0 ; c < M ; ++c) {
				map[r][c] = line[c];
			}
		}
		
		int index = 0;
		for(int r = 1 ; r <= N ; ++r) {
			squareList[index++] = new Square(r, 1);
		}
		for(int c = 2 ; c <= M ; ++c) {
			squareList[index++] = new Square(1, c);
		}
		
		ans = Integer.MIN_VALUE;
		
		go(0);
		
		System.out.println(ans);
	}

	private static void go(int total) {
		Point start = find();
		
		if(start == null) {
			ans = total > ans ? total : ans;
			return;
		}
		
		for(int i = 0 ; i < N + M - 1 ; ++i) {
			if(isFit(start.r, start.c, squareList[i])) {
				total += set(start.r, start.c, squareList[i], true);
				go(total);
				total -= set(start.r, start.c, squareList[i], false);
			}
		}
		
	}

	private static int set(int r, int c, Square square, boolean type) {
		String number = "";
		
		if(square.r_size == 1) {
			for(int i = c ; i < c + square.c_size ; ++i) {
				visited[r][i] = type;
				number += map[r][i];
			}
			
			return Integer.parseInt(number);
			
		} else {
			for(int i = r ; i < r + square.r_size ; ++i) {
				visited[i][c] = type;
				number += map[i][c];
			}
			
			return Integer.parseInt(number);
		}
	}

	private static boolean isFit(int r, int c, Square square) {
		if(square.r_size == 1) {
			for(int i = c ; i < c + square.c_size ; ++i) {
				if(i >= M || visited[r][i]) return false;
			}
			
			return true;
		} else {
			for(int i = r ; i < r + square.r_size ; ++i) {
				if(i >= N || visited[i][c]) return false;
			}
			
			return true;
		}
	}

	private static Point find() {
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				if(visited[r][c]) continue;
				return new Point(r, c);
			}
		}
		return null;
	}
}
