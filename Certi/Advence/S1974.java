package SWEA;


import java.util.ArrayList;
import java.util.Scanner;

public class S1974 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] list;
		int[] count;
		int[][] direction = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1},
							{1, 0}, {1, -1}, {0, -1}, {-1, -1}};
		int[][] point = {{1, 1}, {4, 1}, {7, 1},
						 {1, 4}, {4, 4}, {7, 4},
						 {1, 7}, {4, 7}, {7, 7}};
		
		int T = sc.nextInt();
		
		for(int t = 1 ; t <= 10 ; ++t) {
			list = new int[9][9];
			int result = 1;
			
			for(int row = 0 ; row < 9 ; ++row) {
				for(int col = 0 ; col < 9 ; ++col) {
					list[col][row] = sc.nextInt();
				}
			}
			
			OUTER: for(int row = 0 ; row < 9 ; ++row) {
				count = new int[10];
				for(int col = 0 ; col < 9 ; ++col) {
					count[list[col][row]]++;
				}
				for(int i = 1 ; i <= 9 ; ++i) {
					if(count[i] != 1) {
						result = 0;
						break OUTER;
					}
				}
			}
			
			OUTER: for(int row = 0 ; row < 9 ; ++row) {
				count = new int[10];
				for(int col = 0 ; col < 9 ; ++col) {
					count[list[row][col]]++;
				}
				for(int i = 1 ; i <= 9 ; ++i) {
					if(count[i] != 1) {
						result = 0;
						break OUTER;
					}
				}
			}
			
			OUTER: for(int i = 0 ; i < 9 ; ++i) {
				count = new int[10];
				int row = point[i][1];
				int col = point[i][0];
				count[list[row][col]]++;
				for(int j = 0 ; j < 8 ; ++j) {
					count[list[row + direction[j][1]][col + direction[j][0]]]++;
				}
				for(int j = 1 ; j <= 9 ; ++j) {
					if(count[j] != 1) {
						result = 0;
						break OUTER;
					}
				}
			}
			
//			
//			for(int row = 0 ; row < 9 ; ++row) {
//				for(int col = 0 ; col < 9 ; ++col) {
//					System.out.print(list[col][row] + " ");
//				}
//				System.out.println();
//			}
			System.out.println("#t" + t + " " + result);
			
		}
	}
}
