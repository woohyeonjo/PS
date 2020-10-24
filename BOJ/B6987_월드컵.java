package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B6987_월드컵 {
	
	static int[] home = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
	static int[] away = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
	static int[][] score;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		score = new int[6][3];
		
		for(int i = 0 ; i < 4 ; ++i) {
			int total = 0;
			st = new StringTokenizer(br.readLine());
			
			for(int r = 0 ; r < 6 ; ++r) {
				for(int c = 0 ; c < 3 ; ++c) {
					score[r][c] = Integer.parseInt(st.nextToken());
					total += score[r][c];
				}
			}
			
			if(total > 30) {
				System.out.print(0 + " ");
				continue;
			}
			
			if(play(0)) System.out.print(1 + " ");
			else System.out.print(0 + " ");
		}
	}

	private static boolean play(int game) {

		if(game == 15) {
			return true;
		}

		// 홈 팀이 이기는 경우
		if(score[home[game]][0] > 0 && score[away[game]][2] > 0) {
			score[home[game]][0]--;
			score[away[game]][2]--;
			if(play(game + 1)) return true;
			score[home[game]][0]++;
			score[away[game]][2]++;
		}
		
		// 어웨이 팀이 이기는 경우 
		if(score[home[game]][2] > 0 && score[away[game]][0] > 0) {
			score[home[game]][2]--;
			score[away[game]][0]--;
			if(play(game + 1)) return true;
			score[home[game]][2]++;
			score[away[game]][0]++;
		}
		
		// 비기는 경우 
		if(score[home[game]][1] > 0 && score[away[game]][1] > 0) {
			score[home[game]][1]--;
			score[away[game]][1]--;
			if(play(game + 1)) return true;
			score[home[game]][1]++;
			score[away[game]][1]++;
		}
		
		return false;
	}
}
