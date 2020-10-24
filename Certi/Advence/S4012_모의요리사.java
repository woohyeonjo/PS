package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S4012_모의요리사 {
	
	static int[][] synergy;
	static ArrayList<Integer> A, B;
	static int N, T;
	static long foodA, foodB, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			
			N = Integer.parseInt(br.readLine());
			
			synergy = new int[N + 1][N + 1];
			A = new ArrayList<>();
			B = new ArrayList<>();
			ans = Long.MAX_VALUE;
			
			for(int r = 1 ; r <= N ; ++r) {
				st = new StringTokenizer(br.readLine());
				for(int c = 1 ; c <= N ; ++c) {
					synergy[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			divide(1);
			
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void divide(int idx) {
		if(idx > N) {
			foodA = 0;
			foodB = 0;
			cook(true, 0, 0, new int[2]);
			cook(false, 0, 0, new int[2]);
			
			long gap = Math.abs(foodA - foodB);
			
			ans = ans > gap ? gap : ans;
			
			return;
		}
		
		int sizeA = A.size();
		int sizeB = B.size();
		
		if(sizeA < N / 2) {
			A.add(idx);
			divide(idx + 1);
			A.remove(A.size() - 1);
		}
		
		if(sizeB < N / 2) {
			B.add(idx);
			divide(idx + 1);
			B.remove(B.size() - 1);
		}
	}

	private static void cook(boolean isA, int cnt, int idx, int[] indexs) {
		ArrayList<Integer> list = isA ? A : B;
		
		if(cnt == 2) {
			if(isA) {
				foodA += synergy[indexs[0]][indexs[1]];
				foodA += synergy[indexs[1]][indexs[0]];
			} else {
				foodB += synergy[indexs[0]][indexs[1]];
				foodB += synergy[indexs[1]][indexs[0]];
			}
			
			return;
		}
		
		for(int i = idx ; i < list.size() ; ++i) {
			indexs[cnt] = list.get(i);
			cook(isA, cnt + 1, i + 1, indexs);
		}
	}
}
