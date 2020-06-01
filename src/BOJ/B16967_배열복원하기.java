package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16967_배열복원하기 {
	
	static int[][] A, B;
	static int H, W, X, Y;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
	
		A = new int[H][W];
		B = new int[H + X][W + Y];
		
		for(int r = 0 ; r < B.length ; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0 ; c < B[0].length ; ++c) {
				B[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int r = 0 ; r < H ; ++r) {
			for(int c = 0 ; c < W ; ++c) {
				A[r][c] = B[r][c];

				if(X <= r && r <= H && Y <= c && c <= W) {
					A[r][c] -= A[r - X][c - Y];
				}
			}
		}
		
		print(A);
	}
	
	private static void print(int[][] arr) {
		for(int r = 0 ; r < arr.length ; ++r) {
			for(int c = 0 ; c < arr[0].length ; ++c) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
