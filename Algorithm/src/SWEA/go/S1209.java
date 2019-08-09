package SWEA.go;

import java.util.Scanner;

public class S1209 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int[][] arr = new int[100][100];
		
		for(int t = 1 ; t <= 10 ; ++t) {
			int max = 0;
			int sumRow;
			int sumCol;
			int temp;
			int sumDiagonal1 = 0;
			int sumDiagonal2 = 0;
			
			sc.nextInt();
			for(int i = 0 ; i < 100 ; ++i) {
				for(int j = 0 ; j < 100 ; ++j) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			for(int i = 0 ; i < 100 ; ++i) {
				sumRow = 0;
				sumCol = 0;
				temp = 0;
				for(int j = 0 ; j < 100 ; ++j) {
					sumRow += arr[i][j];
					sumCol += arr[j][i];
				}
				temp = (sumRow > sumCol ? sumRow : sumCol);
				max = (temp > max ? temp : max);
			}
			
			for(int i = 0 ; i < 100 ; ++i) {
				sumDiagonal1 += arr[i][i];
			}
			max = sumDiagonal1 > max ? sumDiagonal1 : max;
			
			for(int i = 0 ; i < 100 ; ++i) {
				sumDiagonal2 += arr[i][99 - i];
			}
			max = (sumDiagonal2 > max ? sumDiagonal2 : max);
			
			System.out.println("#" + t + " " + max);
		}
	}
}
