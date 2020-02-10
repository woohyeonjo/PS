package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17779_게리맨더링2 {
	
	static int[][] population;
	static int[][] map;
	static int[] area;
	static int N, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		
		ans = Integer.MAX_VALUE;
		population = new int[N + 1][N + 1];

		for(int i = 1 ; i <= N ; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j <= N ; ++j) {
				population[i][j] = stoi(st.nextToken());
			}
		}
		
		// 1. map에 선거구를 그린다. 
		for(int x = 1 ; x <= N ; ++x) {
			for(int y = 1 ; y <= N ; ++y) {
				for(int d1 = 1 ; d1 <= N ; ++d1) {
					for(int d2 = 1 ; d2 <= N ; ++d2) {
						//  (1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)
						if((x + d1 + d2) <= N &&
						   (y - d1) >= 1 && (y - d1) < y &&
						   (y + d2) > y && (y + d2) <= N) {
							
//							System.out.println(x + " " + y + " " + d1 + " " + d2);
							map = new int[N + 1][N + 1];
							area = new int[6];
							
							setBoundary(x, y, d1, d2);
							setArea(x, y, d1, d2);
							calPopulation();
						}
					}
				}
			}
		}
		System.out.println(ans);
	}
	
	// 완성된 선거구에서 선거구별 인구수를 합한다. 
	private static void calPopulation() {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= N ; ++c) {
				area[map[r][c]] += population[r][c];
			}
		}
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int i = 1 ; i < 6 ; ++i) {
			max = area[i] > max ? area[i] : max;
			min = area[i] < min ? area[i] : min;
		}
		
		int gap = max - min;
		
		ans = ans > gap ? gap : ans;
	}


	private static void setArea(int x, int y, int d1, int d2) {
//		1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
//		2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
//		3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
//		4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
		
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= N ; ++c) {
				if(map[r][c] != 0) continue;
				
				if(r < (x + d1) && c <= y) map[r][c] = 1;
				else if((x + d2) >= r && c > y) map[r][c] = 2;
				else if((x + d1) <= r && c < (y - d1 + d2)) map[r][c] = 3;
				else if((x + d2) < r && (y - d1 + d2) <= c) map[r][c] = 4;
				else map[r][c] = 5;
			}
		}
	}


	private static void setBoundary(int x, int y, int d1, int d2) {
//		1. (x, y), (x+1, y-1), ..., (x+d1, y-d1)
//		2. (x, y), (x+1, y+1), ..., (x+d2, y+d2)
//		3. (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
//		4. (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
		// 변화값 
		map[x][y] = 5;
		int adder1 = 0, adder2 = 0;

		while(++adder1 <= d1) map[x + adder1][y - adder1] = 5;
		while(++adder2 <= d2) map[x + adder2][y + adder2] = 5;
		
		adder1 = 0; adder2 = 0;
		while(++adder2 <= d2) map[x + d1 + adder2][y - d1 + adder2] = 5;
		while(++adder1 <= d1) map[x + d2 + adder1][y + d2 - adder1] = 5;
		
		// 5구역 채우기 
		for(int r = 1 ; r <= N ; ++r) {
			int left = -1;
			int right = -1;
			
			int idx = 1;
			while(idx <= N) {
				if(map[r][idx] == 5) {
					left = idx;
					break;
				}
				idx++;
			}
			
			idx = N;
			while(idx >= 0) {
				if(map[r][idx] == 5) {
					right = idx;
					break;
				}
				idx--;
			}
			
			if(left != right) {
				for(int i = left ; i < right ; ++i) map[r][i] = 5;
			}
		}
	}

	private static void print() {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= N ; ++c) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
