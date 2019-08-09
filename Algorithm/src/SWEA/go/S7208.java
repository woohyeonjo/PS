package SWEA.go;

import java.util.Arrays;
import java.util.Scanner;

public class S7208 {

	static int[][] adj;
	static boolean[] isDuplicated;
	static int[] colors_origin;
	static int[] colors;
	static int T, N, cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		for (int t = 1; t <= T; ++t) {
			N = sc.nextInt();

			adj = new int[N][N];
			colors_origin = new int[N];
			colors = new int[N];
			cnt = 0;
			
			for(int i = 0 ; i < N ; ++i) {
				int temp = sc.nextInt();
				colors_origin[i] = temp;
				colors[i] = temp;
			}
			
			for(int r = 0 ; r < N ; ++r) {
				for(int c = 0 ; c < N ; ++c) {
					adj[r][c] = sc.nextInt();
				}
			}
			
			int nowColor = 0;
			
			for(int r = 0 ; r < N ; ++r) {
				nowColor = colors[r];
				isDuplicated = new boolean[N];
				for(int c = 0 ; c < N ; ++c) {
					if(adj[r][c] == 1 && colors[c] == nowColor) {
						isDuplicated[c] = true;
					}
				}
				
				for(int c = 0 ; c < N ; ++c) {
					if(isDuplicated[c]) {
						if(colors[c] + 1 <= 4) colors[c]++;
						else colors[c]--;
					}
				}
			}
			
			for(int i = 0 ; i < N ; ++i) {
				if(colors[i] != colors_origin[i]) cnt++;
			}
			
			System.out.println(Arrays.toString(colors_origin));
			System.out.println(Arrays.toString(colors));
			System.out.println("#" + t + " " + cnt);
		}
	}
}
