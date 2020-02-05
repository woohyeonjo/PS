package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10972_다음순열 {

	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		int[] next = null;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		next = nextPermutation(arr);
		
		if(next == null) System.out.println(-1);
		else {
			for(int i = 0 ; i < N ; ++i) {
				System.out.print(next[i] + " ");
			}
		}
	}
	
	// 다음 순열 함수은 O(n)의 시간복잡도를 갖는다.
	private static int[] nextPermutation(int[] permutation) {
		// 1차원 배열은 clone()으로 deep copy 가능  
		int[] next = permutation.clone();
		int first = -1, second = -1;
		
		// 주어진 순열을 탐색하며 N[i] < N[i + 1]인 가장 마지막 i를 찾는다. 
		for(int i = 0 ; i < next.length - 1 ; ++i) {
			if(next[i] < next[i + 1]) {
				first = i;
			}
		}
		// i를 찾지 못하면 현재 순열이 마지막 순열이다.
		if(first == -1) return null;
		
		// 주어진 순열의 마지막부터 역으로 탐색하며 N[j] > N[i]인 j를 찾는다. 
		for(int j = next.length - 1 ; j >= 0 ; --j) {
			if(next[j] > next[first]) {
				second = j;
				break;
			}
		}
		// i, j의 위치를 바꾼다. 
		swap(next, first, second);
		
		// i + 1 부터 끝까지를 뒤집는다.
		int left = first + 1;
		int right = next.length - 1;
		
		while(left < right) {
			swap(next, left, right);
			left++;
			right--;
		}		
		
		return next;
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
