package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B15661_링크와스타트 {
	
	static int[][] ability;
	static boolean[] team;
	static int N, amount, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		ability = new int[N + 1][N + 1];
		ans = Integer.MAX_VALUE;
		
		// 능력치 정보 입력받기 
		for(int i = 1 ; i <= N ; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j <= N ; ++j) {
				ability[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// N명중에 1 ~ N - 1명 뽑기 
		for(int i = 1 ; i <= N / 2 ; ++i) {
			team = new boolean[N + 1];
			select(i, 1, 1);
		}
		
		System.out.println(ans);
	}

	private static void select(int limit, int depth, int idx) {
		if(limit < depth) {
//			System.out.print("Team1: ");
//			for(int i = 1 ; i <= N ; ++i) {
//				if(team[i]) System.out.print(i);
//			}
//			System.out.println();
//			System.out.print("Team2: ");
//			for(int i = 1 ; i <= N ; ++i) {
//				if(!team[i]) System.out.print(i);
//			}
//			System.out.println();
			int gap = cal();
			
			ans = ans > gap ? gap : ans;
			return;
		}
		
		for(int i = idx ; i <= N ; ++i) {
			team[i] = true;
			select(limit, depth + 1, i + 1);
			team[i] = false;
		}
	}
	
	private static int cal() {
		int team1 = 0;
		int team2 = 0;
		
		for(int i = 1 ; i < N ; ++i) {
			for(int j = i + 1 ; j <= N ; ++j) {
				if(team[i] && team[j]) {
					team1 += ability[i][j] + ability[j][i];
				} else if(!team[i] && !team[j]) {
					team2 += ability[i][j] + ability[j][i];
				}
			}
		}
		
//		System.out.println(team1 + " : " + team2);
//		System.out.println();
		return Math.abs(team1 - team2);
	}
}
