package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B1208_부분수열의합2 {
	
	static ArrayList<Integer> A, B;
	static int[] input;
	static int N, S;
	static long ans;
	
	public static void main(String[] args) throws IOException {

		// 데이터 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		S = stoi(st.nextToken());
		
		input = new int[N];
		A = new ArrayList<>();
		B = new ArrayList<>();
		ans = 0;

		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; ++i) {
			input[i] = stoi(st.nextToken());
		}
		
		// 반으로 나눠서 합 구하기 
		makeSumArray(0, 0, N / 2, A);
		makeSumArray(0, N / 2, N, B);
		
		// 양쪽 리스트를 오름차순으로 정렬 
		Collections.sort(A);
		Collections.sort(B);
		
		// 처리
		int left = 0;
		int right = B.size() - 1;
		
		while(left < A.size() && right >= 0) {
			int lValue = A.get(left);
			int rValue = B.get(right);
			
			if(lValue + rValue == S) {
				// 같은 합이 들어있을 수 있기 때문에 다음과 같은 체크를 해줘야한다.
				// left를 증가시키며 lcnt를 증가시킨다.
				long lcnt = 0;
				while(left < A.size() && A.get(left) == lValue) {
					left++;
					lcnt++;
				}
				
				// right를 감소시키며 rcnt를 증가시킨다. 
				long rcnt = 0;
				while(right >= 0 && B.get(right) == rValue) {
					right--;
					rcnt++;
				}
				// 두 경우를 곱하면 모든 경우가 나온다. 
				ans += lcnt * rcnt;
			}
			
			if(lValue + rValue > S) {
				right--;
			}
			
			if(lValue + rValue < S) {
				left++;
			}
		}
		
		// S가 0일때는 아무것도 선택 안했을 경우를 빼줘야한다. 
		ans = S == 0 ? ans - 1 : ans;
		System.out.println(ans);
	}
	
	// 배열안의 요소들로 만들수 있는 모든 합의 경우 
	private static void makeSumArray(int sum, int idx, int end, ArrayList<Integer> list) {
		if(idx == end) {
			list.add(sum);
			return;
		}
		// 현재 idx 선택 
		makeSumArray(sum + input[idx], idx + 1, end, list);
		// 현재 idx 선택 안함 
		makeSumArray(sum, idx + 1, end, list);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}