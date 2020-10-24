package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 포기...


public class B5373_큐빙 {
	
	static final int LEFT = 0;
	static final int RIGHT = 11;
	static final char CLOCKWISE = '+';
	static final char ANTICLOCKWISE = '-';
	
	static final int SECOND = 3;
	static final int THIRD = 6;
	static final int FORTH = 9;
	
	static class Cube {
		char[][] UD, FB, LR;
		
		Cube() {
			// 앞, 오른, 뒤, 왼 
			UD = new char[3][12];
			// 위, 오른, 아래, 왼 
			FB = new char[12][3];
			// 위, 앞, 아래, 뒤 
			LR = new char[12][3];
			
			// 위
			for(int r = 0 ; r < 3 ; ++r) {
				for(int c = 0 ; c < 3 ; ++c) {
					FB[c][r] = 'w';
					LR[c][r] = 'w';
				}
			}
			// 아래
			for(int r = 0 ; r < 3 ; ++r) {
				for(int c = 0 ; c < 3 ; ++c) {
					FB[THIRD + c][r] = 'y';
					LR[THIRD + c][r] = 'y';
				}
			}
			// 앞
			for(int r = 0 ; r < 3 ; ++r) {
				for(int c = 0 ; c < 3 ; ++c) {
					UD[r][c] = 'r';
					LR[SECOND + c][r] = 'r';
				}
			}
			// 뒤 
			for(int r = 0 ; r < 3 ; ++r) {
				for(int c = 0 ; c < 3 ; ++c) {
					UD[r][THIRD + c] = 'o';
					LR[FORTH + c][r] = 'o';
				}
			}
			// 오른
			for(int r = 0 ; r < 3 ; ++r) {
				for(int c = 0 ; c < 3 ; ++c) {
					UD[r][SECOND + c] = 'b';
					FB[SECOND + c][r] = 'b';
				}
			}
			// 왼
			for(int r = 0 ; r < 3 ; ++r) {
				for(int c = 0 ; c < 3 ; ++c) {
					UD[r][FORTH + c] = 'g';
					FB[FORTH + c][r] = 'g';
				}
			}
		}
		
		void rotate(char side, char clock) {
			switch(side) {
			case 'U':
				if(clock == CLOCKWISE) rotate(side, 0, LEFT);
				else rotate(side, 0, RIGHT);
				break;
			case 'D':
				if(clock == CLOCKWISE) rotate(side, 2, RIGHT);
				else rotate(side, 2, LEFT);
				break;
			case 'F':
				if(clock == CLOCKWISE) rotate(side, 0, RIGHT);
				else rotate(side, 0, LEFT);
				break;
			case 'B':
				if(clock == CLOCKWISE) rotate(side, 2, LEFT);
				else rotate(side, 2, RIGHT);
				break;
			case 'L':
				if(clock == CLOCKWISE) rotate(side, 0, RIGHT);
				else rotate(side, 0, LEFT);
				break;
			case 'R':
				if(clock == CLOCKWISE) rotate(side, 2, LEFT);
				else rotate(side, 2, RIGHT);
				break;
			}
			
			sync(side);
		}
		void rotate(char side, int line, int dir) {
			char temp;
			
			for(int i = 0 ; i < 3 ; ++i) {
				switch(side) {
					case 'U':
					case 'D':
						temp = UD[line][dir];
						if(dir == LEFT) {
							for(int c = 0 ; c < 11 ; ++c) {
								UD[line][c] = UD[line][c + 1];
							}
							UD[line][11] = temp;
						} else {
							for(int c = 11 ; c > 0 ; --c) {
								UD[line][c] = UD[line][c - 1];
							}
							UD[line][0] = temp;
						}
						break;
					case 'F':
					case 'B':
						temp = FB[dir][line];
						if(dir == LEFT) {
							for(int r = 0 ; r < 11 ; ++r) {
								FB[r][line] = FB[r + 1][line];
							}
							FB[11][line] = temp;
						} else {
							for(int r = 11 ; r > 0 ; --r) {
								FB[r][line] = FB[r - 1][line];
							}
							FB[0][line] = temp;
						}
						break;
					case 'L':
					case 'R':
						temp = LR[dir][line];
						if(dir == LEFT) {
							for(int r = 0 ; r < 11 ; ++r) {
								LR[r][line] = LR[r + 1][line];
							}
							LR[11][line] = temp;
						} else {
							for(int r = 11 ; r > 0 ; --r) {
								LR[r][line] = LR[r - 1][line];
							}
							LR[0][line] = temp;
						}
						break;
				}
			}
		}
		
