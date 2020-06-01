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
		
		HashMap<Integer, Integer> map = new HashMap<>();

		
	}
	
	private static void one() {
		
		
	}
	
	private static void two() {
		
		
	}
	
	private static void three() {
	
		
	}
	
	private static void four() {
	
		
	}
	
	private static void five() {
	
		
	}
	
	private static void six() {
		
		
	}
	
}
