package BOJ;


import java.util.Scanner;

public class B16924_십자가찾기 {
	
	static class Star {
		int r, c;
		int len;
		
		public Star(int r, int c, int len) {
			super();
			this.r = r;
			this.c = c;
			this.len = len;
		}
	}
	
	static int[][] map;
	static int[][] temp;
	static int[][] max;
	static int[][] full;
	static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static boolean[][] visited;
	static Star[] stars;
	
	static int N, M, ans;
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 
		 N = sc.nextInt();
		 M = sc.nextInt();
		 sc.nextLine();
		 ans = 0;
		 
		 map = new int[N + 1][M + 1];
		 max = new int[N + 1][M + 1];
		 full = new int[N + 1][M + 1];
		 
		 char[] line;
		 for(int r = 1 ; r <= N ; ++r) {
			 line = sc.nextLine().toCharArray();
			 for(int c = 1 ; c <= M ; ++c) {
				 if(line[c - 1] == '.') map[r][c] = 0;
				 else map[r][c] = 1;
			 }
		 }
		 
		 calMax();
		 makeFull();
		 
		 for(int i = 1 ; i <= N * M ; ++i) {
			 stars = new Star[i];
			 visited = new boolean[N + 1][M + 1];
			 if(permu(i, 0)) break;
		 }
		 
		 System.out.println(stars.length);
		 for(Star s : stars) System.out.println(s.r + " " + s.c + " " + s.len);
	}
	
	private static void makeFull() {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= M ; ++c) {
				full[r][c] = 1;
			}
		}
		
		full[1][1] = 0;
		full[1][M] = 0;
		full[N][1] = 0;
		full[N][M] = 0;
	}

	private static void calMax() {
		int num = 1;
		int H = M - 1;
		int V = N - 1;
		int limit;
		if(N % 2 == 0) limit = N / 2;
		else limit = N / 2 + 1;
		
		for(int i = 2 ; i <= limit ; ++i) {
			for(int r = i ; r <= V ; ++r) {
				for(int c = i ; c <= H ; ++c) {
					max[r][c] = num;
				}
			}
			num++;
			H--;
			V--;
		}
	}

	private static boolean permu(int limit, int index) {
		if(limit == index) {
			draw();
			//print(temp);
			if(check()) return true;
			else {
				if(isFull()) {
					System.out.println(-1);
					System.exit(0);
				}
				return false;
			}
		}
		
		for(int r = 2 ; r < N ; ++r) {
			for(int c = 2 ; c < M ; ++c) {
				if(visited[r][c]) continue;
				visited[r][c] = true;
				for(int i = 1 ; i <= max[r][c] ; ++i) {
					stars[index] = new Star(r, c, i);
					if(permu(limit, index + 1)) return true;
				}
				visited[r][c] = false;
			}
		}
		return false;
	}

	private static boolean check() {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= M ; ++c) {
				if(map[r][c] != temp[r][c]) return false;
			}
		}
		return true;
	}
	
	private static boolean isFull() {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= M ; ++c) {
				if(full[r][c] != temp[r][c]) return false;
			}
		}
		return true;
	}

	private static void draw() {
		temp = new int[N + 1][M + 1];
		int nr, nc;
		
		for(Star s : stars) {
			temp[s.r][s.c] = 1; 
			for(int i = 0 ; i < 4 ; ++i) {
				nr = s.r;
				nc = s.c;
				for(int j = 0 ; j < s.len ; ++j) {
					nr += dir[i][0];
					nc += dir[i][1];
					temp[nr][nc] = 1;
				}
			}
		}
	}
	
	private static void print(int[][] arr) {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= M ; ++c) {
				System.out.print(arr[r][c]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
