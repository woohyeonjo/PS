package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16939_2x2x2큐브 {

	static int[][] cube, copy;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		cube = new int[8][8];
		
		for(int i = 0 ; i < 6 ; ++i) {
			for(int j = 2 ; j < 4 ; ++j) {
				cube[i][j] = stoi(st.nextToken());
			}
		}
		
		for(int i = 2 ; i < 4 ; ++i) {
			for(int j = 0 ; j < 2 ; ++j) {
				cube[i][j] = stoi(st.nextToken());
			}
		}
		
		for(int i = 2 ; i < 4 ; ++i) {
			for(int j = 4 ; j < 6 ; ++j) {
				cube[i][j] = stoi(st.nextToken());
			}
		}
		
		for(int i = 2 ; i < 4 ; ++i) {
			for(int j = 6 ; j < 8 ; ++j) {
				cube[i][j] = stoi(st.nextToken());
			}
		}
		
		sync(cube, 0);

		// 수평 위 돌리기
		copy();
		rotateH(2, 0);
		rotateH(2, 0);
		rotateSquare(0, 2, 0);
		if(check(copy)) {
			System.out.println(1);
			return;
		}
		
		copy();
		rotateH(2, 1);
		rotateH(2, 1);
		rotateSquare(0, 2, 1);
		if(check(copy)) {
			System.out.println(1);
			return;
		}
		
		// 수평 아래 돌리기
		copy();
		rotateH(3, 0);
		rotateH(3, 0);
		rotateSquare(4, 2, 0);
		if(check(copy)) {
			System.out.println(1);
			return;
		}

		copy();
		rotateH(3, 1);
		rotateH(3, 1);
		rotateSquare(4, 2, 1);
		if(check(copy)) {
			System.out.println(1);
			return;
		}
		
		// 수직 왼쪽 돌리기 
		copy();
		rotateV(2, 0);
		rotateV(2, 0);
		rotateSquare(2, 0, 0);
		if(check(copy)) {
			System.out.println(1);
			return;
		}

		copy();
		rotateV(2, 1);
		rotateV(2, 1);
		rotateSquare(2, 0, 1);
		if(check(copy)) {
			System.out.println(1);
			return;
		}

		// 수직 오른쪽 돌리기 
		copy();
		rotateV(3, 0);
		rotateV(3, 0);
		rotateSquare(2, 4, 0);
		if(check(copy)) {
			System.out.println(1);
			return;
		}

		copy();
		rotateV(3, 1);
		rotateV(3, 1);
		rotateSquare(2, 4, 1);
		if(check(copy)) {
			System.out.println(1);
			return;
		}
		
		// 앞면 돌리기
		for(int i = 0 ; i < 2 ; ++i) {
			rotateFront(i);
			if(check(copy)) {
				System.out.println(1);
				return;
			}
		}
		
		// 뒷면 돌리
		for(int i = 0 ; i < 2 ; ++i) {
			rotateBack(i);
			if(check(copy)) {
				System.out.println(1);
				return;
			}
		}
		
		System.out.println(0);
	}
	
	private static void rotateBack(int dir) {
		copy();
		
		if(dir == 0) {
			rotateSquare(2, 6, 0);
			int temp1 = copy[2][5];
			int temp2 = copy[2][6];
			
			copy[2][5] = copy[5][3];
			copy[2][6] = copy[5][2];
			copy[5][3] = copy[3][0];
			copy[5][2] = copy[2][0];
			copy[3][0] = copy[0][2];
			copy[2][0] = copy[0][3];
			copy[0][2] = temp1;
			copy[0][3] = temp2;
		} else {
			rotateSquare(2, 6, 0);
			int temp1 = copy[2][5];
			int temp2 = copy[2][6];
			
			copy[2][5] = copy[0][2];
			copy[2][6] = copy[0][3];
			copy[0][2] = copy[3][0];
			copy[0][3] = copy[2][0];
			copy[2][0] = copy[5][2];
			copy[3][0] = copy[5][3];
			copy[5][3] = temp1;
			copy[5][2] = temp2;
		}
	}

	private static void rotateFront(int dir) {
		copy();
		
		if(dir == 0) {
			rotateSquare(2, 2, 0);
			int temp1 = copy[1][2];
			int temp2 = copy[1][3];
			
			copy[1][2] = copy[3][1];
			copy[1][3] = copy[2][1];
			copy[3][1] = copy[4][2];
			copy[2][1] = copy[4][3];
			copy[4][2] = copy[3][4];
			copy[4][3] = copy[2][4];
			copy[2][4] = temp1;
			copy[3][4] = temp2;
		} else {
			rotateSquare(2, 2, 1);
			int temp1 = copy[1][2];
			int temp2 = copy[1][3];
			
			copy[1][2] = copy[2][4];
			copy[1][3] = copy[3][4];
			copy[2][4] = copy[4][3];
			copy[3][4] = copy[4][2];
			copy[4][3] = copy[3][1];
			copy[4][2] = copy[2][1];
			copy[3][1] = temp1;
			copy[2][1] = temp2;
		}
		sync(copy, 0);
	}
	
	// 가로로 돌리기
	private static void rotateH(int row, int dir) {
		// 오른쪽 
		if(dir == 0) {
			int temp = copy[row][7];
			
			for(int c = 7 ; c >= 1 ; --c) {
				copy[row][c] = copy[row][c - 1];
			}
			copy[row][0] = temp;
		// 왼쪽 
		} else {
			int temp = copy[row][0];
			
			for(int c = 0 ; c <= 6 ; ++c) {
				copy[row][c] = copy[row][c + 1];
			}
			copy[row][7] = temp;
		}
		sync(copy, 0);
	}
	
	// 세로로 돌리기
	private static void rotateV(int col, int dir) {
		// 아래로 
		if(dir == 0) {
			int temp = copy[7][col];
			
			for(int r = 7 ; r >= 1 ; --r) {
				copy[r][col] = copy[r - 1][col];
			}
			copy[0][col] = temp;
		// 위로 
		} else {
			int temp = copy[0][col];
			
			for(int r = 0 ; r <= 6 ; ++r) {
				copy[r][col] = copy[r + 1][col];
			}
			copy[7][col] = temp;
		}
		sync(copy, 1);
	}
	
	// 사각형 돌리기 
	private static void rotateSquare(int row, int col, int dir) {
		// 시계 방향 
		if(dir == 0) {
			int temp = copy[row][col];
			copy[row][col] = copy[row + 1][col];
			copy[row + 1][col] = copy[row + 1][col + 1];
			copy[row + 1][col + 1] = copy[row][col + 1];
			copy[row][col + 1] = temp;
		// 반시계 방향 
		} else {
			int temp = copy[row][col];
			copy[row][col] = copy[row][col + 1];
			copy[row][col + 1] = copy[row + 1][col + 1];
			copy[row + 1][col + 1] = copy[row + 1][col];
			copy[row + 1][col] = temp;
		}
		
	}
	
	private static void sync(int[][] arr, int dir) {
		// 가로축에서 세로축으로 
		if(dir == 0) {
			arr[6][2] = arr[2][7];
			arr[7][2] = arr[3][7];
			arr[6][3] = arr[2][6];
			arr[7][3] = arr[3][6];
		// 세로축에서 가로축으로 
		} else {
			arr[2][7] = arr[6][2];
			arr[3][7] = arr[7][2];
		    arr[2][6] = arr[6][3];
		    arr[3][6] = arr[7][3];
		}
	}
	
	private static boolean check(int[][] arr) {
		
		int color = arr[0][2];
		for(int r = 0 ; r < 2 ; ++r) {
			for(int c = 2 ; c < 4 ; ++c) {
				if(arr[r][c] != color) return false;
			}
		}
		
		color = arr[2][0]; 
		for(int r = 2 ; r < 4 ; ++r) {
			for(int c = 0 ; c < 2 ; ++c) {
				if(arr[r][c] != color) return false;
			}
		}
		
		color = arr[2][2];
		for(int r = 2 ; r < 4 ; ++r) {
			for(int c = 2 ; c < 4 ; ++c) {
				if(arr[r][c] != color) return false;
			}
		}
		
		color = arr[2][4];
		for(int r = 2 ; r < 4 ; ++r) {
			for(int c = 4 ; c < 6 ; ++c) {
				if(arr[r][c] != color) return false;
			}
		}
		
		color = arr[2][6];
		for(int r = 2 ; r < 4 ; ++r) {
			for(int c = 6 ; c < 8 ; ++c) {
				if(arr[r][c] != color) return false;
			}
		}
		
		color = arr[4][2];
		for(int r = 4 ; r < 6 ; ++r) {
			for(int c = 2 ; c < 4 ; ++c) {
				if(arr[r][c] != color) return false;
			}
		}
		
		return true;
	}
	
	private static void copy() {
		copy = new int[8][8];
		
		for(int i = 0 ; i < 8 ; ++i) {
			for(int j = 0 ; j < 8 ; ++j) {
				copy[i][j] = cube[i][j];
			}
		}
	}
	
	private static void print(int[][] arr) {
		for(int i = 0 ; i < 8 ; ++i) {
			for(int j = 0 ; j < 8 ; ++j) {
				if(arr[i][j] == 0) System.out.print(" ");
				else System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
