package Algorithm.BOJ.go;

import java.util.Scanner;

public class B14890 {
	
	static int[][] map;
	static boolean[] visited;
	static int N, L, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		
		map = new int[N][N];
		
		for(int r = 0 ; r < N ; ++r	) {
			for(int c = 0 ; c < N ; ++c) {
				map[r][c] = sc.nextInt();
			}
		}
		
		for(int r = 0 ; r < N ; ++r	) {
			check(r);
		}
		
		System.out.println(ans);
		
	}

	private static void check(int idx) {
		if(check_row(idx)) ans++;
		if(check_col(idx)) ans++;
	}

	private static boolean check_row(int idx) {
		visited = new boolean[N];
		int cnt = 1;
		
		for(int c = 1 ; c < N ; ++c) {
			if(map[idx][c] == map[idx][c - 1]) {
				cnt++;
				continue;
			}
			if(Math.abs(map[idx][c] - map[idx][c - 1]) != 1) return false;
			if(map[idx][c] - map[idx][c - 1] == 1) {
				if(cnt < L) return false;
				cnt = 1;
				for(int i = c - L ; i < c ; ++i) {
					visited[i] = true;
				}
			}
		}
		
		cnt = 1;
		
		for(int c = N - 2 ; c >= 0 ; --c) {
			
			if(map[idx][c] == map[idx][c + 1]) {
				cnt++;
				continue;
			}
			if(Math.abs(map[idx][c] - map[idx][c + 1]) != 1) return false;
			if(map[idx][c] - map[idx][c + 1] == 1) {
				if(cnt < L) return false;
				cnt = 1;
				for(int i = c + 1 ; i <= c + L ; ++i) {
					if(visited[i]) return false;
					visited[i] = true;
				}
			}
		}
		return true;
	}

	private static boolean check_col(int idx) {
		visited = new boolean[N];
		int cnt = 1;
		
		for(int r = 1 ; r < N ; ++r) {
			if(map[r][idx] == map[r - 1][idx]) {
				cnt++;
				continue;
			}
			if(Math.abs(map[r][idx] - map[r - 1][idx]) != 1) return false;
			if(map[r][idx] - map[r - 1][idx] == 1) {
				if(cnt < L) return false;
				cnt = 1;
				for(int i = r - L ; i < r ; ++i) {
					visited[i] = true;
				}
			}
		}
		
		cnt = 1;
		
		for(int r = N - 2 ; r >= 0 ; --r) {
			
			if(map[r][idx] == map[r + 1][idx]) {
				cnt++;
				continue;
			}
			if(Math.abs(map[r][idx] - map[r + 1][idx]) != 1) return false;
			if(map[r][idx] - map[r + 1][idx] == 1) {
				if(cnt < L) return false;
				cnt = 1;
				for(int i = r + 1 ; i <= r + L ; ++i) {
					if(visited[i]) return false;
					visited[i] = true;
				}
			}
		}
		return true;
	}
}
