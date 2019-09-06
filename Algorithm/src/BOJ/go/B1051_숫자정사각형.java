package BOJ.go;

import java.util.Scanner;

public class B1051_숫자정사각형 {
	
	static int[][] map;
	static int N, M, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		ans = Integer.MIN_VALUE;
		
		char[] line;
		for(int r = 0 ; r < N ; ++r){
			line = sc.next().toCharArray();
			for(int c = 0 ; c < M ; ++c){
				map[r][c] = line[c];
			}
		}
		
		for(int i = 1 ; i <= Integer.min(N, M) ; ++i){
			if(go(i)) {
				ans = i > ans ? i : ans;
			}
		}
		System.out.println(ans * ans);
	}

	private static boolean go(int size) {
		int num = 0;
		for(int r = 0 ; r < N ; ++r){
			if( r + size -1 >= N ) continue;
			for(int c = 0 ; c < M ; ++c){
				if( c + size - 1 >= M ) continue;
				num = map[r][c];
				if(map[r + size - 1][c] == num &&
				   map[r][c + size - 1] == num &&
				   map[r + size - 1][c + size - 1] == num) return true;
			}
		}
		return false;
	}
	
}
