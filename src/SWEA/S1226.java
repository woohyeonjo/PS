package SWEA;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class S1226 {
	
	static int[][] maze;
	static int[][] isVisited;
	static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int isPossible;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		
		for(int t = 0 ; t < 10 ; ++t) {
			int T = Integer.parseInt(sc.nextLine());
			isPossible = 0;
			maze = new int[16][16];
			isVisited = new int[16][16];
			
			int startR = 0;
			int startC = 0;
			
			for(int row = 0 ; row < 16 ; ++row) {
				String[] temp = sc.nextLine().split("");
				for(int col = 0 ; col < 16 ; ++col) {
					if(temp[col].equals("2")) {
						startR = row;
						startC = col;
					}
					maze[row][col] = Integer.parseInt(temp[col]);
				}
			}
			dfs(startR, startC);
			System.out.println("#" + T + " " + isPossible);
		}
	}

	private static void dfs(int startR, int startC) {
		if(maze[startR][startC] == 3) {
			isPossible = 1;
			return;
		}
		
		int nextR, nextC;
		
		for(int i = 0 ; i < 4 ; ++i) {
			if(startR + direction[i][0] >= 0 && startR + direction[i][0] < 16
					&& startC + direction[i][0] >= 0 && startC + direction[i][0] < 16) {
					nextR = startR + direction[i][0];
					nextC = startC + direction[i][1];
				if(maze[nextR][nextC] != 1 && isVisited[nextR][nextC] == 0) {
					isVisited[nextR][nextC] = 1;
					dfs(nextR, nextC);
					isVisited[nextR][nextC] = 0;
				}
			}
		}
	}
}
