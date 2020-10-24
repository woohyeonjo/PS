package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B10819_차이를최대로 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int max = -1;
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0 ; i < N ; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		do {
			int temp = cal(arr);
			max = temp > max ? temp : max;
		} while((arr = nextPermutation(arr)) != null);
		
		System.out.println(max);
	}
	
	private static int cal(int[] arr) {
		// |A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]|
		int result = 0;
		
		for(int i = 0 ; i < arr.length - 1 ; ++i) {
			result += Math.abs(arr[i] - arr[i + 1]);
		}
		
		return result;
	}
	
	private static int[] nextPermutation(int[] arr) {
		int[] next = arr.clone();
		
		int first = -1, second = -1;
		
		for(int i = 0 ; i < next.length - 1 ; ++i) {
			if(next[i] < next[i + 1]) {
				first = i;
			}
		}
		
		if(first == -1) return null;
		
		for(int j = next.length - 1 ; j >= 0 ; --j) {
			if(next[first] < next[j]) {
				second = j;
				break;
			}
		}
		
		swap(next, first, second);
		
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