		void sync(char side) {
			switch(side) {
				case 'U':
				case 'D':
					// 첫 번째 칸
					LR[3][0] = UD[0][0];
					LR[3][1] = UD[1][0];
					LR[3][2] = UD[2][0];
					LR[4][0] = UD[0][1];
					LR[4][1] = UD[1][1];
					LR[4][2] = UD[2][1];
					LR[5][0] = UD[0][2];
					LR[5][1] = UD[1][2];
					LR[5][2] = UD[2][2];
					// 두 번째 칸
					FB[3][0] = UD[0][3];
					FB[3][1] = UD[1][3];
					FB[3][2] = UD[2][3];
					FB[4][0] = UD[0][4];
					FB[4][1] = UD[1][4];
					FB[4][2] = UD[2][4];
					FB[5][0] = UD[0][5];
					FB[5][1] = UD[1][5];
					FB[5][2] = UD[2][6];
					// 세 번째 칸 
					LR[9][0] = UD[0][6];
					LR[9][1] = UD[1][6];
					LR[9][2] = UD[2][6];
					LR[10][0] = UD[0][7];
					LR[10][1] = UD[1][7];
					LR[10][2] = UD[2][7];
					LR[11][0] = UD[0][8];
					LR[11][1] = UD[1][8];
					LR[11][2] = UD[2][8];
					// 네 번째 칸
					FB[9][0] = UD[0][9]; 
					FB[9][1] = UD[1][9];
					FB[9][2] = UD[2][9];
					FB[10][0] = UD[0][10];
					FB[10][1] = UD[1][10];
					FB[10][2] = UD[2][10];
					FB[11][0] = UD[0][11];
					FB[11][1] = UD[1][11];
					FB[11][2] = UD[2][11];
					break;
				case 'F':
				case 'B':
					// 첫 번째 칸
					for(int i = 0 ; i < 3 ; ++i) {
						for(int j = 0 ; j < 3 ; ++j) {
							LR[i][j] = FB[i][j];
						}
					}
					// 두 번째 칸
					UD[0][3] = FB[3][0];
					UD[1][3] = FB[3][1];
					UD[2][3] = FB[3][2];
					UD[0][4] = FB[4][0];
					UD[1][4] = FB[4][1];
					UD[2][4] = FB[4][2];
					UD[0][5] = FB[5][0];
					UD[1][5] = FB[5][1];
					UD[2][5] = FB[5][2];
					// 세 번째 칸 
					for(int i = 6 ; i < 9 ; ++i) {
						for(int j = 0 ; j < 3 ; ++j) {
							LR[i][j] = FB[i][j];
						}
					}
					// 네 번째 칸
					UD[0][9] = FB[9][0];
					UD[1][9] = FB[9][1];
					UD[2][9] = FB[9][2];
					UD[0][10] = FB[10][0];
					UD[1][10] = FB[10][1];
					UD[2][10] = FB[10][2];
					UD[0][11] = FB[11][0];
					UD[1][11] = FB[11][1];
					UD[2][11] = FB[11][2];
					break;
				case 'L':
				case 'R':
					// 첫 번째 칸
					for(int i = 0 ; i < 3 ; ++i) {
						for(int j = 0 ; j < 3 ; ++j) {
							FB[i][j] = LR[i][j];
						}
					}
					// 두 번째 칸
					UD[0][0] = LR[3][0];
					UD[1][0] = LR[3][1];
					UD[2][0] = LR[3][2];
					UD[0][1] = LR[4][0];
					UD[1][1] = LR[4][1];
					UD[2][1] = LR[4][2];
					UD[0][2] = LR[5][0];
					UD[1][2] = LR[5][1];
					UD[2][2] = LR[5][2];
					// 세 번째 칸 
					for(int i = 6 ; i < 9 ; ++i) {
						for(int j = 0 ; j < 3 ; ++j) {
							FB[i][j] = LR[i][j];
						}
					}
					// 네 번째 칸
					UD[0][6] = LR[9][0];
					UD[1][6] = LR[9][1];
					UD[2][6] = LR[9][2];
					UD[0][7] = LR[10][0];
					UD[1][7] = LR[10][1];
					UD[2][7] = LR[10][2];
					UD[0][8] = LR[11][0];
					UD[1][8] = LR[11][1];
					UD[2][8] = LR[11][2];
					break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = stoi(br.readLine());
		
		for(int i = 0 ; i < T ; ++i) {
			Cube cube = new Cube();
			int times = stoi(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < times ; ++j) {
				char[] cmd = st.nextToken().toCharArray();
				cube.rotate(cmd[0], cmd[1]);
			}
			
			for(int r = 0 ; r < 3 ; ++r) {
				for(int c = 0 ; c < 3 ; ++c) {
					System.out.print(cube.FB[r][c]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
