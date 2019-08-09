package BOJ.go;

import java.util.Scanner;

public class B10250 {
	static int[][] hotel;
	static int T, H, W, N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int t = 1 ; t <= T ; ++t) {
			H = sc.nextInt();
			W = sc.nextInt();
			N = sc.nextInt();
			
			hotel = new int[H + 1][W + 1];
			for(int r = 1 ; r <= H ; ++r) {
				for(int c = 1 ; c <= W ; ++c) {
					if(c < 10) {
						hotel[r][c] = Integer.parseInt(r + "0" + c);
					} else {
						hotel[r][c] = Integer.parseInt(r + "" + c);
					}
				}
			}
			
			int cnt = 0;
	 OUTER: for(int c = 1 ; c <= W ; ++c) {
				for(int r = 1 ; r <=H ; ++r) {
					cnt++;
					if(cnt == N) {
						System.out.println(hotel[r][c]);
						break OUTER;
					}
				}
			}
			
		}
	}
}
