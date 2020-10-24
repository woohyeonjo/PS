package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B4574_스도미노쿠 {
	
	static int[][] dir = {{0, 1}, {1, 0}};
	static int[][] sdoku;
	static boolean[][] col;
	static boolean[][] row;
	static boolean[][][] square;
	static boolean[][] domino;
	static int N, T;
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = 0;
		
		while(true) {
			T++;
			N = stoi(br.readLine());
			
			// 테스트케이스 탈출 조건 
			if(N == 0) break;
			
			init();
			
			for(int i = 0 ; i < N ; ++i) {
				st = new StringTokenizer(br.readLine());
				
				// 도미노 숫자 1
				int num1 = stoi(st.nextToken());
				char[] position1 = st.nextToken().toCharArray();
				// 도미노 숫자 2
				int num2 = stoi(st.nextToken());
				char[] position2 = st.nextToken().toCharArray();
				
				int r1 = position1[0] - 'A';
				int c1 = position1[1] - '0' - 1;
				int r2 = position2[0] - 'A';
				int c2 = position2[1] - '0' - 1;
				
				sdoku[r1][c1] = num1;
				sdoku[r2][c2] = num2;
				
				// 스도쿠 숫자 체크 
				row[r1][num1] = true;
				col[c1][num1] = true;
				row[r2][num2] = true;
				col[c2][num2] = true;
				square[r1 / 3][c1 / 3][num1] = true;
				square[r2 / 3][c2 / 3][num2] = true;
				
				// 도미노 사용 체크
				domino[num1][num2] = true;
				domino[num2][num1] = true;
			}
			
			// 스도쿠 숫자 1 ~ 9 넣기 
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= 9 ; ++i) {
				char[] position = st.nextToken().toCharArray();
				int r = position[0] - 'A';
				int c = position[1] - '0' - 1;
				
				row[r][i] = true;
				col[c][i] = true;
				square[r / 3][c / 3][i] = true;
				sdoku[r][c] = i;
			}
			System.out.println("Puzzle " + T);
			backtracking(0);
		}
	}
	
	private static boolean backtracking(int pos) {
		if(pos == 81) {
			print();
			return true;
		}
		
		int r = pos / 9;
		int c = pos % 9;
		
		// 아직 숫자를 놓지 않았으면서 
		if(sdoku[r][c] == 0) {
			// 가로 또는 세로로 놓는 경우 
			for(int d = 0 ; d < 2 ; ++d) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				// 오른쪽이나 아래로 벗어나지 않으면서 아직 숫자가 없어야 한다. 
				if(nr > 8 || nc > 8 || sdoku[nr][nc] != 0) continue;
				
				// 모든 서로 다른 숫자 쌍 
				for(int i = 1 ; i < 9 ; ++i) {
					for(int j = i + 1 ; j < 10 ; ++j) {
						if(domino[i][j] || domino[j][i]) continue;
						domino[i][j] = domino[j][i] = true;
						
						// i, j가 행과 열에 사용된적 없어야 하며 3x3 사각형에도 사용된적 없어야 한다.
						if(!row[r][i] && !col[c][i] && !square[r / 3][c / 3][i]) {
							if(!row[nr][j] && !col[nc][j] && !square[nr / 3][nc / 3][j]) {
								row[r][i] = col[c][i] = true;
								square[r / 3][c / 3][i] = true;
								row[nr][j] = col[nc][j] = true;
								square[nr / 3][nc / 3][j] = true;
								
								sdoku[r][c] = i;
								sdoku[nr][nc] = j;
								if(backtracking(pos + 1)) return true;
								sdoku[nr][nc] = 0;
								sdoku[r][c] = 0;

								row[r][i] = col[c][i] = false;
								square[r / 3][c / 3][i] = false;
								row[nr][j] = col[nc][j] = false;
								square[nr / 3][nc / 3][j] = false;
							}
						}

						// j, i가 행과 열에 사용된적 없어야 하며 3x3 사각형에도 사용된적 없어야 한다.
						if(!row[r][j] && !col[c][j] && !square[r / 3][c / 3][j]) {
							if(!row[nr][i] && !col[nc][i] && !square[nr / 3][nc / 3][i]) {
								row[r][j] = col[c][j] = true;
								square[r / 3][c / 3][j] = true;
								row[nr][i] = col[nc][i] = true;
								square[nr / 3][nc / 3][i] = true;
								
								sdoku[r][c] = j;
								sdoku[nr][nc] = i;
								if(backtracking(pos + 1)) return true;
								sdoku[nr][nc] = 0;
								sdoku[r][c] = 0;

								row[r][j] = col[c][j] = false;
								square[r / 3][c / 3][j] = false;
								row[nr][i] = col[nc][i] = false;
								square[nr / 3][nc / 3][i] = false;
								
							}
						}
						domino[i][j] = domino[j][i] = false;
					}
				}
			}
		} else {
			// 현재 지역에 숫자가 이미 있으면 넘어간다. 
			if(backtracking(pos + 1)) return true;;
		}

		return false;
	}

	private static void init() {
		sdoku = new int[9][9];
		row = new boolean[9][10];
		col = new boolean[9][10];
		square = new boolean[3][3][10];
		domino = new boolean[10][10];
	}
	
	private static void print() {
		for(int r = 0 ; r < 9 ; ++r) {
			for(int c = 0 ; c < 9 ; ++c) {
				System.out.print(sdoku[r][c]);
			}
			System.out.println();
		}
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
