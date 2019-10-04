package SWEA;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class S1258 {
	
	static int[][] container;
	static boolean[][] visited;
	static int cnt;
	static ArrayList<subSet> ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1; t <= T ; ++t	) {
			int N = sc.nextInt();
			
			container = new int[N][N];
			visited = new boolean[N][N];
			ans = new ArrayList<subSet>();
			cnt = 0;
			
			for(int row = 0 ; row < N ; ++row) {
				for(int col = 0 ; col < N ; ++col) {
					container[row][col] = sc.nextInt();
				}
			}
			
			for(int row = 0 ; row < N ; ++row) {
				for(int col = 0 ; col < N ; ++col) {
					if(!visited[row][col] && container[row][col] != 0) {
						int nextR = row;
						int nextC = col;
						int rCnt = 1;
						int cCnt = 1;
						while(true) {
							nextR = nextR + 1;
							if(nextR < 0 || nextR >= N) break; 
							if(visited[nextR][col] || container[nextR][col] == 0) break;
							rCnt++;
						}
						while(true) {
							nextC = nextC + 1;
							if(nextC < 0 || nextC >= N) break; 
							if(visited[row][nextC] || container[row][nextC] == 0) break;
							cCnt++;
						}
						ans.add(new subSet(rCnt, cCnt));
						cnt++;
						for(int i = row ; i < row + rCnt ; ++i) {
							for(int j = col ; j < col + cCnt ; ++j) {
								visited[i][j] = true;
							}
						}
					}
				}
			}
			Collections.sort(ans);
			
			System.out.print("#" + t + " " + cnt);
			for(subSet s : ans) {
				System.out.print(" " + s.row + " " + s.col);
			}
			System.out.println();
		}
	}
	
	static class subSet implements Comparable<subSet>{
		int row, col;
		int size;
		
		public subSet(int row, int col) {
			super();
			this.row = row;
			this.col = col;
			this.size = row * col;
		}

		@Override
		public int compareTo(subSet o) {
			if(this.size - o.size != 0) return (this.size - o.size);
			else return (this.row - o.row);
		}
	}
}
