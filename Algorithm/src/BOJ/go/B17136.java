package BOJ.go;

import java.util.Scanner;

public class B17136 {

	static int[][] map;
	static boolean[][] marked;
	static int[] cnt;
	static int ans, currentSize;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		map = new int[10][10];
		marked = new boolean[10][10];
		cnt = new int[6];
		ans = Integer.MAX_VALUE;
		currentSize = 0;

		for(int r = 0 ; r < 10 ; ++r) {
			for (int c = 0 ; c < 10 ; ++c) {
				map[r][c] = sc.nextInt();
			}
		}

		for(int i = 0 ; i < 5 ; ++i) {
			for(int r = 0 ; r < 10 ; ++r) {
				for (int c = 0 ; c < 10; ++c) {
					if(map[r][c] == 0) continue;
					if(i == 0) {

					}
				}
			}
		}
	}

	private static boolean marking_one(int r, int c) {
		if(marked[r][c]) return false;
		marked[r][c] = true;
		return true;
	}

	private static boolean marking_two(int r, int c) {
		if(!marking_one(r, c)) return false;
		if(r + 1 >= 10 || c + 1 >= 10) return false;
		if(map[r + 1][c] == 0 || map[r][c + 1] == 0 || map[r + 1][c + 1] == 0 ||
			marked[r + 1][c] || marked[r][c + 1] || marked[r + 1][c + 1]) return false;

		for(int i = r ; i < r + 2 ; ++i) {
			for(int j = c ; j < c + 2 ; ++j) {
				marked[i][j] = true;
			}
		}
		return true;
	}

	private static boolean marking_three(int r, int c) {
		if(!marking_two(r, c)) return false;
		if(r + 2 >= 10 || c + 2 >= 10) return false;

		for(int i = r ; i < r + 3 ; ++i) if(map[i][c + 2] == 0 || marked[i][c + 2]) return false;
		for(int j = c ; j < c + 3 ; ++j) if(map[r + 2][j] == 0 || marked[r + 2][j]) return false;

		for(int i = r ; i < r + 3 ; ++i) {
			for(int j = c ; j < c + 3 ; ++j) {
				marked[i][j] = true;
			}
		}
		return true;
	}

	private static boolean marking_four(int r, int c) {
		if(!marking_three(r, c)) return false;
		if(r + 3 >= 10 || c + 3 >= 10) return false;

		for(int i = r ; i < r + 4 ; ++i) if(map[i][c + 3] == 0 || marked[i][c + 3]) return false;
		for(int j = c ; j < c + 4 ; ++j) if(map[r + 3][j] == 0 || marked[r + 3][j]) return false;

		for(int i = r ; i < r + 4 ; ++i) {
			for(int j = c ; j < c + 4 ; ++j) {
				marked[i][j] = true;
			}
		}
		return true;
	}

	private static boolean marking_five(int r, int c) {
		if(!marking_four(r, c)) return false;
		if(r + 4 >= 10 || c + 4 >= 10) return false;

		for(int i = r ; i < r + 5 ; ++i) if(map[i][c + 4] == 0 || marked[i][c + 4]) return false;
		for(int j = c ; j < c + 5 ; ++j) if(map[r + 4][j] == 0 || marked[r + 4][j]) return false;

		for(int i = r ; i < r + 5 ; ++i) {
			for(int j = c ; j < c + 5 ; ++j) {
				marked[i][j] = true;
			}
		}
		return true;
	}

	private static void remove(int r, int c, int size) {
		for(int i = r ; i < r + size ; ++i) {
			for(int j = c ; j < c + size ; ++j) {
				marked[i][j] = false;
			}
		}
	}

}
