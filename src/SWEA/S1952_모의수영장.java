package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1952_모의수영장 {
	
	static int[] cost;
	static int[] plan;
	static int T, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			cost = new int[4];
			plan = new int[13];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < 4 ; ++i) cost[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= 12 ; ++i) plan[i] = Integer.parseInt(st.nextToken());
		
			ans = cost[3];
			dfs(1, 0);
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void dfs(int month, int total) {
		if(month == 13) {
			ans = ans > total ? total : ans;
			return;
		}
		
		// 이용안할 때 안사기 
		if(plan[month] == 0) dfs(month + 1, total);
		
		// 1일 사용권으로 채우기 이용횟수 1회 이상 
		if(plan[month] > 0) dfs(month + 1, total + plan[month] * cost[0]);
		
		// 1달 사용권으로 채우기
		dfs(month + 1, total + cost[1]);
		
		// 3달 사용권으로 채우기  10월까지만 구매가능
		if(month <= 10) dfs(month + 3, total + cost[2]);
	}
}
