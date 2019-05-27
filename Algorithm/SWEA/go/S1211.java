package Algorithm.SWEA.go;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// ?���? ?��?��?�� 2?���?

public class S1211 {
	
	static int[][] ladder;
	static int[] langth;
	static int[][] direction = {{0, -1}, {0, 1}};
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		for(int t = 1 ; t <= 10 ; ++t) {
			int T = sc.nextInt();
			ladder = new int[100][100];
			langth = new int[100];
			
			for(int row = 0 ; row < 100 ; ++row) {
				for(int col = 0 ; col < 100 ; ++col) {
					ladder[row][col] = sc.nextInt();
				}
			}
			
			for(int col = 0 ; col < 100 ; ++col) {
				if(ladder[0][col] == 1) {
					langth[col] = 1;
					int nowR = 0;
					int nowC = col;
					int nextR = 0;
					int nextC = 0;
					while(true) {
						if(nowR == 99) break;
						for(int i = 0 ; i < 2 ; ++i) {
							nextR = nowR + direction[i][0];
							nextC = nowC + direction[i][1];
							if(nextR >= 0 && nextR < 100 && nextC >= 0 && nextC < 100) {
								if(ladder[nextR][nextC] == 1) {
									langth[col]++;
									while(nextC >= 0 && nextC < 100) {
										if(ladder[nextR][nextC] != 1) break;
										langth[col]++;
										nextC += direction[i][1];
									}
									nowC = nextC - direction[i][1];
									nowR = nextR;
									break;
								}
							}
						}
						nowR += 1;
						langth[col]++;
					}
				}
			}
			
			int minIndex = 0;
			int min = Integer.MAX_VALUE;
			
			for(int i = 0 ; i < 100 ; ++i) {
				if(langth[i] != 0 && min > langth[i]) {
					min = langth[i];
					minIndex = i;
				}
			}
			
			System.out.println("#" + T + " " + minIndex);
			
		}
		

	}
}
