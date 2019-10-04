package SWEA;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/*
 * 	1. 착수?�� �??��?�� 기�??���? ?��방탐?��?�� ?��?��?��?�� ?��?��?�� ?��???��?�� 찾아?��?��.
 * 
 * 	2-1. ?��???��?�� ?��?���? ?�� 종료
 * 	2-2. ?��???��?�� ?��?���? 메모리에 좌표�? ???��?���? ?��???��?�� 발견?�� 방향?���? ?��?���?�? ?��?��?��?��.
 * 
 * 	3-1. ?��무돌?�� ?��?�� �??��?���? ?��?��?�� 종료?��?��.
 * 	3-2. ?��???��?�� ?��?���? 메모리에 좌표�? ???��?���? 같�? 방향?���? ?���? ?�� ?��?���?�? ?��?��?��?��.
 * 	3-3. ?��?��?�� ?��?�� ?��?���? 메모리에 ???��?�� 모든 좌표?�� ?��?�� 반전?��?��?��.
 * 
 */

public class S4615 {
	
	static int[][] board;
	static int[][] direction = { {-1, -1}, {0, -1}, {1, -1},
								  {-1, 0},           {1, 0},
								  {-1, 1},  {0, 1},  {1, 1}};
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
			
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; ++t) {
			int W = 0;
			int B = 0;
			int N = sc.nextInt(); // 보드 ?�� �??�� 길이
			int M = sc.nextInt(); // ?��?��?��?���? ?��?�� ?��?�� ?��?��
			
			// ?�� 1 �? 2 빈곳 0
			board = new int[N][N];
			
			// 중앙?�� 기본?��?���? ?��?��?�� ?��?��
			board[N / 2 - 1][N / 2 - 1] = 2;
			board[N / 2][N / 2] = 2;
			board[N / 2][N / 2 - 1] = 1;
			board[N / 2 - 1][N / 2] = 1;
			
			
			for(int m = 0 ; m < M ; ++m) {
				int inputRow = sc.nextInt();
				int inputCol = sc.nextInt();
				int inputColor = sc.nextInt();
				play(inputRow - 1, inputCol - 1, inputColor);
				
				// 착수?�� 보드 출력
//				for(int i = 0 ; i < board.length ; ++i) {
//					for(int j = 0 ; j < board.length ; ++j) {
//						System.out.print(board[i][j]);
//					}
//					System.out.println();
//				}
//				System.out.println((inputRow - 1) + ", " + (inputCol - 1) + ", " + inputColor );
//				System.out.println();
			}
			
			// 게임종료 ?�� ?��?�� ?��?��
			for(int i = 0 ; i < board.length ; ++i) {
				for(int j = 0 ; j < board.length ; ++j) {
					if(board[i][j] == 1) B++;
					else if(board[i][j] == 2) W++;
				}
			}
			
			System.out.println("#" + t + " " + B + " " + W);
		}
		
	}
	
	static void play(int row, int col, int color) {
		ArrayList<int[]> memory;
		board[col][row] = color; // ?��?��?�� 좌표?�� 착수
		int firstRow;
		int firstCol;
		int nextRow;
		int nextCol;
		
		for(int i = 0 ; i < direction.length ; ++i) {
			memory = new ArrayList<int[]>(); // ?�� 방향?�� ?��?��?�� ?�� 마다 메모�? 초기?��
			
			// ?��방탐?��
			firstRow = row + direction[i][0];
			firstCol = col + direction[i][1];
			
			// 보드�? 벗어?��?���? ?��?��
			if(firstRow >= 0 && firstRow < board.length && firstCol >= 0 && firstCol < board.length) {
				// ?��방탐?�� �? ?��?�� 좌표?�� ?��?? ?��?�� ?��?���? ?��?��
				if(board[firstCol][firstRow] != color && board[firstCol][firstRow] != 0) {
					memory.add(new int[]{firstCol, firstRow}); // 메모리에 ?��?? ?�� �??�� ???��
					// ?��??�? ?��?�� ?��?�� 방향?���? ?��?���?�? ?��?��
					nextRow = firstRow + direction[i][0];
					nextCol = firstCol + direction[i][1];
					while(true) {
						// ?�� ?��?���? �??��?�� 보드�? 벗어?��?���? ?��?��
						if(nextRow >= 0 && nextRow < board.length && nextCol >= 0 && nextCol < board.length) {
							// ?��?���? �??��?�� 착수?�� ?���? 같�? ?��?�� ?��?�� ?��?���? ?��?�� (?��?? ?��?�� 감싸�? ?��?���?)
							if(board[nextCol][nextRow] == color) {
								// ?��?? ?��?�� 감싸�? ?��?���? 메모리에 ???��?�� 모든 �??��?�� ?��?�� 반전
								for(int j = 0 ; j < memory.size() ; ++j) {
									int[] temp = memory.get(j);
									board[temp[0]][temp[1]] = color;
								}
								break; // 반전 ?��켰다�? ?��?�� 방향?�� ???�� ?��?�� 종료
							}
							// ?��?���? �??��?�� ?��??�? ?��?�� 존재
							else if(board[nextCol][nextRow] != color && board[nextCol][nextRow] != 0 ) {
								memory.add(new int[]{nextCol, nextRow}); // 메모리에 ?��?? ?�� �??�� ???��
							}
							// ?��?���? �??��?�� ?���? ?��?�� ?��?���? ?�� 반전 ?��?�� 종료
							else if(board[nextCol][nextRow] == 0) break;
							
							// 같�? 방향?���? ?���? ?�� ?��?���?�?
							nextRow += direction[i][0];
							nextCol += direction[i][1];
						} else break; // 보드 벗어?��?���? ?��?�� 방향 ?��?�� 종료
					}
				}
			}
		}
	}
}
