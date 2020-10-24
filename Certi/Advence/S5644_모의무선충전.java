package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S5644_모의무선충전 {
	
	static class Node {
		int x, y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] bc;
	static int[][] user;
	static int[][] move;
	static int M, A, T, ans;
	static int[][] dir = {{0, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			ans = 0;
			
			bc = new int[A][4];
			user = new int[2][2];
			move = new int[2][M + 1];
			
			user[0][0] = 1;
			user[0][1] = 1;
			user[1][0] = 10;
			user[1][1] = 10;
			
			for(int i = 0 ; i < 2 ; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < M ; ++j) {
					move[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0 ; i < A ; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < 4 ; ++j) {
					bc[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0 ; i <= M ; ++i) {
				charge();
				user[0][0] += dir[move[0][i]][0];
				user[0][1] += dir[move[0][i]][1];
				user[1][0] += dir[move[1][i]][0];
				user[1][1] += dir[move[1][i]][1];
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void charge() {
		ArrayList<Integer> bcForA = new ArrayList<>();
		ArrayList<Integer> bcForB = new ArrayList<>();
		
		for(int i = 0 ; i < A ; ++i) {
			if(Math.abs(user[0][0] - bc[i][0]) + Math.abs(user[0][1] - bc[i][1]) <= bc[i][2]) {
				bcForA.add(i);
			}
			if(Math.abs(user[1][0] - bc[i][0]) + Math.abs(user[1][1] - bc[i][1]) <= bc[i][2]) {
				bcForB.add(i);
			}
		}

		int sizeA = bcForA.size();
		int sizeB = bcForB.size();
		int ap = 0, bp = 0;
		int max = 0;
		
		if(sizeA == 0 && sizeB == 0) {
			return;
		} else if(sizeA == 0) {
			for(int b : bcForB) {
				int sum = bc[b][3];
				bp = sum > bp ? sum : bp;
			}
		} else if(sizeB == 0) {
			for(int a : bcForA) {
				int sum = bc[a][3];
				ap = sum > ap ? sum : ap;
			}
		} else {
			for(int a : bcForA) {
				for(int b : bcForB) {
					int sum = 0;
					
					if(a == b) {
						sum = bc[a][3];
						
						if(sum > max) {
							ap = sum / 2;
							bp = sum / 2;
							max = sum;
						}
					} else {
						sum = bc[a][3] + bc[b][3];

						if(sum > max) {
							ap = bc[a][3];
							bp = bc[b][3];
							max = sum;
						}
					}
				}
			}
		}
		
		ans += ap + bp;
	}
}
