package CodingTest.Programmers;

public class Solution2_190928 {

	public static void main(String[] args) {
		String[] input = { "D5", "E8", "G2" };
		int answer = 0;

		boolean[][] board = new boolean[8 + 1][8 + 1];
		int[][] dir = { { -1, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 } };
		int nr, nc, cr, cc;
		
		for (int i = 0; i < input.length; ++i) {
			int col = (input[i].charAt(0) - 'A') + 1;
			int row = Math.abs((input[i].charAt(1) - '0') - 9);
			
			board[row][col] = true;
			
			for(int d = 0 ; d < 4 ; ++d) {
				cr = row;
				cc = col;
				while(true) {
					nr = cr + dir[d][0];
					nc = cc + dir[d][1];
					if(nr < 1 || nr > 8 || nc < 1 || nc > 8) break;
					board[nr][nc] = true;
					cr = nr;
					cc = nc;
				}
			}
		}

		for (int r = 1; r < 9; ++r) {
			for (int c = 1; c < 9; ++c) {
				if (!board[r][c])
					answer++;
			}
		}
		
		System.out.println(answer);
	}
}
