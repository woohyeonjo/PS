package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16986_인싸들의가위바위보 {
	
	static final int JW = 1;
	static final int KH = 2;
	static final int MH = 3;
	
	static int[][] map;
	static int[][] hands;
	static int[] winCnt;
	static boolean[] visited;
	static boolean isWin;
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = stoi(st.nextToken());
		K = stoi(st.nextToken());
		
		// 낼 수 있는 손동작 수 보다 승수가 더 클 때 
		if(N < K) {
			System.out.println(0);
			return;
		}
		
		map = new int[N + 1][N + 1];
		hands = new int[3 + 1][20];
		visited = new boolean[N + 1];
		
		// 상성표 입력 
		for(int i = 1 ; i <= N ; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j <= N ; ++j) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		
		// 경희 손동작 입력 
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 20 ; ++i) hands[KH][i] = stoi(st.nextToken());
		// 민호 손동작 입력 
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 20 ; ++i) hands[MH][i] = stoi(st.nextToken());
		// 지우 손동작 순열 
		permutation(0);
		
		if(isWin) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}
	
	private static void permutation(int times) {
		if(isWin) return;
		
		if(times == N) {
			isWin = false;
			winCnt = new int[4];
			
			// 시뮬레이션 시작
			if(simulation(1, 2, 0, 0, 0)) {
				isWin = true;
			};
			
			return;
		}
		
		for(int i = 1 ; i <= N ; ++i) {
			if(!visited[i]) {
				visited[i] = true;
				hands[JW][times] = i;
				
				permutation(times + 1);
				if(isWin) return;
				
				hands[JW][times] = 0;
				visited[i] = false;
			}
		}
	}

	private static boolean simulation(int P1, int P2, int jwIdx, int khIdx, int mhIdx) {
		if(winCnt[JW] >= K) {
			return true;
		}

		if(jwIdx >= N || winCnt[KH] >= K || winCnt[MH] >= K) {
			return false;
		}
		
		int nextPlayer;
		// 다음 게임의 플레이어 찾기 (현재 게임에 참여하지 않는 사람)
		if((P1 == JW && P2 == KH) || (P1 == KH && P2 == JW)) nextPlayer = MH;
		else if((P1 == JW && P2 == MH) || (P1 == MH && P2 == JW)) nextPlayer = KH;
		else nextPlayer = JW;
		
		if(nextPlayer == JW) {
			// 경희 vs 민호 
			if(map[hands[KH][khIdx]][hands[MH][mhIdx]] == 2) {
				winCnt[KH]++;
				if(simulation(KH, JW, jwIdx, khIdx + 1, mhIdx + 1)) return true;
			} else {
				winCnt[MH]++;
				if(simulation(MH, JW, jwIdx, khIdx + 1, mhIdx + 1)) return true;
			}
		} else if(nextPlayer == KH) {
			// 지우 vs 민호 
			if(map[hands[JW][jwIdx]][hands[MH][mhIdx]] == 2) {
				winCnt[JW]++;
				if(simulation(JW, KH, jwIdx + 1, khIdx, mhIdx + 1)) return true;
			} else {
				winCnt[MH]++;
				if(simulation(MH, KH, jwIdx + 1, khIdx, mhIdx + 1)) return true;
			}
		} else {
			// 지우 vs 경희 
			if(map[hands[JW][jwIdx]][hands[KH][khIdx]] == 2) {
				winCnt[JW]++;
				if(simulation(JW, MH, jwIdx + 1, khIdx + 1, mhIdx)) return true;
			} else {
				winCnt[KH]++;
				if(simulation(KH, MH, jwIdx + 1, khIdx + 1, mhIdx)) return true;
			}
		}
		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
