package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2003_수들의합2 {
	
	static int[] numbers;
	static int N, M, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		ans = 0;
		numbers = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; ++i) {
			numbers[i] = stoi(st.nextToken());
		}
		
		// 투 포인터가 될 start, end
		int end = 0;
		int sum = 0;
		
		for(int start = 0 ; start < N ; ++start) {
			// start를 증가시키며 sum에 더 한다.
			sum += numbers[start];
			
			// sum이 정확히 M이 되면 
			if(sum == M) {
				ans++;
				// ans를 증가시키고 현재 end위치의 수를 sum에 빼주고 end를 증가시킨다.
				sum -= numbers[end++];
			} else if(sum > M) {
				// sum이 M을 넘었을 
				while(sum > M) {
					// sum이 M보다 작아지거나 M과 같아질 때 까지 반복하며
					// end를 증가시키며 가 가리키는 수를 sum에서 뺀다. 
					sum -= numbers[end++];
					
					if(sum == M) {
						ans++;
						break;
					}
				}
			}
		}
		
		System.out.println(ans);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
