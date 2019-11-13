package CodingTest;

public class Solution {
	
	public static void main(String[] args) {
//		int[][] grid = {{2, 1, 1, 0, 1},
//						{1, 2, 0, 3, 0},
//						{0, 1, 5, 1, 2},
//						{0, 0, 1, 3, 1},
//						{1, 2, 0, 1, 1}};
		int[][] grid = {{3,4,5},{2,3,4},{1,2,3}};
		int k = 1;
		
		boolean[][] visited = new boolean[grid.length][grid.length];
		int result = 0;
		int max = 0;
		int current = 0;
		int max_r = 0, max_c = 0;
		
		for(int r = 0 ; r < grid.length ; ++r) {
			for(int c = 0 ; c < grid.length ; ++c) {
				current = check(r, c, k, grid, visited);
				if(current > max) {
					max = current;
					max_r = r;
					max_c = c;
				}
			}
		}
		
		result += max;
		max = 0;
		mark(max_r, max_c, k, visited);
		
		for(int r = 0 ; r < grid.length ; ++r) {
			for(int c = 0 ; c < grid.length ; ++c) {
				current = check(r, c, k, grid, visited);
				if(current > max) {
					max = current;
					max_r = r;
					max_c = c;
				}
			}
		}
		
		result += max;
		System.out.println(result);
	}

	private static void mark(int max_r, int max_c, int k, boolean[][] visited) {
		for(int i = max_r ; i < max_r + k ; ++i) {
			if(i >= visited.length) continue;
			for(int j = max_c ; j < max_c + k ; ++j) {
				if(j >= visited.length) continue;
				visited[i][j] = true;
			}
		}		
	}

	private static int check(int r, int c, int k, int[][] grid, boolean[][] visited) {
		int sum = 0;
		
		for(int i = r ; i < r + k ; ++i) {
			if(i >= grid.length) continue;
			for(int j = c ; j < c + k ; ++j) {
				if(j >= grid.length) continue;
				if(visited[i][j]) return 0;
				sum += grid[i][j];
			}
		}
		return sum;
	}
	

}
