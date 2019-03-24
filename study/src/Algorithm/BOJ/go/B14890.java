package Algorithm.BOJ.go;

import java.util.Scanner;

public class B14890 {
	static int[][] map;
	static int N, L, ans;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		ans = 0;
		
		map = new int[N][N];
		for(int i = 0 ; i < N ; ++i) {
			for(int j = 0 ; j < N ; ++j) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0 ; i < N ; ++i) {
			rowCheck(i);
			colCheck(i);
		}
		
		System.out.println(ans);
	}
	
	private static void colCheck(int col) {
		visited = new boolean[N];
		for(int row = 0 ; row < N - 1 ; ++row) {
			if(Math.abs(map[row][col] - map[row + 1][col]) > 1) return;
			if(map[row][col] - map[row + 1][col] == 1) {
				int height = map[row + 1][col];
				for(int l = 0 ; l < L ; ++l	) {
					if(row + 1 + l >= N) return;
					if(map[row + 1 + l][col] != height) return;
				}
				for(int l = 0 ; l < L ; ++l	) {
					if(visited[row + 1 + l]) return;
					visited[row + 1 + l] = true;
				}
			} else if(map[row][col] - map[row + 1][col] == -1) {
				int height = map[row][col];
				for(int l = 0 ; l < L ; ++l	) {
					if(row - l < 0) return;
					if(map[row - l][col] != height) return;
				}
				for(int l = 0 ; l < L ; ++l	) {
					if(visited[row - l]) return;
					visited[row - l] = true;
				}
			}
		}
		System.out.println("col : " + col + "is possible");
		ans++;
	}
	
	private static void rowCheck(int row) {
		visited = new boolean[N];
		for(int col = 0 ; col < N - 1 ; ++col) {
			if(Math.abs(map[row][col] - map[row][col + 1]) > 1) return;
			if(map[row][col] - map[row][col + 1] == 1) {
				int height = map[row][col + 1];
				for(int l = 0 ; l < L ; ++l	) {
					if(col + 1 + l >= N) return;
					if(map[row][col + 1 + l] != height) return;
				}
				for(int l = 0 ; l < L ; ++l	) {
					if(visited[col + 1 + l]) return;
					visited[col + 1 + l] = true;
				}
			} else if(map[row][col] - map[row][col + 1] == -1) {
				int height = map[row][col];
				for(int l = 0 ; l < L ; ++l	) {
					if(col - l < 0) return;
					if(map[row][col - l] != height) return;
				}
				for(int l = 0 ; l < L ; ++l	) {
					if(visited[col - l]) return;
					visited[col - l] = true;
				}
			}
		}
		System.out.println("row : " + row + "is possible");
		ans++;
	}
}
