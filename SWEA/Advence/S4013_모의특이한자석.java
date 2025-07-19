package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4013_모의특이한자석 {
	
	static final int CLOCKWISE = 1;
	static final int NONE = 0;
	static final int ANTI_CLOCKWISE = -1;
	static final int N = 0;
	static final int S = 1;
	
	static int[] score;
	static int[] action;
	static int[][] magnet; 
	static int K, T, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		score = new int[4];
		score[0] = 1;
		score[1] = 2;
		score[2] = 4;
		score[3] = 8;
		
		for(int t = 1 ; t <= T ; ++t) {
			K = Integer.parseInt(br.readLine());
			
			magnet = new int[4][8];
			ans = 0;
			
			for(int r = 0 ; r < 4 ; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0 ; c < 8 ; ++c) {
					magnet[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0 ; i < K ; ++i) {
				st = new StringTokenizer(br.readLine());
				
				int id = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());

				checkChaining(id, dir);
				
				for(int j = 0 ; j < 4 ; ++j) {
					turnMagnet(j);
				}
			}
			
			for(int i = 0 ; i < 4 ; ++i) {
				if(magnet[i][0] == S) ans += score[i];
			}
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void turnMagnet(int id) {
		int temp;
		switch(action[id]) {
			case CLOCKWISE:
				temp = magnet[id][7];
				for(int i = 7 ; i > 0 ; --i) {
					magnet[id][i] = magnet[id][i - 1];
				}
				magnet[id][0] = temp;
				break;
			case ANTI_CLOCKWISE:
				temp = magnet[id][0];
				for(int i = 0 ; i < 7 ; ++i) {
					magnet[id][i] = magnet[id][i + 1];
				}
				magnet[id][7] = temp;
				break;
			case NONE:
				break;
		}
	}

	private static void checkChaining(int id, int dir) {
		action = new int[4];
		action[id] = dir;
		
		// 현재 자석에서 오른쪽으로
		for(int i = id + 1 ; i < 4 ; ++i) {
			if(magnet[i][6] != magnet[i - 1][2]) {
				action[i] = action[i - 1] == 1 ? -1 : 1;
			} else {
				action[i] = 0;
				break;
			}
		}
		
		// 현재 자석에서 왼쪽으로
		for(int i = id - 1 ; i >= 0 ; --i) {
			if(magnet[i][2] != magnet[i + 1][6]) {
				action[i] = action[i + 1] == 1 ? -1 : 1;
			} else {
				action[i] = 0;
				break;
			}
		}
	}
}
