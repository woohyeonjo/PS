package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B15662_톱니바퀴2 {

	static final int LEFT = 6;
	static final int RIGHT = 2;
	static int[][] gear;
	static int T, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = stoi(br.readLine());
		
		gear = new int[T][8];
		
		// S = 1, N = 0
		for(int i = 0 ; i < T ; ++i) {
			char[] line = br.readLine().toCharArray();
			for(int j = 0 ; j < 8 ; ++j) {
				gear[i][j] = line[j] - '0';
			}
		}
		
		K = stoi(br.readLine());
		for(int i = 0 ; i < K ; ++i) {
			st = new StringTokenizer(br.readLine());
			int idx = stoi(st.nextToken());
			int dir = stoi(st.nextToken());
			
			turn(idx - 1, dir);
		}
		
		int cnt = 0;
		for(int i = 0 ; i < T ; ++i) {
			if(gear[i][0] == 1) cnt++;
		}
		
		System.out.println(cnt);
	}
	
	private static void turn(int idx, int dir) {
		int[] isRotate = new int[T];
		isRotate[idx] = dir;
		
		int nextDir = -(dir);
		
		// 지정 톱니바퀴 오른쪽
		for(int i = idx + 1 ; i < T ; ++i) {
			if(gear[i][LEFT] != gear[i - 1][RIGHT]) {
				isRotate[i] = nextDir;
				nextDir = -nextDir;
			} else break;
		}
		
		nextDir = -(dir);
		// 지정 톱니바퀴 왼쪽
		for(int i = idx - 1 ; i >= 0 ; --i) {
			if(gear[i][RIGHT] != gear[i + 1][LEFT]) {
				isRotate[i] = nextDir;
				nextDir = -nextDir;
			} else break;
		}
		
		for(int i = 0 ; i < T ; ++i) {
			if(isRotate[i] != 0) {
				rotation(i, isRotate[i]);
			}
		}
	}

	private static void rotation(int idx, int dir) {
		
		// 시계 방향 
		if(dir == 1) {
			int temp = gear[idx][7];
			for(int i = 7 ; i > 0 ; --i) {
				gear[idx][i] = gear[idx][i - 1];
			}
			gear[idx][0] = temp;
		// 반시계 방향 
		} else {
			int temp = gear[idx][0];
			for(int i = 0 ; i < 7 ; ++i) {
				gear[idx][i] = gear[idx][i + 1];
			}
			gear[idx][7] = temp;
		}
	}
	
	private static void print() {
		for(int i = 0 ; i < T ; ++i) {
			for(int j = 0 ; j < 8 ; ++j) {
				System.out.print(gear[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
