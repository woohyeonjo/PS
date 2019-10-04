package DS;

import java.util.Arrays;

public class BinarySearch {
	
	public static int search(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		int mid;
		
		while(left <= right) {
			mid = (left + right) / 2;
			if(arr[mid] == target) return mid;
			else if(arr[mid] > target) right = mid - 1;
			else if (arr[mid] < target) left = mid + 1;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] numbers = {1, 10, 100, 1000, 500, 5, 400};
		Arrays.sort(numbers);
		
		for(int i = 0 ; i < numbers.length ; ++i) System.out.print(numbers[i] + ", ");
		System.out.println();
		
		System.out.println(search(numbers, 500));
	}
}
