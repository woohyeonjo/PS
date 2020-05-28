package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2115_모의벌꿀채취 {

	static int[][] map;
	static boolean[][] selected;
	static boolean[] selected2;
	static int[][] worker;
	static int[] income;
	static int N, M, C, T, ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			selected = new boolean[N][N];
			selected2 = new boolean[M];
			worker = new int[2][M];
			ans = 0;
			
			for(int r = 0 ; r < N ; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0 ; c < N ; ++c) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			selectHoneyComb(0, -1);
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void selectHoneyComb(int idx, int pos) {
		if(idx == 2) {
			// 벌통 선택 완료 수익 계산
			int total = 0;
			income = new int[2];
			
			for(int i = 0 ; i < 2 ; ++i) {
				findHighestIncome(i, 0, 0);
				total += income[i];
			}
			 
//			System.out.println("수익 : " + total);
			ans = total > ans ? total : ans;
			
			return;
		}
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				// 이전 좌표는 선택 안함  
				if(r * 100 + c <= pos) continue;
				// 가로로 놓을 수 없거나 이미 선택된 벌통은 제외 
				if(c + M - 1 >= N || selected[r][c]) continue;
				
				// 벌통 선택
				for(int i = 0 ; i < M ; ++i) {
					selected[r][c + i] = true;
					worker[idx][i] = map[r][c + i];
				}
//				System.out.println("[" + r + ", " + c + "]의 벌통 선택 ");
				selectHoneyComb(idx + 1, r * 100 + c);
				
				// 벌통 선택 해제
				for(int i = 0 ; i < M ; ++i) {
					selected[r][c + i] = false;
				}
			}
		}
	}

	private static void findHighestIncome(int idx, int sum, int money) {
		income[idx] = money > income[idx] ? money : income[idx];
		
		for(int i = 0 ; i < M ; ++i) {
			if(selected2[i]) continue;
			
			if(sum + worker[idx][i] <= C) {
				selected2[i] = true;
				findHighestIncome(idx, sum + worker[idx][i], money + worker[idx][i] * worker[idx][i]);
				selected2[i] = false;
			}
		}
	}

	

}
