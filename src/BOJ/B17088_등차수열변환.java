package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17088_등차수열변환 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		if(N == 1) {
			System.out.println(0);
			return;
		}
		
		int[] numbers = new int[N];
		int ans = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; ++i) numbers[i] = Integer.parseInt(st.nextToken());

		for(int i = -1 ; i <= 1 ; ++i) {
			for(int j = -1 ; j <= 1 ; ++j) {
				
				int[] arr = numbers.clone();
				int cnt = 0;
				boolean success = true;
				
				if(i != 0) cnt++;
				if(j != 0) cnt++;
				
				arr[0] += i;
				arr[1] += j;
				int diff = arr[1] - arr[0];
				
				for(int k = 2 ; k < N ; ++k) {
					
					int curDiff = arr[k] - arr[k - 1];
					boolean flag = false;
					
					for(int l = -1 ; l <= 1 ; ++l) {
						if(curDiff + l == diff) {
							if(l != 0) cnt++;
							
							arr[k] += l;
							flag = true;
							
							break;
						}
					}
					
					if(!flag) {
						success = false;
						break;
					}
				}
				if(success) ans = ans > cnt ? cnt : ans;
			}
		}
		
		if(ans == Integer.MAX_VALUE) ans = -1;
		System.out.println(ans);
	}

}
