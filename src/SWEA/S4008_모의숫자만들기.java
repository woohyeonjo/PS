package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4008_모의숫자만들기 {
	
	static int[] op;
	static int[] cnt;
	static int[] numbers;
	static int N, T, min, max, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			N = Integer.parseInt(br.readLine());
			
			numbers = new int[N];
			op = new int[N - 1];
			cnt = new int[4];
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			ans = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < 4 ; ++i) {
				cnt[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; ++i) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			combination(0);
			
			ans = max - min;
			
			System.out.println("#" + t + " " + ans);
		}
		
	}

	private static void combination(int idx) {
		
		if(idx == N - 1) {
			int result = cal();
			
			min = min > result ? result : min;
			max = result > max ? result : max;
			
			return;
		}
		
		for(int i = 0 ; i < 4 ; ++i) {
			if(cnt[i] > 0 ) {
				cnt[i]--;
				op[idx] = i;
				combination(idx + 1);
				cnt[i]++;
			}
		}
	}

	private static int cal() {
		int result = numbers[0];
		int nidx = 1;
		
		for(int i = 0 ; i < N - 1 ; ++i) {
			switch(op[i]) {
			case 0:
				result += numbers[nidx++];
				break;
			case 1:
				result -= numbers[nidx++];
				break;
			case 2:
				result *= numbers[nidx++];
				break;
			case 3:
				result /= numbers[nidx++];
				break;
			}
		}
		
		return result;
	}
}
