package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B2143_두배열의합 {

	static int[] A, B;
	static ArrayList<Long> sumA, sumB;
	static int N, M;
	static long T, cnt;
	
	public static void main(String[] args) throws IOException {

		// 데이터 입력 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Long.parseLong(br.readLine());
		N = stoi(br.readLine());
		
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; ++i) {
			A[i] = stoi(st.nextToken());
		}
		
		M = stoi(br.readLine());
		B = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; ++i) {
			B[i] = stoi(st.nextToken());
		}
		
		cnt = 0;
		sumA = new ArrayList<>();
		sumB = new ArrayList<>();
		
		// 합 리스트 만들기 
		for(int i = 0 ; i < N ; ++i) {
			long sum = 0;
			for(int j = i ; j < N ; ++j) {
				sum += A[j];
				sumA.add(sum);
			}
		}
		
		for(int i = 0 ; i < M ; ++i) {
			long sum = 0;
			for(int j = i ; j < M ; ++j) {
				sum += B[j];
				sumB.add(sum);
			}
		}
		
		// 이진탐색(lower, upper bound)를 사용하기 위해서는 오름차순으로 정렬되어 있어야한다
		Collections.sort(sumA);
		Collections.sort(sumB);
		
		for(int i = 0 ; i < sumA.size() ; ++i) {
			long target = T - sumA.get(i);
			cnt += upper_bound(0, sumB.size(), target) - lower_bound(0, sumB.size(), target);
		}
		
		System.out.println(cnt);
	}
	
	// lower_bound는 list에서 target이 등장하는 첫 번째 index를 반환한다.
	private static int lower_bound(int left, int right, long target) {
		while(left < right) {
			int mid = (left + right) / 2;
			if(sumB.get(mid) < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}
	
	// upper_bound는 list에서 마지막 target의 다음 index를 반환한다.
	private static int upper_bound(int left, int right, long target) {
		while(left < right) {
			int mid = (left + right) / 2;
			if(sumB.get(mid) <= target) { // target과 같을 때도 left를 갱신한다.
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
