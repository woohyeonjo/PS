package BOJ;

import java.util.Scanner;

public class B10974_모든순열_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] arr = new int[N];
		
		for(int i = 0 ; i < N ; ++i) {
			arr[i] = i + 1;
		}
		
		print(arr);
		
		while((arr = nextPermutation(arr)) != null) {
			print(arr);
		}
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
			if(next[j] > next[first]) {
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
	
	private static void print(int[] arr) {
		for(int i = 0 ; i < arr.length ; ++i) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
