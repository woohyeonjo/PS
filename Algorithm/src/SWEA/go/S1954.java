package SWEA.go;

import java.util.Scanner;

public class S1954 {
	
	static int[][] snailArr;
	static int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			int num = 2;
			int N = sc.nextInt();
			int x = 0, y = 0;
			int tempX = x, tempY = y;
			int directionNum = 1;
			snailArr = new int[N][N];
			snailArr[0][0] = 1;
			while(true) {
				if(num == Math.pow(N, 2) + 1) break;
				
				tempX = x + direction[directionNum % 4][0];
				tempY = y + direction[directionNum % 4][1];
				
				if(tempX < 0 || tempX >= snailArr.length
						|| tempY < 0 || tempY >= snailArr.length
						|| snailArr[tempX][tempY] != 0) {
					directionNum++;
					continue;
				}
				
				x = tempX;
				y = tempY;
				
				snailArr[x][y] = num++;
			}
			
			System.out.println("#" + t);
			for(int i = 0 ; i < N ; ++i) {
				for(int j = 0 ; j < N ; ++j) {
					System.out.print(snailArr[i][j] + " ");
				}
				System.out.println();
			}
			
		}
	}
}
