package BOJ.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1018 {
	static char[][] board;
	static int N, M, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] input = in.readLine().split(" ");
		char[] line;
		
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		board = new char[N][M];
		ans = 65;
		
		for(int r = 0 ; r < N ; ++r) {
			line = in.readLine().toCharArray();
			for(int c = 0 ; c < M ; ++c) {
				board[r][c] = line[c];
			}
		}
		
		for(int or = 0 ; or <= N - 8 ; ++or) {
			for(int oc = 0 ; oc <= M - 8 ; ++oc) {
				ans = Math.min(ans, check(or, oc));
			}
		}
		
		System.out.println(ans);
		
	}
	private static int check(int or, int oc) {
		// true = white, false = black
		boolean colorFlag = true; 
		int wCnt = 0;
		int bCnt = 0;
		
		// white
		for(int ir = or ; ir < or + 8 ; ++ir) {
			colorFlag = colorFlag ? false : true;
			for(int ic = oc ; ic < oc + 8 ; ++ic) {
				if(colorFlag) {
					if(board[ir][ic] != 'W') wCnt++;
				} else {
					if(board[ir][ic] != 'B') wCnt++;
				}
				colorFlag = colorFlag ? false : true;
			}
		}
		
		colorFlag = false;
		
		// black
		for(int ir = or ; ir < or + 8 ; ++ir) {
			colorFlag = colorFlag ? false : true;
			for(int ic = oc ; ic < oc + 8 ; ++ic) {
				if(colorFlag) {
					if(board[ir][ic] != 'W') bCnt++;
				} else {
					if(board[ir][ic] != 'B') bCnt++;
				}
				colorFlag = colorFlag ? false : true;
			}
		}
		
		return Math.min(wCnt, bCnt);
	}
}
