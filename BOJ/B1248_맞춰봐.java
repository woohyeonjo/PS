package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1248_맞춰봐 {
	static int[] A;
	static char[][] S;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		A = new int[N];
		S = new char[N][N];

		char[] line = br.readLine().toCharArray();
		int limit = N * (N + 1) / 2;
		int r = 0, c = 0;
		for (int i = 0; i < limit; ++i) {
			S[r][c] = line[i];
			c++;
			if(c == N) {
				r++;
				c = r;
			}
		}

		// A를 뒤쪽에서부터 채워나간다. 
		backtracking(N - 1, 0);
	}
	
	// boolean형의 리턴을 통해 찾는 즉시 backtracking이 끝나도록 한다. 
	private static boolean backtracking(int depth, int sum) {
		// N개의 숫자를 모두 정했을 때 탈출 
		if(depth == - 1) {
			for (int i = 0; i < N; ++i) {
				System.out.print(A[i] + " ");
			}
			return true;
		}
	
		// 현재 depth에 넣을 숫자를 찾는다. 
		for(int i = -10 ; i <= 10 ; ++i) {
			// 현재 depth에서 선택할 수 있는 부호를 S[depth][depth]에서 확인한다. 
			if((S[depth][depth] == '+' && i <= 0) ||
			   (S[depth][depth] == '-' && i >= 0) ||
			   (S[depth][depth] == '0' && i != 0)) continue;
			
			// 합계를 구하여 S[depth][i]의 조건을 만족하는지 확인한다.
			boolean flag = true;
			int tempSum = sum + i;
			
			// 이전에 구한 S[depth][N - 1] ~ S[depth][depth + 1]의 합계 조건을 확인한다.
			for(int j = N - 1 ; j >= depth + 1 ; --j) {
				if((S[depth][j] == '+' && tempSum <= 0) ||
				   (S[depth][j] == '-' && tempSum >= 0) ||
				   (S[depth][j] == '0' && tempSum != 0)) {
					flag = false;
					break;
				}
				// 이전에 선택되어있던 숫자를 빼가면서 확인한다. 
				tempSum -= A[j];
			}
			
			if(flag) {
				A[depth] = i;
				if(backtracking(depth - 1, sum + i)) return true;
			}
		}
		return false;
	}
}
