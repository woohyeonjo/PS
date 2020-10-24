package BOJ;


import java.util.Scanner;

public class B6987 {
	
	static int[][] scoreboard;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int t = 1 ; t <= 4 ; ++t) {
			
			scoreboard = new int[6][3];
			
			for(int r = 0 ; r < 6 ; ++r) {
				for(int c = 0 ; c < 3 ; ++c) {
					scoreboard[r][c] = sc.nextInt();
				}
			}
			
		}
	}
	
}
